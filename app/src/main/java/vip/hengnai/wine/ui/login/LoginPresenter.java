package vip.hengnai.wine.ui.login;

import android.support.annotation.NonNull;

import vip.hengnai.wine.entity.MobileCodeEntity;
import vip.hengnai.wine.entity.UserInfoEntity;
import vip.hengnai.wine.framework.IMvpView;
import vip.hengnai.wine.framework.BaseMvpPresenter;
import vip.hengnai.wine.framework.base.BaseCommonErrorSubscriber;
import vip.hengnai.wine.framework.model.LoginModel;

import okhttp3.RequestBody;


/**
 * AUTHOR：dell
 * TIME:2019/8/8 2019/8/8
 * DESCRIPTION:LoginPresenter
 * @author Hugh
 */
public class LoginPresenter extends BaseMvpPresenter<ILoginView> {

    private LoginModel loginModel;

    public LoginPresenter() {
        loginModel = new LoginModel();
    }


    /**
     * 获取验证码
     * @param mobile
     */
    public void getMobileCode(String mobile){

        addDisposable(loginModel.getMobileCode(mobile),new BaseCommonErrorSubscriber<MobileCodeEntity>(){

            @Override
            public void onNext(MobileCodeEntity mobileCodeEntity) {
                getView().getMobileCode(mobileCodeEntity);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }

        });
    }

    /**
     * 短信登录
     * @param mobile
     */
    public void login(String mobile,String key,String code){

        addDisposable(loginModel.login(mobile,key,code),new BaseCommonErrorSubscriber<UserInfoEntity>(){

            @Override
            public void onNext(UserInfoEntity userInfoEntity) {
                getView().loginSuccess(userInfoEntity);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }

        });
    }
}
