package vip.hengnai.wine.framework;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 *
 * @author hua
 * @date 2019/8/21
 */

public interface IMvpView {
    /**
     * 上下文
     * @return
     */
    Context context();

    /**
     * 显示加载框
     */
    void showLoadingView();

    /**
     * 关闭加载框
     */
    void hideLoadingView();

    /**
     * toast内容
     * @param message
     */
    void showErrorMessage(@NonNull String message);

    /**
     * 返回登录
     * @param message
     */
    void forceToReLogin(String message);
}
