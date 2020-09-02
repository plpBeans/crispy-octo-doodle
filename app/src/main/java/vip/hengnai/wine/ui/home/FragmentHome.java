package vip.hengnai.wine.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.bumptech.glide.Glide;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.hengnai.wine.R;
import vip.hengnai.wine.eventbus.MainEvent;
import vip.hengnai.wine.framework.BaseMvpFragment;
import vip.hengnai.wine.ui.home.message.MessageActivity;
import vip.hengnai.wine.ui.home.zxing.ZxingActivity;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.util.AuthUtil;
import vip.hengnai.wine.util.MyMapUtils;
import vip.hengnai.wine.util.StatusBarUtil;
import vip.hengnai.wine.view.NewBanner;

/**
 *
 *首页
 * @author Hugh
 */
public class FragmentHome extends BaseMvpFragment<IHomeView, HomePresenter> implements IHomeView {


    @BindView(R.id.banner)
    NewBanner banner;
    @BindView(R.id.img_location)
    ImageView imgLocation;
    @BindView(R.id.tx_location)
    public TextView txLocation;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.img_order)
    ImageView imgOrder;
    @BindView(R.id.rl_order)
    RelativeLayout rlOrder;
    @BindView(R.id.img_coupon)
    ImageView imgCoupon;
    @BindView(R.id.rl_coupon)
    RelativeLayout rlCoupon;
    @BindView(R.id.img_attestation)
    ImageView imgAttestation;
    @BindView(R.id.rl_attestation)
    RelativeLayout rlAttestation;
    @BindView(R.id.img_partner_one)
    ImageView imgPartnerOne;
    @BindView(R.id.img_partner_two)
    ImageView imgPartnerTwo;
    @BindView(R.id.rl_partner)
    RelativeLayout rlPartner;
    @BindView(R.id.img_bottom)
    ImageView imgBottom;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    @BindView(R.id.tx_orderInfo)
    TextView txOrderInfo;
    @BindView(R.id.tx_serviceTime)
    TextView txServiceTime;
    @BindView(R.id.ll_orderInfo)
    LinearLayout llOrderInfo;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.tx_message_count)
    TextView txMessageCount;
    @BindView(R.id.img_sao)
    ImageView imgSao;
    private HomePresenter homePresenter;
    private View view;
    List<String> urlList = new ArrayList<>();
    public String city="";
    public double longitude;
    public double latitude;
    public String poiName="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 优化View减少View的创建次数
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, null);
            ButterKnife.bind(this, view);
            EventBus.getDefault().register(this);

//            city= SellWineApplication.city;
//            longitude=SellWineApplication.longitude;
//            latitude=SellWineApplication.latitude;
//            poiName=SellWineApplication.poiName;
//            txLocation.setText(poiName);
            return view;
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    protected HomePresenter initPresenter() {
        return homePresenter = new HomePresenter();
    }

    /**
     * 每次fragment显示的时候在此方法调取定位
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {
            // 不在最前端界面显示

        } else {
            StatusBarUtil.setImmersiveStatusBar(getActivity(), false);
            MyMapUtils.startLocation();
        }
    }

    private void initView() {
        ViewTreeObserver vto = rlTitle.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rlTitle.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) llTitle.getLayoutParams();
                layoutParams1.height = rlTitle.getHeight() + getStatusBarHeight();
                llTitle.setLayoutParams(layoutParams1);
            }
        });

        StatusBarUtil.setImmersiveStatusBar(getActivity(), false);
        /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTitle.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTitle.setLayoutParams(layoutParams);
        imgBottom.setImageResource(R.mipmap.ic_home_bottom);

        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        //设置图片集合
        banner.setImages(urlList);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                showLongToast("点击了第" + position + "个");
                imgLocation.setImageResource(R.mipmap.ic_qishou);
                llOrderInfo.setVisibility(View.VISIBLE);
                txLocation.setVisibility(View.GONE);
                txServiceTime.setText("20:06");
                txOrderInfo.setText("骑手正在赶往商家");
            }
        });
        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MyMapUtils.startLocation();
                hideLoadingView();
            }
        });
        //自定义下来刷新SuperSwipeRefreshLayout
//        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.header_progress_view, null);
//        swiprefresh.setHeaderView(viewGroup);
//        swiprefresh.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swiprefresh.setRefreshing(false);
//            }
//
//            @Override
//            public void onPullDistance(int distance) {
//
//            }
//
//            @Override
//            public void onPullEnable(boolean enable) {
//
//            }
//        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLocation(AMapLocation location) {
        if(null!=location){
            if(0== location.getErrorCode()){
                city=location.getCity();
                longitude=location.getLongitude();
                latitude=location.getLatitude();
                txLocation.setText(location.getPoiName());
            }else{

                txLocation.setText("定位失败");
                if(!isHidden()){
                    showDialog();
                }

            }
        }else{
            txLocation.setText("定位失败");
            if(!isHidden()){
                showDialog();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void showLoadingView() {
        setRefresh(true,swiprefresh);
//        showProgressDialog(null, "正在加载", null);
    }

    @Override
    public void hideLoadingView() {
        setRefresh(false,swiprefresh);
//        dismissProgressDialog();
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showShortToast(message);
    }

    @Override
    public void forceToReLogin(String message) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.img_message,R.id.img_sao,R.id.img_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_message:
                if(AuthUtil.getAuthUtil(getActivity()).hasLoginIn()){
                    startActivity(new Intent(getActivity(), MessageActivity.class));
                }else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }

                break;
            case R.id.img_sao:
                startActivityForResult(new Intent(getActivity(), ZxingActivity.class),111);
                break;

            case R.id.img_order:
//                ((MainActivity)getActivity()).goMenu();
                EventBus.getDefault().post(new MainEvent().setIndex("1"));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if ( 111==requestCode) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    String id = "";
                    String date = "";
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        id = jsonObject.optString("jgid");
                        date = jsonObject.optString("date");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    getPresenter().CheckIn(id, date, customerId);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

    /**
     * 定位失败弹窗
     */
    public void showDialog() {
        final AlertDialog dlg = new AlertDialog.Builder(getActivity(), R.style.ActionSheetDialogStyle).create();
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.alert_dialog_location);
        window.setGravity(Gravity.CENTER);
        TextView textView = window.findViewById(R.id.dialog_text_title);
        textView.setText("哎呀，定位失败");
        window.findViewById(R.id.again_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMapUtils.startLocation();
                dlg.dismiss();
            }
        });
        window.findViewById(R.id.other_shop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dlg.dismiss();
            }
        });
    }
}
