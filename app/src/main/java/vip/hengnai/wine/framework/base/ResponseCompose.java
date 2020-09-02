package vip.hengnai.wine.framework.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import vip.hengnai.wine.BuildConfig;

/**
 * 统一处理subscribe工具类
 *
 * @author hua
 * @date 2019/8/21
 */

public class ResponseCompose {
    /***
     * 请求成功
     */
    public static final String RESPONSE_CODE_SUCCESS = "0";
    /***
     * 该用户没有权限
     */
    public static final String NO_PERMISSION = "403";
    /***
     * 用户认证失败,没有登录(此时返回到登录界面)
     */
    public static final String NO_LOGIN = "401";
    /***
     * 错误的请求
     */
    public static final String BAD_REQUEST= "400";
    /***
     * 网络服务异常
     */
    public static final String INTERNAL_SERVER_ERROR= "500";

    private static Gson mGson = new Gson();
    private static Context mContext;

    public interface Converter<T extends BaseResponse, R> {
        /**
         *解析
         * @param value
         * @return
         */
        R convert(T value);
    }

    public ResponseCompose(Context context) {
        mContext = context;
    }
    /**
     * this method will be removed next version
     * please use {@link #handleResponse(Converter, Class)} instead
     *
     * @param converter
     * @param tClass
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T extends BaseResponse, R> ObservableTransformer<Response<String>, R> handleResponse(@NonNull final Converter<T, R> converter, final Class<T> tClass) {
        return new ObservableTransformer<Response<String>, R>() {
            @Override
            public Observable<R> apply(Observable<Response<String>> upstream) {
                return upstream.flatMap(new Function<Response<String>, Observable<R>>() {
                    @Override
                    public Observable<R> apply(Response<String> stringResponse) throws Exception {
                        return createData(stringResponse, converter, tClass);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }



    private static <T extends BaseResponse, R> Observable<R> createData(final Response<String> response, final Converter<T, R> converter, final Class<T> tClass) {
        return Observable.create(new ObservableOnSubscribe<R>() {
            @Override
            public void subscribe(ObservableEmitter<R> subscriber) {
                try {
                    MyException exception = null;
                    switch (response.code()) {
                        case 400:
                            exception = new MyException(response.code(), "参数不符合 API 的要求、或者数据格式验证没有通过");
                            break;
                        case 401:
                            exception = new MyException(response.code(), "认证失败");
                            break;
                        case 403:
                            exception = new MyException(response.code(), "权限不足");
                            break;
                        case 404:
                            exception = new MyException(response.code(), "接口地址错误！");
                            break;
                        case 500:
                            exception = new MyException(response.code(), "服务器开了会儿小差，请稍候重试！");
                            break;
                        default:
                            break;
                    }
                    if (null != exception) {
                        subscriber.onError(exception);
                        return;
                    }
                    T body = null;
                    String bodyStr = response.body();
                    Log.d("ResponseCompose", bodyStr);
                    JSONObject bodyJson;
                    try {
                        bodyJson = new JSONObject(bodyStr);
                        Iterator<String> keys = bodyJson.keys();
                        List<String> emptyKeys = new ArrayList<>(1);
                        while (keys.hasNext()) {
                            String key = keys.next();
                            if (bodyJson.get(key) == null || TextUtils.isEmpty(bodyJson.getString(key))) {
                                emptyKeys.add(key);
                            }
                        }
                        for (String emptyKey : emptyKeys) {
                            bodyJson.remove(emptyKey);
                        }
                        body = mGson.fromJson(bodyJson.toString(), tClass);
                    } catch (JSONException e) {
                        if (BuildConfig.DEBUG) {
                            Log.d("ResponseCompose", e.getMessage());
                        }
                        exception = new MyException(response.code(), "数据有误，请重试！");
                    }
                    if (body != null) {
                        String status = body.getStatus();
                        String message = body.getMessage();
                        if (TextUtils.isEmpty(message)) {
                            message = "出错啦，请稍候重试！";
                        }
                        if (TextUtils.isEmpty(status)) {
                            status = RESPONSE_CODE_SUCCESS;
                        }
                        if (NO_LOGIN.equals(status)) {
                            exception = new MyException(Integer.valueOf(NO_LOGIN), message);
                        } else if (NO_PERMISSION.equals(status)) {
                            exception = new MyException(Integer.valueOf(NO_PERMISSION), message);
                        } else if (INTERNAL_SERVER_ERROR.equals(status)) {
                            exception = new MyException(Integer.valueOf(INTERNAL_SERVER_ERROR), message);
                        }else if (BAD_REQUEST.equals(status)) {
                            exception = new MyException(Integer.valueOf(BAD_REQUEST), message);
                        }
                    }
                    if (null != exception) {
                        if (BuildConfig.DEBUG) {
                            Log.d("ResponseCompose", exception.getMessage());
                        }
                        subscriber.onError(exception);
                    } else {
                        if (converter == null) {
                            subscriber.onError(new Throwable("the Converter can not be null"));
                        } else {
                            subscriber.onNext(converter.convert(body));
                            subscriber.onComplete();
                        }
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
