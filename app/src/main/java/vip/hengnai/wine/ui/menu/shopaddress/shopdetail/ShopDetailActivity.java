package vip.hengnai.wine.ui.menu.shopaddress.shopdetail;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.PersonAddressEntity;
import vip.hengnai.wine.entity.ShopDetailEntity;
import vip.hengnai.wine.entity.ShopEntity;
import vip.hengnai.wine.eventbus.ShopEvent;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.ui.main.MainActivity;
import vip.hengnai.wine.ui.menu.shopaddress.IShopAndAddressView;
import vip.hengnai.wine.ui.menu.shopaddress.ShopAndAddressPresenter;
import vip.hengnai.wine.ui.menu.shopaddress.shopdetail.photoview.ShowPhotoActivity;
import vip.hengnai.wine.util.LocationAlert;
import vip.hengnai.wine.util.MapNaviUtils;
import vip.hengnai.wine.util.MyMapUtils;
import vip.hengnai.wine.util.NotNull;
import vip.hengnai.wine.util.StatusBarUtil;
import vip.hengnai.wine.util.glide.MyGlideModule;

/**
 * 商家详情
 *
 * @author Hugh
 */
public class ShopDetailActivity extends BaseMvpAppCompatActivity<IShopAndAddressView, ShopAndAddressPresenter> implements IShopAndAddressView {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.btn_go_shopping)
    Button btnGoShopping;
    @BindView(R.id.img_shop)
    ImageView imgShop;
    @BindView(R.id.rl_shop)
    RelativeLayout rlShop;
    @BindView(R.id.tx_pic_count)
    TextView txPicCount;
    @BindView(R.id.tx_shopName)
    TextView txShopName;
    @BindView(R.id.tx_shopTime)
    TextView txShopTime;
    @BindView(R.id.ll_shopInfo)
    LinearLayout llShopInfo;
    @BindView(R.id.tx_shopAddress)
    TextView txShopAddress;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.ll_shop_map)
    LinearLayout llShopMap;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ShopAndAddressPresenter shopAndAddressPresenter;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private UiSettings mUiSettings;
    private Marker marker, marker1;
    private ArrayList<String> urlList = new ArrayList<>();
    private LatLng centerPoint;
    private String distance;
    private int shopId;
    private String shopName;
    private double lat;
    private double lon;
    private AMapLocation aMapLocation;
    private ShopEntity shopEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        shopEntity=(ShopEntity) getIntent().getSerializableExtra("shop");
        shopId = shopEntity.getId();
        MyMapUtils.startLocation();
        StatusBarUtil.setImmersiveStatusBar(this, false);
         /*设置头部栏高度*/
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rlTitle.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTitle.setLayoutParams(layoutParams);

        // 此方法必须重写
        mapView.onCreate(savedInstanceState);


    }

    /**
     * 计算缩放比
     *
     * @param distance
     * @param curHeight
     * @return
     */
    private int getZoomTo(int distance, int curHeight) {
        if (distance / curHeight <= 50 / 110) {
            return 17;
        } else if (distance / curHeight <= 100 / 110) {
            return 16;
        } else if (distance / curHeight <= 200 / 110) {
            return 15;
        } else if (distance / curHeight <= 200 / 50) {
            return 14;
        } else if (distance / curHeight <= 300 / 60) {
            return 13;
        } else if (distance / curHeight <= 1000 / 60) {
            return 12;
        } else if (distance / curHeight <= 2000 / 60) {
            return 11;
        } else if (distance / curHeight <= 5000 / 80) {
            return 10;
        } else if (distance / curHeight <= 10000 / 80) {
            return 9;
        } else if (distance / curHeight <= 20000 / 80) {
            return 8;
        } else if (distance / curHeight <= 50000 / 110) {
            return 7;
        } else if (distance / curHeight <= 100000 / 110) {
            return 6;
        } else if (distance / curHeight <= 100000 / 50) {
            return 5;
        } else if (distance / curHeight <= 200000 / 50) {
            return 4;
        } else if (distance / curHeight <= 500000 / 80) {
            return 3;
        } else if (distance / curHeight <= 1000000 / 60) {
            return 2;
        } else {
            return 2;
        }
    }

    /**
     * 配置地图
     */
    private void initMapView(Location location) {
        //初始化地图控制器对象,显示地图
        if (aMap == null) {
            aMap = mapView.getMap();
            mUiSettings = aMap.getUiSettings();
        }
        //隐藏放大按钮
        mUiSettings.setZoomControlsEnabled(false);
        //设置地图不能手势滑动
        mUiSettings.setScrollGesturesEnabled(false);
        //不能放大缩小
        mUiSettings.setZoomGesturesEnabled(false);
        //设置地图不能倾斜
        mUiSettings.setTiltGesturesEnabled(false);
        //设置地图不能旋转
        mUiSettings.setRotateGesturesEnabled(false);
//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle = new MyLocationStyle();
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.icon_location);
        myLocationStyle.myLocationIcon(bitmapDescriptor);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        // 设置圆形的边框颜色
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));

        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
//        //连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
////        设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        myLocationStyle.interval(2000);
////        设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
        //设置默认定位按钮是否显示，非必需设置。
//        aMap.getUiSettings().setMyLocationButtonEnabled(false);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(false);
        marker1 = aMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_location)).setFlat(true));
        //设置锚点使图片中心点为定位
        marker1.setAnchor((float) 0.5, (float) 0.5);
        marker1.setClickable(false);
        marker = aMap.addMarker(new MarkerOptions().position(centerPoint).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_shop_location)).setFlat(true));

        //设置锚点使图片中心点为定位
        marker1.setAnchor((float) 0.5, (float) 0.5);
        //高德计算
//        distance=(int)AMapUtils.calculateLineDistance(marker1.getPosition(),marker.getPosition());

        // 设置  自定义的InfoWindow 适配器
        aMap.setInfoWindowAdapter(infoWindowAdapter);
//         显示 infowindow
        marker.showInfoWindow();
//        绑定信息窗点击事件
        aMap.setOnInfoWindowClickListener(onInfoWindowClickListener);
        Log.e("测量高度", "测试");


        mapView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mapView.getViewTreeObserver().removeOnPreDrawListener(this);

                int height = mapView.getMeasuredHeight();
                Log.e("缩放比例", getZoomTo(Integer.parseInt(distance.split("m")[0]), height) + "");
                aMap.moveCamera(CameraUpdateFactory.zoomTo(getZoomTo(Integer.parseInt(distance.split("m")[0]), height)));
                aMap.animateCamera(CameraUpdateFactory.newLatLng(centerPoint));
                return true;
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected ShopAndAddressPresenter initPresenter() {
        return shopAndAddressPresenter = new ShopAndAddressPresenter();
    }

    @Override
    public void showDatas(List<ShopEntity> datas) {

    }

    @Override
    public void appendDatas(List<ShopEntity> datas) {

    }

    @Override
    public void showLoadingMore() {

    }

    @Override
    public void noMoreData() {

    }

    @Override
    public void showLoadingView() {
        showProgressDialog(null, getString(R.string.loading), null, true);
    }

    @Override
    public void hideLoadingView() {
        dismissProgressDialog();
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showShortToast(message);
    }

    @Override
    public void forceToReLogin(String message) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void getMyShopAddressSuccess(List<PersonAddressEntity> personAddressEntity) {

    }

    /**
     * 获取店铺信息
     *
     * @param shopDetailEntity
     */
    @Override
    public void getShopDetail(ShopDetailEntity shopDetailEntity) {
        //图片
        if (NotNull.isNotNull(shopDetailEntity.getImages())) {
            urlList.addAll(shopDetailEntity.getImages());
            MyGlideModule.loadImage(this, imgShop, shopDetailEntity.getImages().get(0), 0);
            txPicCount.setText(String.valueOf(shopDetailEntity.getImages().size()));
        }
        shopName = shopDetailEntity.getTitle();
        distance = shopDetailEntity.getDistance();
        txShopName.setText(shopName);
        //营业时间
        if (NotNull.isNotNull(shopDetailEntity.getHours())) {
            String hour = "";
            for (int i = 0; i < shopDetailEntity.getHours().size(); i++) {
                if(0!=i){
                    hour += "/n"+shopDetailEntity.getHours().get(i);
                }else{
                    hour += shopDetailEntity.getHours().get(i);
                }

            }
            txShopTime.setText("营业时间:" + hour);
        }
        //店铺特色
        if (NotNull.isNotNull(shopDetailEntity.getTags())) {
//            String tags = "";
//            for (int i = 0; i < shopDetailEntity.getTags().size(); i++) {
//                tags += shopDetailEntity.getTags().get(i);
//            }
//            txShopIntroduce.setText(tags);
            setTraitAdapter(shopDetailEntity.getTags());
        }
        txShopAddress.setText("门店地址:" + shopDetailEntity.getAddress());
        String geo[] = shopDetailEntity.getGeo().split(",");
        lat = Double.parseDouble(geo[1]);
        lon = Double.parseDouble(geo[0]);
        centerPoint = new LatLng(lat, lon);
        initMapView(aMapLocation);
    }
    /**
     * 店铺特性
     */
    private void setTraitAdapter(List<String> list) {
        List<String> traitList = new ArrayList<>();
        traitList.add("干净");
        recyclerview.setLayoutManager(new GridLayoutManager(this, traitList.size(), LinearLayoutManager.VERTICAL, false));

        MultiTypeAdapter mPatientsAdapter = new MultiTypeAdapter();

        ShopTraitProvider shopTraitProvider = new ShopTraitProvider(this);

        mPatientsAdapter.register(String.class, shopTraitProvider);

        mPatientsAdapter.setItems(traitList);
        recyclerview.setAdapter(mPatientsAdapter);
    }

    @OnClick({R.id.img_back, R.id.btn_go_shopping, R.id.rl_shop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rl_shop:
                Intent intent1 = new Intent(ShopDetailActivity.this, ShowPhotoActivity.class);
                intent1.putStringArrayListExtra("imageurls", urlList);
                intent1.putExtra("currentItem", 0);
                startActivity(intent1);
                break;
            case R.id.btn_go_shopping:
//                Intent intent = new Intent(ShopDetailActivity.this, MainActivity.class);
//                startActivity(intent);
                EventBus.getDefault().post(new ShopEvent(shopEntity));
                finish();
                break;
        }
    }

    /**
     * 个性化定制的信息窗口视图的类
     * 如果要定制化渲染这个信息窗口，需要重载getInfoWindow(Marker)方法。
     * 如果只是需要替换信息窗口的内容，则需要重载getInfoContents(Marker)方法。
     */
    AMap.InfoWindowAdapter infoWindowAdapter = new AMap.InfoWindowAdapter() {

        // 个性化Marker的InfoWindow 视图
        // 如果这个方法返回null，则将会使用默认的信息窗口风格，内容将会调用getInfoContents(Marker)方法获取
        @Override
        public View getInfoWindow(Marker marker) {

            return getInfoWindowView(marker);
        }

        // 这个方法只有在getInfoWindow(Marker)返回null 时才会被调用
        // 定制化的view 做这个信息窗口的内容，如果返回null 将以默认内容渲染
        @Override
        public View getInfoContents(Marker marker) {

            return null;
        }
    };

    /**
     * 自定义View并且绑定数据方法
     *
     * @param marker 点击的Marker对象
     * @return 返回自定义窗口的视图
     */
    private View getInfoWindowView(Marker marker) {
        View infoWindow = getLayoutInflater().inflate(
                R.layout.layout_amap_window, null);
        TextView tx_window_name = (TextView) infoWindow.findViewById(R.id.tx_window_name);
        TextView tx_window_distance = (TextView) infoWindow.findViewById(R.id.tx_window_distance);
        tx_window_distance.setText(distance );
        tx_window_name.setText(shopName);
        return infoWindow;
    }

    AMap.OnInfoWindowClickListener onInfoWindowClickListener = new AMap.OnInfoWindowClickListener() {

        @Override
        public void onInfoWindowClick(Marker arg0) {
            if (MapNaviUtils.isGdMapInstalled()) {
                MapNaviUtils.openGaoDeNavi(ShopDetailActivity.this, 0, 0, null, lat, lon, shopName);
//                            MapUtils.openMap(mContext,"com.autonavi.minimap",new LatLng(31.33260711060764,121.54777721524306,"CCB"));
            } else {
                showLongToast("您还未安装高德地图！");

            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLocation(AMapLocation location) {
        if (null != location) {
            if (0 == location.getErrorCode()) {
                Log.e("定位", "定位");
                aMapLocation = location;
                presenter.getShop(shopId);

            } else {
                LocationAlert.showDialog(ShopDetailActivity.this);

            }
        } else {
            LocationAlert.showDialog(ShopDetailActivity.this);
        }
    }
}
