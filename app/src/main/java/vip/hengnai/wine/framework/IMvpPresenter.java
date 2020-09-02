package vip.hengnai.wine.framework;

/**
 *
 * @author hua
 * @date 2019/8/21
 */

public interface IMvpPresenter<V extends IMvpView> {
    /**
     * 绑定
     * @param view
     */
    void attachView(V view);

    /**
     * 解绑
     * @param retainInstance
     */
    void detachView(boolean retainInstance);

    /**
     * 返回对象
     * @return
     */
    V getView();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();
}
