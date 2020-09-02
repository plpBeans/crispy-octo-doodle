package vip.hengnai.wine.util.nfc;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcV;
import android.provider.Settings;
import android.support.annotation.CallSuper;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import vip.hengnai.wine.BuildConfig;
import vip.hengnai.wine.R;
import vip.hengnai.wine.framework.IMvpPresenter;
import vip.hengnai.wine.framework.IMvpView;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;


/**
 *
 * @author hua
 * @date 2018/7/11
 */

public abstract class BaseNfcActivity<V extends IMvpView, P extends IMvpPresenter<V>> extends BaseMvpAppCompatActivity implements IMvpView {
    private NfcAdapter mNfcAdapter;
    private PendingIntent mNfcApi;
    private IntentFilter mNfcIntentFilter;
    private String[][] mTechLists = new String[][]{new String[]{IsoDep.class.getName()},
            new String[]{NfcV.class.getName()}, new String[]{Ndef.class.getName()},
            new String[]{NfcA.class.getName()}, new String[]{NfcB.class.getName()}};
    private NfcTools mNfcTools = new NfcTools();

    /**
     * 返回卡信息
     * @param nfcCardInfo
     */
    protected abstract void scanSuccess(String nfcCardInfo);

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
        initNfc();

    }

    @CallSuper
    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);
        //点了签到，签出才会处理数据
        if (null != intent && NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
            // 处理该intent
            processIntent(intent);
        }
    }

    /**
     * 引导开启NFC
     */
    private void guideToSwitchOnNfc() {
        new AlertDialog.Builder(this).setMessage(R.string.nfc_activity_dialog_message_enable_nfc)
                .setPositiveButton(R.string.nfc_activity_dialog_btn_enable_nfc, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    }
                })
                .setNegativeButton(R.string.nfc_activity_dialog_btn_no, null)
                .show();
    }

    private void initNfc() {
        // 初始化设备支持NFC功能
        if (null == mNfcAdapter) {
            mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        }
        // 判定设备是否支持NFC或启动NFC
        if (mNfcAdapter == null) {
//            showShortToast(getString(R.string.nfc_activity_divice_not_support_nfc));
            return;
        }
//        if (!mNfcAdapter.isEnabled()) {
//            guideToSwitchOnNfc();
//            return;
//        }
        if (null == mNfcApi || null == mNfcIntentFilter) {
            // 初始化PendingIntent，当有NFC设备连接上的时"，就交给当前Activity处理
            mNfcApi = PendingIntent.getActivity(this, 0, new Intent(this, getClass())
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
            // 新建IntentFilter，使用的是第二种的过滤机"
            mNfcIntentFilter = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        }

    }

    protected void startNfcListener() {
        if (null == mNfcAdapter) {
            showShortToast(getString(R.string.nfc_activity_divice_not_support_nfc));
            return;
        }
        if (!mNfcAdapter.isEnabled()) {
            guideToSwitchOnNfc();
            return;
        }
        // 监听NFC设备是否连接，如果连接就发pi
        mNfcAdapter.enableForegroundDispatch(this, mNfcApi,
                new IntentFilter[]{mNfcIntentFilter}, mTechLists);
    }

    protected void stopNfcListener() {
        if (null == mNfcAdapter || !mNfcAdapter.isEnabled()) {
            return;
        }
        // 停止监听NFC设备是否连接
        mNfcAdapter.disableForegroundDispatch(this);
    }

    private void processIntent(Intent intent) {
        if (mNfcAdapter == null) {
            showShortToast(getString(R.string.nfc_activity_divice_not_support_nfc));
            return;
        }

        // 取出封装在intent中的TAG
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        //= bytesToHexString(tagFromIntent.getId());
        //十进制 正向
        String nfcCardInfo =String.valueOf(getReversed(tagFromIntent.getId()));
        //十进制 正向
//        String nfcCardInfo =String.valueOf(getDec(tagFromIntent.getId()));

//        String nfcCardInfo =String.valueOf(getHex(tagFromIntent.getId()));
//        String nfcCardInfo =bytesToHexString(tagFromIntent.getId());


        if (BuildConfig.DEBUG) {
            Log.d("BaseNfcActivity", nfcCardInfo);
        }
        scanSuccess(nfcCardInfo);
    }

    /***
     * 十六进制正向
     * @param src
     * @return
     */
    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        //添加全局参数
        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (byte aSrc : src) {
            buffer[0] = Character.forDigit((aSrc >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(aSrc & 0x0F, 16);
            stringBuilder.append(buffer);
        }
        int value14 =14;
        if (stringBuilder.length() ==value14 ) {
            stringBuilder2.append(stringBuilder);
            return stringBuilder2.toString();
        } else {
            return stringBuilder.toString();
        }

    }

    /***
     *
     * @param bytes
     * 十进制正向
     * @return
     */
    private long getReversed(byte[] bytes) {
        long result = 0;
        long factor = 1;
        for (int i = bytes.length - 1; i >= 0; --i) {
            long value = bytes[i] & 0xffL;
            result += value * factor;
            factor *= 256L;
        }
        return result;
    }

    /***
     * 十六进制逆向
     * @param bytes
     * @return
     */
    private String getHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = bytes.length - 1; i >= 0; --i) {
            int b = bytes[i] & 0xff;
            if (b < 0x10) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(b));
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /***
     * 十进制逆向
     * @param bytes
     * @return
     */
    private long getDec(byte[] bytes) {
        long result = 0;
        long factor = 1;
        for (int i = 0; i < bytes.length; ++i) {
            long value = bytes[i] & 0xffL;
            result += value * factor;
            factor *= 256L;
        }
        return result;
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
        stopNfcListener();
    }

}
