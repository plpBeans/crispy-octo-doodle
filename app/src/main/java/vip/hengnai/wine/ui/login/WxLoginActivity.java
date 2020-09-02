package vip.hengnai.wine.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.MobileCodeEntity;
import vip.hengnai.wine.entity.UserInfoEntity;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.util.glide.MyGlideModule;

/**
 * 微信登录
 *
 * @author Hugh
 */
public class WxLoginActivity extends BaseMvpAppCompatActivity<ILoginView, LoginPresenter> implements ILoginView {
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
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_WxLogin)
    Button btnWxLogin;
    @BindView(R.id.ll_body)
    LinearLayout llBody;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wchat);
        ButterKnife.bind(this);
          /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTitle.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTitle.setLayoutParams(layoutParams);
        MyGlideModule.loadImage(this,imgHeader,"",R.mipmap.icon_login_defualt);
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

    @OnClick({R.id.img_back, R.id.btn_login, R.id.btn_WxLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_login:
                startActivity(new Intent(WxLoginActivity.this,LoginActivity.class));
                finish();
                break;
            case R.id.btn_WxLogin:
                break;
        }
    }

    @Override
    public void getMobileCode(MobileCodeEntity mobileCodeEntity) {

    }

    @Override
    public void loginSuccess(UserInfoEntity userInfoEntity) {

    }
}
