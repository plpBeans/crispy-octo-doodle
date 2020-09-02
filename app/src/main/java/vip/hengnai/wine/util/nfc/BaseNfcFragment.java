package vip.hengnai.wine.util.nfc;

import android.content.Context;
import android.util.Log;

import vip.hengnai.wine.BuildConfig;
import vip.hengnai.wine.framework.IMvpPresenter;
import vip.hengnai.wine.framework.IMvpView;
import vip.hengnai.wine.framework.BaseMvpFragment;


/**
 * 需要配合{@link BaseNfcActivity}使用
 *
 * @author hua
 * @date 2017/3/5
 */

public abstract class BaseNfcFragment<V extends IMvpView, P extends IMvpPresenter<V>> extends BaseMvpFragment<V,P> {
    private static final String TAG = "BaseNfcFragment";
    private OnRequireNfcScan mOnRequireNfcScan;

    public void setOnRequireNfcScan(OnRequireNfcScan onRequireNfcScan) {
        mOnRequireNfcScan = onRequireNfcScan;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof BaseNfcActivity)) {
            if (BuildConfig.DEBUG) {
                Log.e(TAG, "make sure your activity extends BaseNfcActivity");
            }
        }
    }

    /**
     * 需要进行扫描时，调用此方法
     */
    protected void startScan() {
        if (null != mOnRequireNfcScan) {
            mOnRequireNfcScan.requireNfcScan(-1);
        } else if (BuildConfig.DEBUG) {
            Log.e(TAG, "do not forget to register OnRequireNfcScan in your activity");
        }
    }

    /**
     * 从Activity扫描成功后调用此方法
     *
     * @param nfcId
     */
    public abstract void scanSuccess(String nfcId);
}
