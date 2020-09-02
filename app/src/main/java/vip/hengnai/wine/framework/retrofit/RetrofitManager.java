package vip.hengnai.wine.framework.retrofit;

import android.content.Context;
import android.util.Log;

import okhttp3.internal.tls.OkHostnameVerifier;
import vip.hengnai.wine.BuildConfig;
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.SellWineApplication;
import vip.hengnai.wine.framework.api.ApiService;
import vip.hengnai.wine.util.AuthUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import vip.hengnai.wine.util.ssl.HttpsUtils;
import vip.hengnai.wine.util.ssl.TrustDoubleCerts;
import vip.hengnai.wine.util.ssl.TrustHostnameVerifier;
import vip.hengnai.wine.util.ssl.TrustSingleCerts;


/**
 *
 * @author hua
 * @date 2019/7/18
 */

public class RetrofitManager {
    private static final String BASE_URL = "http://demo.sqsh365.com:7001/xaclinterface/";
    public static OkHttpClient.Builder builder;
    private static Context mContext;

    public RetrofitManager(Context mContext) {
        RetrofitManager.mContext = mContext;
    }
    public static ApiService getInstance() {
        //Okhttp可以放在Application中只创建一次
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(120, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
//        builder.sslSocketFactory(HttpsUtils.getSslSocketFactory().sslSocketFactory,HttpsUtils.UnSafeTrustManager);
//        builder.sslSocketFactory(null,HttpsUtils.UnSafeTrustManager);
        //支持https信任指定服务器的证书---单向认证
//                try {
                    //自签名单项认证方法，ca机构颁发的证书一般不需要配置代码mContext.getResources().openRawResource(R.raw.hengnai)
//                    SSLContext sslContext =  new TrustSingleCerts().setCertificates(mContext.getResources().openRawResource(R.raw.hengnai));
//                    builder.sslSocketFactory(sslContext.getSocketFactory(),new TrustSingleCerts());
////                        HttpsUtils.SSLParams sslParams= HttpsUtils.getSslSocketFactory(mContext.getResources().openRawResource(R.raw.hengnai));
////                        builder.sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager);
////                        builder.hostnameVerifier(OkHostnameVerifier.INSTANCE);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
        //双向认证
//        try {
//            SSLContext sslContext =  new TrustDoubleCerts().setCertificates(null);
//            builder.sslSocketFactory(sslContext.getSocketFactory(),new TrustDoubleCerts());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        builder.hostnameVerifier(new TrustHostnameVerifier());
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder();

                Log.e("手机信息","手机型号:"+android.os.Build.MODEL);
                Log.e("手机信息","手机品牌:"+android.os.Build.BRAND);
                Log.e("手机信息","手机系统版本号 :"+android.os.Build.VERSION.RELEASE);
                Log.e("手机信息","UserAgent:"+ System.getProperty("http.agent"));

                //默认认证信息
                builder.addHeader("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJoZW5nbmFpLnZpcCIsImlhdCI6MTU3NzA3MjYyNCwiZXhwIjoxNTc3MTU5MDI0LCJzdWIiOiIxODYyMTY5ODkxMSJ9.ou8k5vmz5geh0KtiJKtb3hO5yM5uSLP7P2TrV0UtZvg");
                //设备id
                builder.addHeader("terminalId", SellWineApplication.decId);
                //经纬度
                builder.addHeader("geo", SellWineApplication.longitude+","+SellWineApplication.latitude);

                builder.addHeader("accessToken", AuthUtil.getAuthUtil(mContext).getToken());

                return chain.proceed(builder.build());
            }
        });
        return new Retrofit.Builder().baseUrl(Constants.getBaseUrl())
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build().create(ApiService.class);

    }
}
