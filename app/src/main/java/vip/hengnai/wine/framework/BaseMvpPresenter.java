package vip.hengnai.wine.framework;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 *
 * @author hua
 * @date 2019/8/21
 */

public abstract class BaseMvpPresenter<V extends IMvpView> implements IMvpPresenter<V> {
    private  WeakReference<V> mView;

    public V view;


        private CompositeDisposable compositeDisposable;

    public void addDisposable( Observable<?> observable, DisposableObserver observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));

    }

    public void removeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    @Override
    public void attachView(V view) {
        mView = new WeakReference<>(view);
    }

    /**
     * 解除绑定
     */
    @Override
    public void detachView(boolean retainInstance) {
        if (mView != null) {
            mView.clear();
            mView = null;
            removeDisposable();
        }
    }

    @Override
    public V getView() {
        if (null == mView || null == mView.get()) {
            throw new NullPointerException("do not forget to call attachView the set view for this presenter");
        }
        return mView.get();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        if (null != compositeDisposable) {
            if (!compositeDisposable.isDisposed()) {
                compositeDisposable.dispose();
            }
        }
    }
}
