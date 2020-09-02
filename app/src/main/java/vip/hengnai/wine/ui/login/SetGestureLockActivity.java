package vip.hengnai.wine.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.MobileCodeEntity;
import vip.hengnai.wine.entity.UserInfoEntity;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.util.AuthUtil;
import vip.hengnai.wine.util.gesture.GestureLockDisplayView;
import vip.hengnai.wine.util.gesture.GestureLockLayout;
import vip.hengnai.wine.util.gesture.GestureUtils;

/**
 * 设置手势密码界面
 *
 * @author Hugh
 */
public class SetGestureLockActivity  extends BaseMvpAppCompatActivity<ILoginView, LoginPresenter> implements ILoginView {
    @BindView(R.id.display_view)
    GestureLockDisplayView mLockDisplayView;
    @BindView(R.id.setting_hint)
    TextView mSettingHintText;
    @BindView(R.id.gesture_view)
    GestureLockLayout mGestureLockLayout;
    @BindView(R.id.reSet)
    TextView reSet;
    @BindView(R.id.hintTV)
    TextView hintTV;
    private Animation animation;
    private Context mContext;
    private LoginPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_gesture_lock);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected LoginPresenter initPresenter() {
        return mPresenter = new LoginPresenter();
    }

    private void initView() {
        mContext = this;
        //设置提示view 每行每列点的个数
//        mLockDisplayView.setDotCount(3);
//        //设置提示view 选中状态的颜色
//        mLockDisplayView.setDotSelectedColor(ContextCompat.getColor(this, R.color.choose));
//        //设置提示view 非选中状态的颜色
//        mLockDisplayView.setDotUnSelectedColor(ContextCompat.getColor(this, R.color.no_choose));
        //设置手势解锁view 每行每列点的个数
        mGestureLockLayout.setDotCount(3);
        //设置手势解锁view 最少连接数
        mGestureLockLayout.setMinCount(4);
        //设置手势解锁view 模式为重置密码模式
        mGestureLockLayout.setMode(GestureLockLayout.RESET_MODE);

        //初始化动画
        animation = AnimationUtils.loadAnimation(this, R.anim.shake);
        initEvents();
    }

    private void initEvents() {

        mGestureLockLayout.setOnLockResetListener(new GestureLockLayout.OnLockResetListener() {
            @Override
            public void onConnectCountUnmatched(int connectCount, int minCount) {
                //连接数小于最小连接数时调用
                mSettingHintText.setText("最少连接" + minCount + "个点");
                resetGesture();
            }

            @Override
            public void onFirstPasswordFinished(List<Integer> answerList) {
                //第一次绘制手势成功时调用
                Log.e("TAG", "第一次密码=" + answerList);
                mSettingHintText.setText("确认解锁图案");
                //将答案设置给提示view
//                mLockDisplayView.setAnswer(answerList);
                //重置
                resetGesture();
            }

            @Override
            public void onSetPasswordFinished(boolean isMatched, List<Integer> answerList) {
                //第二次密码绘制成功时调用
                Log.e("TAG", "第二次密码=" + answerList.toString());
                if (isMatched) {
                    //两次答案一致，保存
//                    PreferenceUtils.commitString(Constants.GESTURELOCK_KEY, answerList.toString());
                    //将密码存储
                    AuthUtil.getAuthUtil(SetGestureLockActivity.this).setGesturelockKey(answerList.toString());
                    //将按钮设置为true
                    AuthUtil.getAuthUtil(SetGestureLockActivity.this).setIsgesturelockKey(true);
//                    setResult(RESULT_OK);
                    showLongToast("设置成功");
//                    finish();
                } else {
                    hintTV.setVisibility(View.VISIBLE);
                    GestureUtils.setVibrate(mContext);
                    hintTV.startAnimation(animation);
                    mGestureLockLayout.startAnimation(animation);
                    resetGesture();
                }
            }
        });
    }

    /**
     * 重置手势布局（只是布局）
     */
    private void resetGesture() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mGestureLockLayout.resetGesture();
            }
        }, 300);
    }

    /**
     * 重置手势布局（布局加逻辑）
     */
    @OnClick(R.id.reSet)
    public void onViewClicked() {
        mGestureLockLayout.setOnLockResetListener(null);
        mSettingHintText.setText("绘制解锁图案");
//        mLockDisplayView.setAnswer(new ArrayList<Integer>());
        mGestureLockLayout.resetGesture();
        mGestureLockLayout.setMode(GestureLockLayout.RESET_MODE);
        hintTV.setVisibility(View.INVISIBLE);
        initEvents();
    }



    @Override
    public void showLoadingView() {
        showProgressDialog(null, "正在加载", null, true);
    }

    @Override
    public void hideLoadingView() {
        dismissProgressDialog();
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showShortToast(message);
    }

    @Override
    public void forceToReLogin(String message) {

    }

    @Override
    public void getMobileCode(MobileCodeEntity mobileCodeEntity) {

    }

    @Override
    public void loginSuccess(UserInfoEntity userInfoEntity) {

    }
}