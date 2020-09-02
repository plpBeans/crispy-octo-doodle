package vip.hengnai.wine.ui.mine.beer;

import android.graphics.Bitmap;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.hengnai.wine.R;
import vip.hengnai.wine.framework.BaseAppCompatActivity;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * 啤酒不凡详情
 *
 * @author Hugh
 */
public class BeerDetailActivity extends BaseAppCompatActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_title_right)
    TextView textTitleRight;
    @BindView(R.id.img_arrow)
    ImageView imgArrow;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    WebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_detail);
        ButterKnife.bind(this);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        textTitle.setText("详情");
        initView();
    }

    private void initView() {
        webView = new WebView(getApplicationContext());
//        /**
//         * 配置硬盘缓存
//         */
//        boolean externalStorageAvailable = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
//        String cachePath=this.getFilesDir().getAbsolutePath();
//        if (externalStorageAvailable) {
//            cachePath = this.getExternalCacheDir().getPath()+"/webviewCache" ;
//        } else {
//            cachePath = this.getCacheDir().getPath()+"/webviewCache" ;
//        }
//        createDir(cachePath);
        WebSettings webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //优先使用缓存:设置缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //提高渲染的优先级
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
//        //支持插件
//        webSettings.setPluginsEnabled(true);
        //设置自适应屏幕，两者合用
        //将图片调整到适合webview的大小
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);

        //缩放操作
        //支持缩放，默认为true。是下面那个的前提。
        webSettings.setSupportZoom(true);
        //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setBuiltInZoomControls(true);
        //隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(false);
        //其他细节操作
        //是否允许访问 文件
        webSettings.setAllowFileAccess(true);
        //设置应用缓存文件的路径
//        webSettings.setAppCachePath(cachePath);

        //应用缓存API是否可用 结合setAppCachePath(String)使用
        webSettings.setAppCacheEnabled(true);
        //缓存相关

        //不适用缓存
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //设置编码格式
        webSettings.setDefaultTextEncodingName("utf-8");

        //清除网页访问留下的缓存
        //由于内核缓存是全局的因此这个方法不仅仅针对webview而是针对整个应用程序.
        //        WebView.clearCache(true);

        //清除当前webview访问的历史记录
        //只会webview访问历史记录里的所有记录除了当前访问记录
        //        webView.clearHistory();

        //这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据
        //        webView.clearFormData();

        webView.loadUrl("https://www.baidu.com/");
        //步骤3. 复写shouldOverrideUrlLoading()方法，
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //使得打开网页时不调用系统浏览器， 而是在本WebView中显示
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                } else {
                    view.loadUrl(request.toString());
                }
                return true;

            }
            //            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                //使得打开网页时不调用系统浏览器， 而是在本WebView中显示
//                view.loadUrl(url);
//                return true;
//            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //设定加载开始的操作
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //设定加载结束的操作
            }

            @Override
            public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
                super.onReceivedLoginRequest(view, realm, account, args);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                //设定加载资源的操作
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (request.isForMainFrame()) {// 在这里加上个判断
                        // 显示错误界面
                    }
                } else {
                    // 显示错误界面
                }

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //处理https请
//                if (error.getPrimaryError() == SslError.SSL_DATE_INVALID  // 日期不正确
//                        || error.getPrimaryError() == SslError.SSL_EXPIRED // 日期不正确
//                        || error.getPrimaryError() == SslError.SSL_INVALID // webview BUG
//                        || error.getPrimaryError() == SslError.SSL_UNTRUSTED // 根证书丢失
//                        || error.getPrimaryError() == SslError.SSL_IDMISMATCH // 根证书不匹配
//                        ) {
//
//                    boolean check = CheckSSLCertUtil.checkMySSLCNCert(error.getCertificate());
//                    if (check) {
//                        handler.proceed();  // 如果证书一致，忽略错误
//                    }
//                }
                handler.proceed();    //表示等待证书响应
                // handler.cancel();      //表示挂起连接，为默认方式
                // handler.handleMessage(null);    //可做其他处理
            }
        });
//        webView.setWebChromeClient(new WebChromeClient() {
//
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress < 100) {
//                    progressBar.setVisibility(View.VISIBLE);
//                    progressBar.setProgress(newProgress);
//                } else {
//                    // to do something...
//                    progressBar.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
////                titleview.setText(title);
//            }
//        });
        frameLayout.addView(webView);
    }

    /**
     * 创建文件夹
     * @param dirPath
     * @return
     */
    public  String createDir(String dirPath){
        //因为文件夹可能有多层，比如:  a/b/c/ff.txt  需要先创建a文件夹，然后b文件夹然后...
        try{
            File file=new File(dirPath);
            if(file.getParentFile().exists()){

                file.mkdir();
                return file.getAbsolutePath();
            } else {
                createDir(file.getParentFile().getAbsolutePath());

                file.mkdir();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return dirPath;
    }
    public class CheckSSLCertUtil {

        /**
         * 验证域名证书
         *
         * @param cert
         * @return
         */
        public boolean checkMySSLCNCert(SslCertificate cert) {

            byte[] MySSLCNSHA256 = {60, -37, 44, -43, 115, -111, -31, -28, 61, 105, 117, -99, 123, 98, -87, 71, -30,
                    58, -109, 70, 55, -84, -116, 74, -108, 64, -122, -56, -45, 34, -23, 12};  //证书指纹

            Bundle bundle = SslCertificate.saveState(cert);
            byte[] bytes = bundle.getByteArray("x509-certificate");
            if (bytes != null) {
                try {
                    CertificateFactory cf = CertificateFactory.getInstance("X.509");
                    Certificate ca = cf.generateCertificate(new ByteArrayInputStream(bytes));
                    MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
                    byte[] Key = sha256.digest(((X509Certificate) ca).getEncoded());
                    return Arrays.equals(Key, MySSLCNSHA256);
                } catch (Exception Ex) {
                }
            }
            return false;
        }

    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            ViewParent parent = webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(webView);
            }
            webView.stopLoading();
            webView.getSettings().setJavaScriptEnabled(false);
            webView.removeAllViews();
            webView.destroy();
        }
        super.onDestroy();
    }
}
