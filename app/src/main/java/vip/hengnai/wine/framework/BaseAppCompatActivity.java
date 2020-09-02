package vip.hengnai.wine.framework;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;

import vip.hengnai.wine.util.StatusBarUtil;


/**
 *
 * @author hua
 * @date 2019/8/21
 */

public class BaseAppCompatActivity extends AppCompatActivity {
    private ProgressDialog mDialog;

    public interface OnDialogDismissListener {
        /**
         * 关闭载入框
         */
        void onDismiss();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersiveStatusBar(this,true);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
//            try {
//                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
//                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
//                field.setAccessible(true);
//                //改为透明
//                field.setInt(getWindow().getDecorView(), Color.TRANSPARENT);
//            } catch (Exception e) {}
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = this.getWindow();
//            /*状态栏透明*/
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = this.getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN );
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            /*设置状态栏颜色透明*/
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            WindowManager.LayoutParams lp = this.getWindow().getAttributes();
//            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
//            this.getWindow().setAttributes(lp);
//            View decorView = this.getWindow().getDecorView();
//            int systemUiVisibility = decorView.getSystemUiVisibility();
//            int flags =View.SYSTEM_UI_FLAG_HIDE_NAVIGATION//隐藏导航栏
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    |View.SYSTEM_UI_FLAG_IMMERSIVE;//隐藏状态栏
//            systemUiVisibility |= flags;
//            this.getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    protected String getStringExtra(String key) {
        Intent intent = getIntent();
        if (null != intent) {
            Bundle data = intent.getExtras();
            if (data != null) {
                return data.getString(key);
            }
        }
        return "";
    }

    protected <T extends Parcelable> T getParcelableExtra(String key) {
        Intent intent = getIntent();
        if (null != intent) {
            Bundle data = intent.getExtras();
            if (data != null) {
                return data.getParcelable(key);
            }
        }
        return null;
    }

    protected <T extends Parcelable> ArrayList<T> getParcelableExtraArrayList(String key) {
        Intent intent = getIntent();
        if (null != intent) {
            Bundle data = intent.getExtras();
            if (data != null) {
                return data.getParcelableArrayList(key);
            }
        }
        return null;
    }

    /**
     *
     * @param title
     * @param message
     * @param listener
     * @param isClick 点击外部能否取消dialog
     */
    public void showProgressDialog(@Nullable String title, @NonNull String message, @Nullable final OnDialogDismissListener listener, @NonNull boolean isClick) {
        if (null == mDialog) {
            mDialog = new ProgressDialog(this);
            mDialog.setCancelable(isClick);
            mDialog.setCanceledOnTouchOutside(isClick);

        }
        if (null != listener) {
            mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    listener.onDismiss();
                }
            });
        }

        if (!TextUtils.isEmpty(title)) {
            mDialog.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            mDialog.setMessage(message);
        }
        if(!this.mDialog.isShowing()&!this.isFinishing()) {

            this.mDialog.show();
        }

//        if (null == mDialog) {
//            mDialog = new AlertDialog.Builder(this, R.style.ActionSheetDialogStyle).create();
//            if(!this.mDialog.isShowing()&!this.isFinishing()) {
//
//                this.mDialog.show();
//            }
//            mDialog.setCanceledOnTouchOutside(isClick);
//            Window window = mDialog.getWindow();
//            //加载自定义的布局文件
//            window.setContentView(R.layout.alert_dialog_progress_view);
//
//            window.setGravity(Gravity.CENTER);
////            window.setLayout(dp2px(this,100), WindowManager.LayoutParams.WRAP_CONTENT);
////            WindowManager.LayoutParams wm = window.getAttributes();
////            // 对话框背景透明度
////            wm.alpha = 1.0f;
////            window.setAttributes(wm);
//        }
//        if (null != listener) {
//            mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                @Override
//                public void onDismiss(DialogInterface dialog) {
//                    listener.onDismiss();
//                }
//            });
//        }

    }

    public void dismissProgressDialog() {
        if (null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void showShortToast(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void setRefresh(final boolean refreshing, final SwipeRefreshLayout refreshLayout) {
        if (null == refreshLayout) {
            return;
        }
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        });
    }
    /**
     * 获取头部栏高度
     * @return
     */
    protected int getStatusBarHeight() {
        Resources resources = this.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);
//        Log.v("dbw", "Status height:" + height);
        return height;
    }
//    /**
//     * Get the Main Application component for dependency injection.
//     *
//     * @return {@link FApplicationComponent}
//     */
//    protected FApplicationComponent getApplicationComponent() {
//        Application application = getApplication();
//        if (!(application instanceof FApplication)) {
//            throw new IllegalStateException("Application 一定要继承 FApplication");
//        }
//        return ((FApplication) application).getApplicationComponent();
//    }


}
