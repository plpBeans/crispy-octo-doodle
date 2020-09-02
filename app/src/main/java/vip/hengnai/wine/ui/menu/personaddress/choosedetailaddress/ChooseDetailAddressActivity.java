package vip.hengnai.wine.ui.menu.personaddress.choosedetailaddress;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.Tip;
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
import vip.hengnai.wine.R;
import vip.hengnai.wine.SellWineApplication;
import vip.hengnai.wine.entity.PersonAddressEntity;
import vip.hengnai.wine.entity.PoiAddressBean;
import vip.hengnai.wine.eventbus.AddressEvent;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.menu.personaddress.IPersonAddressView;
import vip.hengnai.wine.ui.menu.personaddress.PersonAddressPresenter;
import vip.hengnai.wine.ui.login.LoginActivity;
import vip.hengnai.wine.util.MyMapUtils;
import vip.hengnai.wine.util.ToastUtil;

/**
 * @author dell
 *选择地址界面
 */
public class ChooseDetailAddressActivity extends BaseMvpAppCompatActivity<IPersonAddressView, PersonAddressPresenter> implements IPersonAddressView, Inputtips.InputtipsListener, PoiSearch.OnPoiSearchListener {


    @BindView(R.id.imageBack)
    ImageView imageBack;
    @BindView(R.id.tx_location)
    AppCompatTextView txLocation;
    @BindView(R.id.choose_address_edit_text)
    EditText chooseAddressEditText;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    private PersonAddressPresenter personAddressPresenter;
    private String keyWord = "企业|公司|小区";// 要输入的poi搜索关键字
    private PoiResult poiResult; // poi返回的结果
    private int currentPage = 0;// 当前页面，从0开始计数
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;// POI搜索
    private PoiKeywordSearchAdapter adapter;
    private ArrayList<PoiAddressBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_detail_address);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        MyMapUtils.startLocation();
         /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        showLoadingView();
        initView();
        initListener();
        initData();

    }

    @Override
    protected PersonAddressPresenter initPresenter() {
        return personAddressPresenter = new PersonAddressPresenter();
    }

    private void initView() {
        txLocation.setText(SellWineApplication.city);
        data = new ArrayList<PoiAddressBean>();//自己创建的数据集合
        adapter = new PoiKeywordSearchAdapter(ChooseDetailAddressActivity.this, data);
        adapter.setOnClickListener(new PoiKeywordSearchAdapter.OnClickListener() {
            @Override
            public void onItemClick(PoiAddressBean poiAddressBean) {
//                EventBus.getDefault().post(new AddressEvent(poiAddressBean.getDetailAddress(), poiAddressBean.getText()));
                EventBus.getDefault().post(poiAddressBean);
                finish();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initListener() {
        chooseAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                keyWord = String.valueOf(charSequence);
                if ("".equals(keyWord)) {
//                    showShortToast("请输入搜索关键字");
//                    ToastUtil.show(ChooseDetailAddressActivity.this, "请输入搜索关键字");
//                    data.clear();
                    keyWord = "企业|公司|小区";
                    searchByBound();
//                    adapter.notifyDataSetChanged();
                    return;
                } else {
                    //关键字搜索
                    doSearchQuery();
//                    输入内容自动
//                    第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
//                    InputtipsQuery inputquery = new InputtipsQuery(keyWord, "上海");
//                    inputquery.setCityLimit(true);//限制在当前城市
//                    Inputtips inputTips = new Inputtips(PoiKeywordSearchActivity.this, inputquery);
//                    inputTips.setInputtipsListener(PoiKeywordSearchActivity.this);
//                    inputTips.requestInputtipsAsyn();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 周边检索
     */
    public void searchByBound() {
        currentPage = 0;
        //不输入城市名称有些地方搜索不到
        query = new PoiSearch.Query(keyWord, "", SellWineApplication.city);// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        //这里没有做分页加载了,默认给50条数据
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页
        query.setCityLimit(true);
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(SellWineApplication.latitude, SellWineApplication.longitude), 500000));//设置周边搜索的中心点以及半径
        poiSearch.searchPOIAsyn();

    }


    /**
     * 关键字搜索开始进行poi搜索
     */
    protected void doSearchQuery() {
        currentPage = 0;
        //不输入城市名称有些地方搜索不到
        query = new PoiSearch.Query(keyWord, "", txLocation.getText().toString());// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        //这里没有做分页加载了,默认给50条数据
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页
        query.setCityLimit(true);
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }


    /**
     * 关键字查询poi和周边检索信息查询的回调方法
     */
    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getQuery() != null) {  // 搜索poi的结果
                if (result.getQuery().equals(query)) {  // 是否是同一条
                    poiResult = result;
                    ArrayList<PoiAddressBean> data = new ArrayList<PoiAddressBean>();//自己创建的数据集合
                    // 取得搜索到的poiitems有多少页
                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    for (PoiItem item : poiItems) {
                        //获取经纬度对象
                        LatLonPoint llp = item.getLatLonPoint();
                        double lon = llp.getLongitude();
                        double lat = llp.getLatitude();

                        String title = item.getTitle();
                        String text = item.getSnippet();
                        String provinceName = item.getProvinceName();
                        String cityName = item.getCityName();
                        String adName = item.getAdName();
                        data.add(new PoiAddressBean(String.valueOf(lat), String.valueOf(lon), title, text, provinceName,
                                cityName, adName));
                    }
                    PoiKeywordSearchAdapter adapter = new PoiKeywordSearchAdapter(ChooseDetailAddressActivity.this, data);
                    adapter.setOnClickListener(new PoiKeywordSearchAdapter.OnClickListener() {
                        @Override
                        public void onItemClick(PoiAddressBean poiAddressBean) {
//                            EventBus.getDefault().post(new AddressEvent(poiAddressBean.getDetailAddress(), poiAddressBean.getText()));
                            EventBus.getDefault().post(poiAddressBean);
                            finish();
                        }
                    });
                    recyclerView.setAdapter(adapter);
                }
            } else {
                ToastUtil.show(ChooseDetailAddressActivity.this,
                        getString(R.string.no_result));
            }
        } else {
            ToastUtil.showerror(this, rCode);
        }
        hideLoadingView();
    }


    /**
     * POI信息查询回调方法
     */
    @Override
    public void onPoiItemSearched(PoiItem item, int rCode) {
        // TODO Auto-generated method stub

    }


    /**
     * 输入内容自动提示回调
     * 返回结果成功或者失败的响应码。1000为成功，其他为失败（详细信息参见网站开发指南-实用工具-错误码对照表）
     *
     * @param list
     * @param rCode
     */
    @Override
    public void onGetInputtips(List<Tip> list, int rCode) {
        data.clear();
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (null == list || 0 == list.size()) {
                ToastUtil.show(ChooseDetailAddressActivity.this,
                        getString(R.string.no_result));

                adapter.notifyDataSetChanged();
            } else {

                for (Tip item : list) {
//                        LatLonPoint llp = item.getPoint();
//                        double lon = llp.getLongitude();
//                        double lat = llp.getLatitude();

                    String title = item.getName();
                    String text = item.getAddress();
//                        String provinceName = item.getProvinceName();
//                        String cityName = item.getCityName();
//                        String adName = item.getAddress();
//                        data.add(new PoiAddressBean(String.valueOf(lon), String.valueOf(lat), title, text,"",
                    data.add(new PoiAddressBean("", "", title, text, "",
                            "", ""));
                }
                adapter.notifyDataSetChanged();

            }

        } else {
            ToastUtil.showerror(this, rCode);
        }
    }



    @Override
    public void showDatas(List<PersonAddressEntity> datas) {

    }

    @Override
    public void appendDatas(List<PersonAddressEntity> datas) {

    }

    @Override
    public void showLoadingMore() {

    }

    @Override
    public void noMoreData() {

    }

    @Override
    public void showLoadingView() {
        showProgressDialog(null, getString(R.string.login_ing), null, true);
    }

    @Override
    public void hideLoadingView() {
        dismissProgressDialog();
    }

    @Override
    public void showErrorMessage(@NonNull String message) {

    }

    @Override
    public void forceToReLogin(String message) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @OnClick({R.id.imageBack, R.id.tx_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageBack:
                finish();
                break;
            case R.id.tx_location:
                break;
        }
    }

    /**
     * 定位成功回调
     *
     * @param location
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLocation(AMapLocation location) {

        if (null != location) {
            if (0 == location.getErrorCode()) {
                txLocation.setText(SellWineApplication.city);
                searchByBound();
            } else {
                showLongToast("定位失败");
            }
        } else {
            showLongToast("定位失败");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void deletePersonAddressSuccess(String message) {

    }

    @Override
    public void modifyPersonAddressSuccess(String message) {

    }
}
