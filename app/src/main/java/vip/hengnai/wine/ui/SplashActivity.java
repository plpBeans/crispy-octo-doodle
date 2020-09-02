package vip.hengnai.wine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import vip.hengnai.wine.R;
import vip.hengnai.wine.framework.BaseAppCompatActivity;
import vip.hengnai.wine.ui.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Hugh
 */
public class SplashActivity extends BaseAppCompatActivity {

    @BindView(R.id.ad_iv)
    ImageView adIv;
    @BindView(R.id.ad_time_tv)
    TextView adTimeTv;
    @BindView(R.id.ad_rl)
    RelativeLayout adRl;
    private int time;
//    private Handler mDataHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            enterToMain();
//        }
//    };
//    private Handler mAdverHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//           JSONObject jsonObject = (JSONObject)msg.obj;
////            if(jsonObject == null){
////                enterToMain();
////            }else{
//                showAd(jsonObject);
////            }
//        }
//    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (time <= 0) {
                enterToMain();
            } else {
                time = time - 1;
                adTimeTv.setText("跳过" + time + "s");
                mHandler.sendEmptyMessageDelayed(0, 1000);
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        // 注意顺序
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        adTimeTv.setVisibility(View.VISIBLE);

            time = 3;
            adTimeTv.setText("跳过" + time + "s");
            mHandler.sendEmptyMessageDelayed(0, 1000);
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("url", "file:///android_asset/sun.gif");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        showAd(jsonObject);
        /**
         * 初始化应用数据,异步访问后端接口
         */
        //模拟访问网络延时
//        mDataHandler.sendEmptyMessageDelayed(0, 2000);
    }

    private void enterToMain() {
        if (mHandler != null && mHandler.hasMessages(0)) {
            mHandler.removeMessages(0);
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    //    public void getAd() {
//        /**
//         * 模拟异步获取广告数据
//         */
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("url","file:///android_asset/sun.gif");
//        Message message = new Message();
//        message.obj = jsonObject;
//        mAdverHandler.sendMessageDelayed(message,2000);
//    }
    private void showAd(JSONObject jsonObject) {
        adTimeTv.setVisibility(View.VISIBLE);
//        if(TextUtils.isEmpty(jsonObject.getString("url"))){
//            enterToMain();
//            }else{
        final WeakReference<ImageView> imageViewWeakReference2 = new WeakReference<>(adIv);
        ImageView target2 = imageViewWeakReference2.get();
        if (target2 != null) {
            try {
                Glide.with(this)
                        .load(jsonObject.getString("url"))
                        .into(target2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            time = 3;
            adTimeTv.setText("跳过" + time + "s");
            mHandler.sendEmptyMessageDelayed(0, 1000);
//        }


        }
    }

    @OnClick(R.id.ad_time_tv)
    public void onViewClicked() {
       enterToMain();
    }
}
