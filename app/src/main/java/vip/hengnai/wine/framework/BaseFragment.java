package vip.hengnai.wine.framework;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.widget.Toast;

import vip.hengnai.wine.R;


/**
 *
 * @author hua
 * @date 2019/8/21
 */

public class BaseFragment extends Fragment {
    private AlertDialog mDialog;

    public interface OnDialogDismissListener {
        /**
         * 关闭载入框
         */
        void onDismiss();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showShortToast(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog(@Nullable String title, @NonNull String message, @Nullable final BaseAppCompatActivity.OnDialogDismissListener listener, @NonNull boolean isClick) {
        if (null == mDialog) {
            mDialog = new ProgressDialog(getActivity());
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
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
//        if (null == mDialog) {
//            mDialog = new AlertDialog.Builder(getActivity(), R.style.ActionSheetDialogStyle).create();
//            if(!this.mDialog.isShowing()&!getActivity().isFinishing()) {
//
//                this.mDialog.show();
//            }
//            mDialog.setCanceledOnTouchOutside(false);
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

    public void setRefresh(final boolean refreshing, final SwipeRefreshLayout refreshLayout){
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
        Resources resources = getActivity().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
    }
//    /**
//     * Get the Main Application component for dependency injection.
//     *
//     * @return {@link FApplicationComponent}
//     */
//    protected FApplicationComponent getApplicationComponent() {
//        Application application = getActivity().getApplication();
//        if (!(application instanceof FApplication)) {
//            throw new IllegalStateException("Application 一定要继承 FApplication");
//        }
//        return ((FApplication) application).getApplicationComponent();
//    }
}
