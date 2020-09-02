package vip.hengnai.wine.framework;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
/**
 *
 * @author hua
 * @date 2019/8/21
 */

public abstract class BaseMvpAppCompatActivity<V extends IMvpView, P extends IMvpPresenter<V>> extends BaseAppCompatActivity implements IMvpView {
    public P presenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = initPresenter();
        //把所有继承此类的Activity都绑定到这里了，这样View就和Present联系起来了。
        presenter.attachView((V) this);

    }

    /**
     * 初始化
     * @return
     */
    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(isRetainingInstance());

    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }

    /**
     * 界面销毁后是否继续presenter中的操作(比如点赞的操作，及时界面销毁了，理论上还是要继续完成请求)
     * 如果想要继续操作，则override本方法，返回true
     *
     * @return
     */
    public boolean isRetainingInstance() {
        return false;
    }
}
