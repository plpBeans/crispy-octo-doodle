package vip.hengnai.wine.ui.login;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.MobileCodeEntity;
import vip.hengnai.wine.entity.UserInfoEntity;
import vip.hengnai.wine.eventbus.LoginEvent;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.util.AuthUtil;
import vip.hengnai.wine.util.NotNull;

/**
 * 普通登录
 *
 * @author Hugh
 */
public class LoginActivity extends BaseMvpAppCompatActivity<ILoginView, LoginPresenter> implements ILoginView {


    @BindView(R.id.img_location)
    ImageView imgLocation;
    @BindView(R.id.tx_location)
    AppCompatTextView txLocation;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.img_header)
    ImageView imgHeader;
    @BindView(R.id.img_phone)
    ImageView imgPhone;
    @BindView(R.id.tx_phone)
    TextView txPhone;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.img_pwd)
    ImageView imgPwd;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.tx_yzm)
    TextView txYzm;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.checkbox)
    AppCompatCheckBox checkbox;
    @BindView(R.id.tx_treaty)
    TextView txTreaty;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.tx_other)
    TextView txOther;
    @BindView(R.id.img_weixin)
    ImageView imgWeixin;
    private LoginPresenter mPresenter;
    /**
     * 倒计时类
      */
    private TimeCount time;
    public static final int MIN_CLICK_TIME = 1000;
    private long lastTime = 0;
    private String getKey="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTitle.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTitle.setLayoutParams(layoutParams);
        initView();
    }
    private void initView() {
    }
    @Override
    protected LoginPresenter initPresenter() {
        return mPresenter = new LoginPresenter();
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

    @OnClick({R.id.img_back, R.id.checkbox,R.id.tx_yzm, R.id.tx_treaty, R.id.img_weixin,R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tx_yzm:
                if (!NotNull.isNotNull(etPhone.getText().toString().trim())) {
                    showShortToast("请输入手机号码");
                    return;
                } else if (!etPhone.getText().toString().trim().matches("[1][3456789]\\d{9}")) {
                    showShortToast("手机号码格式有误");
                    return;
                } else {
                    mPresenter.getMobileCode(etPhone.getText().toString().trim());
                }

                break;
            case R.id.checkbox:
                break;
            case R.id.tx_treaty:
                break;
            case R.id.img_weixin:
                break;
            case R.id.btn_login:
                if (!NotNull.isNotNull(etPhone.getText().toString().trim())) {
                    showShortToast("请输入手机号码");
                    return;
                } else if (!etPhone.getText().toString().trim().matches("[1][3456789]\\d{9}")) {
                    showShortToast("手机号码格式有误");
                    return;
                } else if(!NotNull.isNotNull(etYzm.getText().toString().trim())){
                    showShortToast("请输入验证码");
                    return;
                }else{
                    presenter.login(etPhone.getText().toString().trim(),getKey,etYzm.getText().toString().trim());
                }


                break;
        }
    }

    @Override
    public void getMobileCode(MobileCodeEntity mobileCodeEntity) {
        getKey=mobileCodeEntity.getKey();
        long cTime = Calendar.getInstance().getTimeInMillis();
        if (cTime - lastTime > MIN_CLICK_TIME) {
            lastTime = cTime;
            time = new TimeCount(60000, 1000);
            time.start();
        }
    }

    /**
     * 登录成功
     * @param userInfoEntity
     */
    @Override
    public void loginSuccess(UserInfoEntity userInfoEntity) {
        AuthUtil.getAuthUtil(this).setToken(userInfoEntity.getToken());
            showLongToast("登录成功");
        //判断是跳转录入手势还是验证手势login 为登录验证 change 为修改
//        if(AuthUtil.getAuthUtil(LoginActivity.this).getIsgesturelockKey()){
//            startActivity(new Intent(LoginActivity.this,GestureLockActivity.class).putExtra("type","login"));
//        }else{
//            startActivity(new Intent(LoginActivity.this,SetGestureLockActivity.class));
//        }
        EventBus.getDefault().post(new LoginEvent());
        finish();
    }

    /**
     * 倒计时类
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示

            txYzm.setClickable(false);
            txYzm.setText(millisUntilFinished / 1000 + "秒");
            txYzm.setGravity(Gravity.CENTER);

        }

        @Override
        public void onFinish() {

            // 计时完毕时触发
            txYzm.setGravity(Gravity.CENTER);
            txYzm.setText("重新获取");
            txYzm.setClickable(true);
        }
    }
}
