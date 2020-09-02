package vip.hengnai.wine.ui.menu.shopaddress;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.SellWineApplication;
import vip.hengnai.wine.entity.PersonAddressEntity;
import vip.hengnai.wine.entity.PoiAddressBean;
import vip.hengnai.wine.entity.ShopDetailEntity;
import vip.hengnai.wine.entity.ShopEntity;
import vip.hengnai.wine.eventbus.AddressEvent;
import vip.hengnai.wine.eventbus.ShopEvent;
import vip.hengnai.wine.eventbus.TakeAwayEvent;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.menu.personaddress.ModifyAddressActivity;
import vip.hengnai.wine.ui.menu.personaddress.PersonAddressActivity;
import vip.hengnai.wine.ui.menu.personaddress.choosedetailaddress.PoiKeywordSearchAdapter;
import vip.hengnai.wine.ui.menu.shopaddress.shopdetail.ShopDetailActivity;
import vip.hengnai.wine.util.MyMapUtils;
import vip.hengnai.wine.util.NotNull;
import vip.hengnai.wine.util.ToastUtil;
import vip.hengnai.wine.util.rvwrapper.EmptyWrapper;
import vip.hengnai.wine.util.rvwrapper.LoadMoreWrapper;

/**
 * 地址选择-门店自取和送货上门
 *
 * @author Hugh
 */
public class ShopAndAddressActivity extends BaseMvpAppCompatActivity<IShopAndAddressView, ShopAndAddressPresenter> implements IShopAndAddressView, PoiSearch.OnPoiSearchListener {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tx_shop)
    AppCompatTextView txShop;
    @BindView(R.id.tx_outCall)
    AppCompatTextView txOutCall;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tx_city)
    AppCompatTextView txCity;
    @BindView(R.id.et_address)
    AppCompatEditText etAddress;
    @BindView(R.id.recyclerView_address)
    RecyclerView recyclerViewAddress;
    @BindView(R.id.recyclerView_local_person_address)
    RecyclerView recyclerViewLocalPersonAddress;
    @BindView(R.id.btn_add_address)
    Button btnAddAddress;
    @BindView(R.id.rl_add_address)
    RelativeLayout rlAddAddress;
    @BindView(R.id.rl_songHuo)
    RelativeLayout rlSongHuo;
    @BindView(R.id.img_dingwei)
    ImageView imgDingwei;
    @BindView(R.id.tx_address)
    AppCompatTextView txAddress;
    @BindView(R.id.tx_startLocation)
    AppCompatTextView txStartLocation;
    @BindView(R.id.recyclerview_shop)
    RecyclerView recyclerviewShop;
    @BindView(R.id.ll_ziTi)
    LinearLayout llZiTi;
    @BindView(R.id.guanli)
    TextView guanli;
    @BindView(R.id.ll_my_address)
    LinearLayout llMyAddress;
    @BindView(R.id.poiRecyclerview)
    RecyclerView poiRecyclerview;
    @BindView(R.id.ll_all_address)
    LinearLayout llAllAddress;
    @BindView(R.id.ll_nearAddress)
    LinearLayout llNearAddress;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    /**
     * 0自提1外卖
     */
    private String type = "";
    /**
     * 0商品列表1确认订单
     */
    private String from = "";
    private ShopAndAddressPresenter shopAndAddressPresenter;
    /**
     * Poi查询条件类
     */
    private PoiSearch.Query query;
    /**
     * POI搜索
     */
    private PoiSearch poiSearch;
    /**
     * 要输入的poi搜索关键字
     */
    private String getAddress = "";
    private ArrayList<PoiAddressBean>  poiAddressBeanArrayList = new ArrayList<>();
    /**
     * 附近地址列表
     */
    private PoiKeywordSearchAdapter poiKeywordSearchAdapter;
    private double longitude;
    private double latitude;
    private String city;
    private List<PersonAddressEntity> personAddressEntityList = new ArrayList<>();
    private EmptyWrapper emptyWrapper;
    private MultiTypeAdapter shopAdapter;
    private MultiTypeAdapter addressAdapter;
    private AddressProvider addressProvider;
    private LoadMoreWrapper mLoadMoreWrapper;
    private ShopProvider shopProvider;
    private List<ShopEntity> shopEntityList = new ArrayList<>();
    /**
     * 用来判断切换门店自提和送货上门搜索框不进行操作
     */
    private boolean isSearch = false;
    /**
     * 用来判断是附近搜索还是关键字搜索    默认附近搜索
     */
    private boolean searchNear = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_and_address);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        city = SellWineApplication.city;
        longitude = SellWineApplication.longitude;
        latitude = SellWineApplication.latitude;
        initView();
    }

    private void initView() {
        MyMapUtils.startLocation();

        //0为自提,1为外送
        type = getIntent().getStringExtra("type");
        //0为商品列表1为确认订单
        from = getIntent().getStringExtra("from");
        if (Constants.VALUE_0.equals(type)) {
            onViewClicked(txShop);
        } else {
            onViewClicked(txOutCall);
        }
        /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTitle.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTitle.setLayoutParams(layoutParams);
        //附近列表adapter
        setNearAddressAdapter();
        //收货地址adapter
        setResultsAdapter();
        etAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isSearch) {
                    getAddress = String.valueOf(charSequence);

                    if (Constants.VALUE_0.equals(type)) {
                        //搜索商品
                        shopAndAddressPresenter.getShopList(getAddress, Constants.GET_LIST_PAGE_INDEX, Constants.GET_LIST_PAGE_SIZE_20, 0);
                    } else {
                        //搜索地址
                        if ("".equals(getAddress)) {
                            searchNear = true;
//                            showLongToast("请输入搜索关键字");
                            getAddress = "企业|公司|小区";
                            searchByBound();
                            return;
                        } else {
                            searchNear = false;
                            //关键字搜索
                            doSearchQuery();
                        }

                    }
                } else {
                    isSearch = true;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 商铺adapter
     */
    private void setShopAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerviewShop.setLayoutManager(layoutManager);
        shopProvider = new ShopProvider();
        shopAdapter = new MultiTypeAdapter();
        shopAdapter.setItems(shopEntityList);
        emptyWrapper = new EmptyWrapper(shopAdapter, 1);
        emptyWrapper.setEmptyView(R.layout.empty_layout);
        mLoadMoreWrapper = new LoadMoreWrapper(this, recyclerviewShop, emptyWrapper, 20);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //网络请求
                shopAndAddressPresenter.getShopList("", Constants.GET_LIST_PAGE_INDEX, Constants.GET_LIST_PAGE_SIZE_20, shopEntityList.size());
            }
        });
        shopAdapter.register(ShopEntity.class, shopProvider);
        recyclerviewShop.setAdapter(emptyWrapper);
        shopProvider.setOnClickListener(new ShopProvider.OnClickListener() {
            @Override
            public void onItemClick(ShopEntity shopEntity) {
                EventBus.getDefault().post(new ShopEvent(shopEntity));
            }

            @Override
            public void onDetailClick(ShopEntity shopEntity) {
                startActivity(new Intent(ShopAndAddressActivity.this, ShopDetailActivity.class).putExtra("shop", shopEntity));
            }
        });

    }

    /**
     * 附近地址adapter
     */
    private void setNearAddressAdapter() {

        //附近地址列表
        poiKeywordSearchAdapter = new PoiKeywordSearchAdapter(ShopAndAddressActivity.this, poiAddressBeanArrayList);
        poiKeywordSearchAdapter.setOnClickListener(new PoiKeywordSearchAdapter.OnClickListener() {
            @Override
            public void onItemClick(PoiAddressBean poiAddressBean) {
                //新增地址界面显示地址 type为0是新增type1是修改
                startActivity(new Intent(ShopAndAddressActivity.this, ModifyAddressActivity.class).putExtra("type", "0").putExtra("poiAddress", poiAddressBean.getDetailAddress() + poiAddressBean.getText()));
            }
        });
        recyclerViewAddress.setAdapter(poiKeywordSearchAdapter);
        recyclerViewAddress.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * poi搜索地址
     */
    private void setPoiAdapter() {

        //附近地址列表
        poiKeywordSearchAdapter = new PoiKeywordSearchAdapter(ShopAndAddressActivity.this, poiAddressBeanArrayList);
        poiKeywordSearchAdapter.setOnClickListener(new PoiKeywordSearchAdapter.OnClickListener() {
            @Override
            public void onItemClick(PoiAddressBean poiAddressBean) {
                //新增地址界面显示地址 type为0是新增type1是修改
                startActivity(new Intent(ShopAndAddressActivity.this, ModifyAddressActivity.class).putExtra("type", "0").putExtra("poiAddress", poiAddressBean.getDetailAddress() + poiAddressBean.getText()));
                llAllAddress.setVisibility(View.VISIBLE);
                poiRecyclerview.setVisibility(View.GONE);
            }
        });
        poiRecyclerview.setAdapter(poiKeywordSearchAdapter);
        poiRecyclerview.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * set我的收货地址数据
     */
    private void setResultsAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLocalPersonAddress.setLayoutManager(layoutManager);
        addressProvider = new AddressProvider(this);
        addressAdapter = new MultiTypeAdapter();
        addressAdapter.setItems(personAddressEntityList);
        addressAdapter.register(PersonAddressEntity.class, addressProvider);
        recyclerViewLocalPersonAddress.setAdapter(addressAdapter);
        addressProvider.setOnClickListener(new AddressProvider.OnClickListener() {
            /**
             * 修改事件
             * @param personAddressEntity
             */
            @Override
            public void onChooseClick(PersonAddressEntity personAddressEntity) {
                startActivity(new Intent(ShopAndAddressActivity.this, ModifyAddressActivity.class).putExtra("type", "1").putExtra("personAddressEntity", personAddressEntity));
            }

            /**
             * 选中地址事件
             * @param personAddressEntity
             */
            @Override
            public void onItemClick(PersonAddressEntity personAddressEntity) {
                //返回菜单显示外送
                EventBus.getDefault().post(new TakeAwayEvent(personAddressEntity));
                finish();
            }
        });
    }


    /**
     * 关键字搜索开始进行poi搜索
     */
    protected void doSearchQuery() {
        //不输入城市名称有些地方搜索不到
        // 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query = new PoiSearch.Query(getAddress, "", city);
        //这里没有做分页加载了,默认给50条数据
        // 设置每页最多返回多少条poiitem
        if (searchNear) {
            query.setPageSize(5);
        } else {
            query.setPageSize(20);
        }

        // 设置查第一页
        query.setPageNum(0);
        query.setCityLimit(true);
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }

    /**
     * 周边检索
     */
    public void searchByBound() {
        //不输入城市名称有些地方搜索不到
        // 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query = new PoiSearch.Query(getAddress, "", city);
        //这里没有做分页加载了,默认给50条数据
        // 设置每页最多返回多少条poiitem
        query.setPageSize(5);
        // 设置查第一页
        query.setPageNum(0);
        query.setCityLimit(true);
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        //设置周边搜索的中心点以及半径
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude, longitude), 500000));
        poiSearch.searchPOIAsyn();

    }

    @Override
    protected ShopAndAddressPresenter initPresenter() {
        return shopAndAddressPresenter = new ShopAndAddressPresenter();
    }

    @Override
    public void showDatas(List<ShopEntity> datas) {
        shopEntityList.clear();
        shopEntityList.addAll(datas);
        setShopAdapter();
    }

    @Override
    public void appendDatas(List<ShopEntity> datas) {
        shopEntityList.addAll(datas);
        recyclerviewShop.post(new Runnable() {
            @Override
            public void run() {
                mLoadMoreWrapper.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void showLoadingMore() {
        mLoadMoreWrapper.loadingMore();
    }

    @Override
    public void noMoreData() {
        mLoadMoreWrapper.reachEnd();
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

    }

    @OnClick({R.id.img_back, R.id.tx_shop, R.id.tx_outCall, R.id.btn_add_address, R.id.tx_startLocation, R.id.guanli})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tx_shop:
                isSearch = false;
                searchNear = true;
                if (Constants.VALUE_1.equals(type)) {
                    etAddress.setText("");

                }
                llSearch.setVisibility(View.VISIBLE);
                getAddress = "";
                type = "0";
                guanli.setVisibility(View.GONE);
                recyclerViewAddress.setVisibility(View.GONE);
                txShop.setBackgroundResource(R.drawable.address_choose_black_left);
                txShop.setTextColor(ContextCompat.getColor(this, R.color.white));
                txOutCall.setBackgroundResource(R.drawable.address_choose_white_right);
                txOutCall.setTextColor(ContextCompat.getColor(this, R.color.shop));
                rlSongHuo.setVisibility(View.GONE);
                llZiTi.setVisibility(View.VISIBLE);
                //获取店铺列表
                shopAndAddressPresenter.getShopList(getAddress, Constants.GET_LIST_PAGE_INDEX, Constants.GET_LIST_PAGE_SIZE_20, shopEntityList.size());
                break;
            case R.id.tx_outCall:
                isSearch = false;
                searchNear=true;
                if (Constants.VALUE_0.equals(type)) {
                    etAddress.setText("");
                    getAddress = "";
                }
                type = "1";
                recyclerViewAddress.setVisibility(View.VISIBLE);

                guanli.setVisibility(View.VISIBLE);
                llZiTi.setVisibility(View.GONE);
                txShop.setBackgroundResource(R.drawable.address_choose_white_left);
                txShop.setTextColor(ContextCompat.getColor(this, R.color.shop));
                txOutCall.setBackgroundResource(R.drawable.address_choose_black_right);
                txOutCall.setTextColor(ContextCompat.getColor(this, R.color.white));
                rlSongHuo.setVisibility(View.VISIBLE);
                shopAndAddressPresenter.getPersonAddressList();
                if (Constants.VALUE_0.equals(from)) {
                    getAddress = "企业|公司|小区";
                    llNearAddress.setVisibility(View.VISIBLE);
                    llSearch.setVisibility(View.VISIBLE);
                    //搜索附近地址
                    searchByBound();
                } else {
                    llNearAddress.setVisibility(View.GONE);
                    llSearch.setVisibility(View.GONE);
                }


                break;
            case R.id.btn_add_address:
                //新增地址
                startActivity(new Intent(ShopAndAddressActivity.this, ModifyAddressActivity.class).putExtra("type", "0"));
                break;
            case R.id.tx_startLocation:
                showLoadingView();
                MyMapUtils.startLocation();
                break;
            case R.id.guanli:
                startActivity(new Intent(ShopAndAddressActivity.this, PersonAddressActivity.class));
                break;
        }
    }

    /**
     * 关键字查询poi和周边检索信息查询的回调方法
     *
     * @param poiResult
     * @param rCode     1000代表成功
     */
    @Override
    public void onPoiSearched(PoiResult poiResult, int rCode) {

        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            // 搜索poi的结果
            if (poiResult != null && poiResult.getQuery() != null) {

                // 是否是同一条
                if (poiResult.getQuery().equals(query)) {
                    poiAddressBeanArrayList.clear();
                    // 取得搜索到的poiitems有多少页
                    // 取得第一页的poiitem数据，页数从数字0开始
                    List<PoiItem> poiItems = poiResult.getPois();
//                    // 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
//                    List<SuggestionCity> suggestionCities = poiResult
//                            .getSearchSuggestionCitys();
                    double lon = 0;
                    double lat = 0;
                    for (PoiItem item : poiItems) {
                        //获取经纬度对象
                        LatLonPoint llp = item.getLatLonPoint();
                        if (NotNull.isNotNull(llp)) {
                            lon = llp.getLongitude();
                            lat = llp.getLatitude();
                        }
                        String title = item.getTitle();
                        String text = item.getSnippet();
                        String provinceName = item.getProvinceName();
                        String cityName = item.getCityName();
                        String adName = item.getAdName();
                        //如果没有详细地址的不显示，防止选中添加地址显示为空
//                        if (!TextUtils.isEmpty(text)) {
                        poiAddressBeanArrayList.add(new PoiAddressBean(String.valueOf(lat), String.valueOf(lon), title, text, provinceName,
                                cityName, adName));
//                        }
                    }
                    if (searchNear) {
                        poiRecyclerview.setVisibility(View.GONE);
                        llAllAddress.setVisibility(View.VISIBLE);
                        //附近搜索
                        poiKeywordSearchAdapter.notifyDataSetChanged();
                    } else {
                        poiRecyclerview.setVisibility(View.VISIBLE);
                        llAllAddress.setVisibility(View.GONE);
                        //关键字搜索
                        setPoiAdapter();
                    }

                }
            } else {
                ToastUtil.show(ShopAndAddressActivity.this,
                        getString(R.string.no_result));
            }
        } else {
            ToastUtil.showerror(ShopAndAddressActivity.this, rCode);
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLocation(AMapLocation location) {
        if (null != location) {
            if (0 == location.getErrorCode()) {
                city = location.getCity();
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                txCity.setText(city);
                txAddress.setText(location.getPoiName());

            } else {
                showLongToast("定位失败");


            }
        } else {
            showLongToast("定位失败");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AddressEvent addressEvent) {
        presenter.getPersonAddressList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * @param personAddressEntity 获取我的收货地址成功回调
     * @author viiliz
     */
    @Override
    public void getMyShopAddressSuccess(List<PersonAddressEntity> personAddressEntity) {

        personAddressEntityList.clear();
        if (NotNull.isNotNull(personAddressEntity)) {
            llMyAddress.setVisibility(View.VISIBLE);
            personAddressEntityList.addAll(personAddressEntity);
            addressAdapter.notifyDataSetChanged();
        } else {
            llMyAddress.setVisibility(View.GONE);
        }

    }

    @Override
    public void getShopDetail(ShopDetailEntity shopDetailEntity) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getShop(ShopEvent shopEvent) {
        finish();
    }
}
