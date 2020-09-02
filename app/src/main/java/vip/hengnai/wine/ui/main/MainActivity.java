package vip.hengnai.wine.ui.main;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.eventbus.MainEvent;
import vip.hengnai.wine.framework.BaseAppCompatActivity;
import vip.hengnai.wine.push.ExampleUtil;
import vip.hengnai.wine.ui.cart.FragmentCart;
import vip.hengnai.wine.ui.home.FragmentHome;
import vip.hengnai.wine.ui.order.FragmentOrder;
import vip.hengnai.wine.ui.mine.FragmentMine;
import vip.hengnai.wine.ui.menu.FragmentMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.hengnai.wine.util.MyMapUtils;


/**
 * @author hua
 */
public class MainActivity extends BaseAppCompatActivity {

    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.tv_menu)
    TextView tvMenu;
    @BindView(R.id.ll_menu)
    public LinearLayout llMenu;
    @BindView(R.id.iv_order)
    ImageView ivOrder;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.ll_order)
    public LinearLayout llOrder;
    @BindView(R.id.iv_shopping_cart)
    ImageView ivShoppingCart;
    @BindView(R.id.tv_shopping_cart)
    TextView tvShoppingCart;
    @BindView(R.id.ll_shopping_cart)
    public LinearLayout llShoppingCart;
    @BindView(R.id.iv_mine)
    ImageView ivMine;
    @BindView(R.id.tv_mine)
    TextView tvMine;
    @BindView(R.id.ll_mine)
    public LinearLayout llMine;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    private Fragment mCurrent;
    /**
     * 布局管理器
     */
    private FragmentManager fManager;
    private FragmentCart fragmentShoppingCart;
    private FragmentOrder fragmentOrder;
    private FragmentMenu fragmentMenu;
    private FragmentHome fragmentHome;
    private FragmentMine fragmentMine;
    /**
     *  6.不再提示权限时的展示对话框
     */
    android.support.v7.app.AlertDialog mPermissionDialog;
    /**
     * 、首先声明一个数组permissions，将所有需要申请的权限都放在里面
     */
    String[] permissions = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
           };
    List<String> mPermissionList = new ArrayList<>();
    /**
     *  权限请求码
     */
    private final int mRequestCode = 100;
    /**
     *   如果勾选了不再询问
     */
    private static final int NOT_NOTICE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            initPermission();
        }else{

        initViews();
        }

    }
    /**
     *   4、权限判断和申请
     */
    private void initPermission() {
        mPermissionList.clear();//清空已经允许的没有通过的权限
        //逐个判断是否还有未通过的权限
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) !=
                    PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限到mPermissionList中
            }
        }
        //申请权限
        if (mPermissionList.size() > 0) {
            //有权限没有通过，需要申请
            ActivityCompat.requestPermissions(this, permissions, mRequestCode);
        } else {
            initViews();
//            startLocation();
        }
    }
    /**
     * 权限申请返回结果
     *
     * @param requestCode  请求码
     * @param permissions  权限数组
     * @param grantResults 申请结果数组，里面都是int类型的数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //有权限没有通过
        boolean hasPermissionDismiss = false;
        if (mRequestCode==requestCode){
            for (int i=0;i<grantResults.length;i++){
                if (grantResults[i]==-1){
                    hasPermissionDismiss=true;
                    break;
                }
            }
        }
        //如果有没有被允许的权限
        if (hasPermissionDismiss){
            showPermissionDialog();
        }else {
            initViews();
//            startLocation();
        }
    }

    private void showPermissionDialog() {
        if (mPermissionDialog == null) {
            mPermissionDialog = new android.support.v7.app.AlertDialog.Builder(this)
                    .setMessage("已禁用权限，请手动授予权限,才能继续使用哦")
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            cancelPermissionDialog();

                            Uri packageURI = Uri.parse("package:" + getPackageName());
                            Intent intent = new Intent(Settings.
                                    ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                            startActivityForResult(intent,NOT_NOTICE);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //关闭页面或者做其他操作
                            cancelPermissionDialog();
                            MainActivity.this.finish();
                        }
                    })
                    .create();
        }
        mPermissionDialog.show();
    }
    private void cancelPermissionDialog() {
        mPermissionDialog.cancel();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==NOT_NOTICE){
            initPermission();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //接收到推送进入某个fragment
//        onClick(llMenuHome);
    }

    private void initViews() {
        EventBus.getDefault().register(this);
        //初始化定位
//     initLocation();
        MyMapUtils.startLocation();
        //初始化极光需要的广播
        registerMessageReceiver();
        // 得到fragment 管理器，Activity 基类里面定义的
        fManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fManager.beginTransaction();
        ivHome.setImageResource(R.mipmap.ic_shouye_down);
        tvHome.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_choose));
        fragmentHome = new FragmentHome();
        fragmentTransaction.replace(R.id.content, fragmentHome);
        fragmentTransaction.commit();
//        startLocation();

    }


    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }

    @OnClick({R.id.ll_home, R.id.ll_menu, R.id.ll_order, R.id.ll_shopping_cart,R.id.ll_mine})
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();
        switch (v.getId()) {
            case R.id.ll_home:

                ivHome.setImageResource(R.mipmap.ic_shouye_down);
                tvHome.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_choose));
                ivMenu.setImageResource(R.mipmap.ic_caidan);
                tvMenu.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivOrder.setImageResource(R.mipmap.ic_dingdan);
                tvOrder.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivShoppingCart.setImageResource(R.mipmap.ic_gouwuche);
                tvShoppingCart.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivMine.setImageResource(R.mipmap.ic_wode);
                tvMine.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                // 隐藏其他fragment
                hideFragment(fragmentMine, fragmentTransaction);
                hideFragment(fragmentMenu, fragmentTransaction);
                hideFragment(fragmentShoppingCart, fragmentTransaction);
                hideFragment(fragmentOrder,fragmentTransaction);
                if (fragmentHome == null) {
                    //添加一个新的fragment
                    fragmentHome = new FragmentHome();
                    fragmentTransaction.add(R.id.content, fragmentHome);
                } else {
                    // 显示当前fragment
                    mCurrent = fragmentHome;
                    fragmentTransaction.show(fragmentHome);
                }
                break;
            case R.id.ll_menu:
                Log.e("执行","点击");
                ivMenu.setImageResource(R.mipmap.ic_caidan_down);
                tvMenu.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_choose));
                ivOrder.setImageResource(R.mipmap.ic_dingdan);
                tvOrder.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivHome.setImageResource(R.mipmap.ic_shouye);
                tvHome.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivShoppingCart.setImageResource(R.mipmap.ic_gouwuche);
                tvShoppingCart.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivMine.setImageResource(R.mipmap.ic_wode);
                tvMine.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                // 隐藏其他fragment
                hideFragment(fragmentHome, fragmentTransaction);
                hideFragment(fragmentMine, fragmentTransaction);
                hideFragment(fragmentShoppingCart, fragmentTransaction);
                hideFragment(fragmentOrder,fragmentTransaction);
                if (fragmentMenu == null) {
                    //添加一个新的fragment
                    fragmentMenu = new FragmentMenu();
                    fragmentTransaction.add(R.id.content, fragmentMenu);
                } else {
                    // 显示当前fragment
                    mCurrent = fragmentMenu;
                    fragmentTransaction.show(fragmentMenu);
                }
                break;
            case R.id.ll_order:
                ivOrder.setImageResource(R.mipmap.ic_dingdan_down);
                tvOrder.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_choose));
                ivMenu.setImageResource(R.mipmap.ic_caidan);
                tvMenu.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivHome.setImageResource(R.mipmap.ic_shouye);
                tvHome.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivShoppingCart.setImageResource(R.mipmap.ic_gouwuche);
                tvShoppingCart.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivMine.setImageResource(R.mipmap.ic_wode);
                tvMine.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                // 隐藏其他fragment
                hideFragment(fragmentMine, fragmentTransaction);
                hideFragment(fragmentShoppingCart, fragmentTransaction);
                hideFragment(fragmentHome, fragmentTransaction);
                hideFragment(fragmentMenu,fragmentTransaction);
                if (fragmentOrder == null) {
                    //添加一个新的fragment
                    fragmentOrder = new FragmentOrder();
                    fragmentTransaction.add(R.id.content, fragmentOrder);
                } else {
                    // 显示当前fragment
                    mCurrent = fragmentOrder;
                    fragmentTransaction.show(fragmentOrder);
                }
                break;
            case R.id.ll_shopping_cart:
                ivOrder.setImageResource(R.mipmap.ic_dingdan);
                tvOrder.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivMenu.setImageResource(R.mipmap.ic_caidan);
                tvMenu.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivHome.setImageResource(R.mipmap.ic_shouye);
                tvHome.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivShoppingCart.setImageResource(R.mipmap.ic_gouwuche_down);
                tvShoppingCart.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_choose));
                ivMine.setImageResource(R.mipmap.ic_wode);
                tvMine.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                // 隐藏其他fragment
                hideFragment(fragmentHome, fragmentTransaction);
                hideFragment(fragmentMenu, fragmentTransaction);
                hideFragment(fragmentMine, fragmentTransaction);
                hideFragment(fragmentOrder,fragmentTransaction);
                if (fragmentShoppingCart == null) {
                    //添加一个新的fragment
                    fragmentShoppingCart = new FragmentCart();
                    fragmentTransaction.add(R.id.content, fragmentShoppingCart);
                } else {
                    // 显示当前fragment
                    mCurrent = fragmentShoppingCart;
                    fragmentTransaction.show(fragmentShoppingCart);
                }
                break;
            case R.id.ll_mine:
                ivOrder.setImageResource(R.mipmap.ic_dingdan);
                tvOrder.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivMenu.setImageResource(R.mipmap.ic_caidan);
                tvMenu.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivHome.setImageResource(R.mipmap.ic_shouye);
                tvHome.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivShoppingCart.setImageResource(R.mipmap.ic_gouwuche);
                tvShoppingCart.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_no_choose));
                ivMine.setImageResource(R.mipmap.ic_wode_down);
                tvMine.setTextColor(ContextCompat.getColor(this, R.color.wonder_bottom_choose));
//                // 隐藏其他fragment
                hideFragment(fragmentShoppingCart, fragmentTransaction);
                hideFragment(fragmentMenu, fragmentTransaction);
                hideFragment(fragmentHome, fragmentTransaction);
                hideFragment(fragmentOrder,fragmentTransaction);
                if (fragmentMine == null) {
                    //添加一个新的fragment
                    fragmentMine = new FragmentMine();
                    fragmentTransaction.add(R.id.content, fragmentMine);
                } else {
                    // 显示当前fragment
                    mCurrent = fragmentMine;
                    fragmentTransaction.show(fragmentMine);
                }
                break;
            default:
                break;
        }

        fragmentTransaction.commitAllowingStateLoss();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getGo(MainEvent mainEvent) {
        if(Constants.VALUE_1.equals(mainEvent.getIndex())){
            /**
             * 跳转的菜单页面
             */
            onClick(llMenu);
        }else if(Constants.VALUE_3.equals(mainEvent.getIndex())){
            /**
             * 跳转的购物车页面
             */
            onClick(llShoppingCart);
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        MyMapUtils.destroyLocation();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);

    }
    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "vip.hengnai.wine.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    /**
     * 注册极光推送广播
     */
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    /**
     * 极光推送内部消息广播接收器
     */
    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                        showLongToast("接收到消息");
                    }

                }
            } catch (Exception e){
            }
        }
    }
}
