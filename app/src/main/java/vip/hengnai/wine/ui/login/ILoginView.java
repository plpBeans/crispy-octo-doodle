package vip.hengnai.wine.ui.login;

import vip.hengnai.wine.entity.MobileCodeEntity;
import vip.hengnai.wine.entity.UserInfoEntity;
import vip.hengnai.wine.framework.IMvpView;

/**
 * AUTHOR：dell
 * TIME:2019/8/8 2019/8/8
 * DESCRIPTION:IMineView
 * IMineView——Activity里面要实现的方法在这里面定义
 * @author Hugh
 */
public interface ILoginView extends IMvpView {
    /**
     * 获取验证码
     * @param mobileCodeEntity
     */
    void getMobileCode(MobileCodeEntity mobileCodeEntity);
    /**
     * 登录成功
     * @param userInfoEntity
     */
    void loginSuccess(UserInfoEntity userInfoEntity);
}
