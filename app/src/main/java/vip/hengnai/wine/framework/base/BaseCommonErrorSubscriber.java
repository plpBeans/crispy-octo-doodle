package vip.hengnai.wine.framework.base;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import vip.hengnai.wine.BuildConfig;
import vip.hengnai.wine.framework.IMvpView;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;


/**
 * 接口需要token时使用，在token失效时强制重新登录
 *
 * @author hua
 * @date 2019/8/21
 */

public abstract class BaseCommonErrorSubscriber<T> extends DisposableObserver<T> {

    @Override
    protected void onStart() {
        getMvpView().showLoadingView();
    }

    /**
     *view对象
     * @return
     */
    @NonNull
    public abstract IMvpView getMvpView();

    @CallSuper
    @Override
    public void onError(Throwable e) {
        if (BuildConfig.DEBUG) {
            e.printStackTrace();
        }
        String message = e.getMessage();
        if (TextUtils.isEmpty(message)) {
            message = "请求出错，请稍候重试！";
        }
        getMvpView().hideLoadingView();
        if (e instanceof SocketTimeoutException) {
            getMvpView().showErrorMessage("请求超时，请检查网络后重试！");
        } else if(e instanceof SocketException){
            getMvpView().showErrorMessage("网络请求失败，请稍后重试！");
        }else if(e instanceof UnknownHostException){
            getMvpView().showErrorMessage("网络请求失败，请稍后重试！");
        }else {
            getMvpView().showErrorMessage(message);
            if (e instanceof MyException) {
                if (((MyException) e).getCode() == 999 ) {
                    getMvpView().forceToReLogin(message);
                    return;
                }
            }
        }
    }


    @CallSuper
    @Override
    public void onComplete() {
        getMvpView().hideLoadingView();
    }
}
