package vip.hengnai.wine.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.GoodsDetailEntity;
import vip.hengnai.wine.entity.MenuEntity;
import vip.hengnai.wine.entity.GoodsEntity;
import vip.hengnai.wine.eventbus.ShopEvent;
import vip.hengnai.wine.eventbus.TakeAwayEvent;
import vip.hengnai.wine.framework.BaseMvpFragment;
import vip.hengnai.wine.ui.menu.adapter.ItemHeaderDecoration;
import vip.hengnai.wine.ui.menu.adapter.LeftAdapter;
import vip.hengnai.wine.ui.menu.adapter.RightAdapter;
import vip.hengnai.wine.ui.menu.goods.GoodsActivity;
import vip.hengnai.wine.ui.menu.shopaddress.ShopAndAddressActivity;
import vip.hengnai.wine.util.LocationAlert;
import vip.hengnai.wine.util.MyMapUtils;
import vip.hengnai.wine.util.NotNull;
import vip.hengnai.wine.util.StatusBarUtil;
import vip.hengnai.wine.util.glide.MyGlideModule;

/**
 * 菜单商品列表
 *
 * @author hua
 */
public class FragmentMenu extends BaseMvpFragment<IMenuView, MenuPresenter> implements IMenuView {

    @BindView(R.id.img_banner)
    ImageView imgBanner;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tx_shop)
    TextView txShop;
    @BindView(R.id.tx_distance)
    TextView txDistance;
    @BindView(R.id.tx_byMyself)
    TextView txByMyself;
    @BindView(R.id.tx_outCall)
    TextView txOutCall;
    @BindView(R.id.rl_shop)
    RelativeLayout rlShop;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    @BindView(R.id.recyclerView_left)
    RecyclerView recyclerViewLeft;
    @BindView(R.id.recyclerView_right)
    RecyclerView recyclerViewRight;
    private MenuPresenter menuPresenter;
    private View view;

    public String city = "";
    public double longitude;
    public double latitude;

    /**
     * 左边数据
     */
    private  List<String> listLeft= new ArrayList<>();
    /**
     * 左边adapter
     */
    public LeftAdapter leftAdapter;
    private LinearLayoutManager manager;
    /**
     * 右边adapter
     */
    private RightAdapter rightAdapter;
    private LinearLayoutManager linearLayoutManager;
    /**
     * 右边滑动到第几个
     */
    private int moveCounts;
    public boolean move=false;
    /**
     * 右边数据 具体的实体类集合
     */
    private List<GoodsEntity> rightList=new ArrayList<>();
    private boolean isChoose=false;
    private int choosePosition=0;
    private MenuEntity defaultEntity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 优化View减少View的创建次数
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_menu, null);


            return view;
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
//        Log.e("fragment","执行");
        initView();
        MyMapUtils.startLocation();
//            initData();
    }

    @Override
    protected MenuPresenter initPresenter() {
        return menuPresenter = new MenuPresenter();
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
            StatusBarUtil.setImmersiveStatusBar(getActivity(), true);
            MyMapUtils.startLocation();
        }
    }

    private void initView() {
        StatusBarUtil.setImmersiveStatusBar(getActivity(), true);
        /*设置头部栏高度*/
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imgBanner.getLayoutParams();
//        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
//        imgBanner.setLayoutParams(layoutParams);
        MyGlideModule.loadImage(getActivity(), imgBanner, "", R.mipmap.ic_home_bottom);
        swiprefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                MyMapUtils.startLocation();
                presenter.getDefaultMenus();
            }
        });
        //左边adapter
        manager = new LinearLayoutManager(getActivity());
        recyclerViewLeft.setLayoutManager(manager);

//        //右边adapter
        recyclerViewRight.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             *监听回调，滑动结束回调。
            */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //在这里进行第二次滚动（最后的100米！）
                if (move ){
                    move = false;
                    //获取要置顶的项在当前屏幕的位置，moveCount是记录的要置顶项在RecyclerView中的位置
                    int n = moveCounts - linearLayoutManager.findFirstVisibleItemPosition();
                    if ( 0 <= n && n < recyclerView.getChildCount()){
                        //获取要置顶的项顶部离RecyclerView顶部的距离
                        int top = recyclerView.getChildAt(n).getTop();
                        //最后的移动
                        recyclerView.scrollBy(0, top);
                    }
                }
            }
            /*
            监听回调，滑动状态改变回调
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (move&&newState==RecyclerView.SCROLL_STATE_IDLE) {
                    move=false;
                    int n=moveCounts-linearLayoutManager.findFirstVisibleItemPosition();
                    if (0<=n&&n<recyclerView.getChildCount())
                    {
                        int top=recyclerView.getChildAt(n).getTop();
                        recyclerView.scrollBy(0,top);
                    }
                }
            }
        });
        linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerViewRight.setLayoutManager(linearLayoutManager);
        setAdapter();
        presenter.getDefaultMenus();
    }
//    private void initData() {
//        //左边数据
//
//        listLeft= new ArrayList<>();
//        listLeft.add("广东");
//        listLeft.add("黑龙江");
//        listLeft.add("江西");
//        listLeft.add("辽宁");
//        listLeft.add("内蒙古");
//        listLeft.add("宁夏");
//        listLeft.add("山西");
//        listLeft.add("陕西");
//        listLeft.add("西藏");
//        listLeft.add("云南");
//        listLeft.add("甘肃");
//        listLeft.add("广西");
//        listLeft.add("海南");
//        listLeft.add("新疆");
//
//        leftAdapter=new LeftAdapter(listLeft,getActivity());
//        leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Toast.makeText(getActivity(),listLeft.get(position),Toast.LENGTH_LONG).show();
//                isChoose=true;
//                choosePosition = position;
//                startMove(position,true);
//
//                Log.i(">>>>>>","position:"+position);
//                moveToCenter(position);
//            }
//        });
//        recyclerViewLeft.setAdapter(leftAdapter);
//
//        initAllData(listLeft);
//
//
//    }
//
//    /**
//     * 添加数据
//     * @param listLeft
//     */
//    private void initAllData(List<String> listLeft) {
//        cityList=new ArrayList<>();
//        cityList.add(new String[]{"深圳","东莞","广州","韶关","汕头","肇庆","惠州"});
//        cityList.add(new String[]{"哈尔滨","尚志","五常","海伦"});
//        cityList.add(new String[]{"南昌","赣州","宜春","吉安"});
//        cityList.add(new String[]{"沈阳","大连","鞍山","丹东"});
//        cityList.add(new String[]{"呼和浩特","包头","赤峰","鄂尔多斯"});
//        cityList.add(new String[]{"银川","石嘴山","吴忠","中卫"});
//        cityList.add(new String[]{"太原","大同","运城","临汾"});
//        cityList.add(new String[]{"西安","宝鸡","咸阳","延安"});
//        cityList.add(new String[]{"拉萨","日喀则","那曲","巴青"});
//        cityList.add(new String[]{"昆明","大理","丽江","普洱"});
//        cityList.add(new String[]{"兰州","天水","白银","平凉"});
//        cityList.add(new String[]{"南宁","柳州","桂林","钦州"});
//        cityList.add(new String[]{"海口","三亚","三沙","琼海"});
//        cityList.add(new String[]{"乌鲁木齐","克拉玛依","哈密","阿克苏"});
//        for (int i=0;i<listLeft.size();i++) {
//            GoodsEntity titleBean=new GoodsEntity();
//            titleBean.setProvince(listLeft.get(i));
//            //设置为title
//            titleBean.setTitle(true);
//            //设置tag，方便获取position
//            titleBean.setTag(String.valueOf(i));
//            rightList.add(titleBean);
//
//            for (int j=0;j<cityList.get(i).length;j++) {
//                GoodsEntity goodsEntity =new GoodsEntity();
//                goodsEntity.setCity(cityList.get(i)[j]);
//                //设置成和省份一样的tag，将省份与城市绑定。
//                goodsEntity.setTag(String.valueOf(i));
//                rightList.add(goodsEntity);
//            }
//        }
//        rightAdapter=new RightAdapter(rightList);
//        rightAdapter.setOnItemClickListener(new RightAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//              startActivity(new Intent(getActivity(), GoodsActivity.class).putExtra("goods",rightList.get(position)));
//            }
//        });
//        if(0!=rightList.size()){
//
//        }
//        ItemHeaderDecoration decoration=new ItemHeaderDecoration(getActivity(),rightList,listLeft);
//        decoration.setData(rightList);
//        recyclerViewRight.addItemDecoration(decoration);
//        decoration.setCheckListener(this);
//        recyclerViewRight.setAdapter(rightAdapter);
//    }

    @Override
    public void showLoadingView() {
//        showProgressDialog(null, "正在加载", null,true);
        setRefresh(true, swiprefresh);
    }

    @Override
    public void hideLoadingView() {
        setRefresh(false, swiprefresh);
    }

    @Override
    public void showErrorMessage(@NonNull String message) {
        showShortToast(message);
    }

    @Override
    public void forceToReLogin(String message) {
    }

    @OnClick({R.id.tx_byMyself, R.id.tx_outCall})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tx_byMyself:
                //自提
                txByMyself.setBackgroundResource(R.mipmap.btn_choose_location);
                txByMyself.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                txOutCall.setBackgroundResource(0);
                txOutCall.setTextColor(ContextCompat.getColor(getActivity(), R.color.lunarTextColor));
                getActivity().startActivity(new Intent(getActivity(), ShopAndAddressActivity.class).putExtra("type", "0").putExtra("from","0"));
                break;
            case R.id.tx_outCall:
                //外送
                txByMyself.setBackgroundResource(0);
                txByMyself.setTextColor(ContextCompat.getColor(getActivity(), R.color.lunarTextColor));
                txOutCall.setBackgroundResource(R.mipmap.btn_choose_location);
                txOutCall.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                getActivity().startActivity(new Intent(getActivity(), ShopAndAddressActivity.class).putExtra("type", "1").putExtra("from","0"));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLocation(AMapLocation location) {
        hideLoadingView();
        if (null != location) {
            if (0 == location.getErrorCode()) {
                city = location.getCity();
                longitude = location.getLongitude();
                latitude = location.getLatitude();

            } else {

                if (!isHidden()) {
                    LocationAlert.showDialog(getActivity());
                }

            }
        } else {
            if (!isHidden()) {
                LocationAlert.showDialog(getActivity());
            }
        }
    }

    /**
     * 选中店铺信息
     * @param shopEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getShop(ShopEvent shopEvent) {
        txShop.setText(shopEvent.getShopEntity().getTitle());
        txDistance.setText("距您"+shopEvent.getShopEntity().getDistance());
        //调用自提门店菜单
        presenter.getShopMenus(shopEvent.getShopEntity().getId());
    }

    /**
     * 选中个人地址信息外送
     * @param takeAwayEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTakeAway(TakeAwayEvent takeAwayEvent) {
        txShop.setText(takeAwayEvent.getPersonAddressEntity().getAddress());
        txDistance.setText(takeAwayEvent.getPersonAddressEntity().getPhone()+"  " +takeAwayEvent.getPersonAddressEntity().getTitle());
        //调用外送菜单
        presenter.getTakeAwayMenus(takeAwayEvent.getPersonAddressEntity().getId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 点击事件
     * @param position
     * @param isScroll
     */
    @Override
    public void check(int position, boolean isScroll) {
        startMove(position,isScroll);
        moveToCenter(position);
    }

    /**
     * 设置adapter
     */
     public void setAdapter(){

         leftAdapter=new LeftAdapter(listLeft,getActivity());
         leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
             @Override
             public void onItemClick(int position) {
                 isChoose=true;
                 choosePosition = position;
                 startMove(position,true);
                 moveToCenter(position);
             }
         });
         recyclerViewLeft.setAdapter(leftAdapter);

         rightAdapter=new RightAdapter(getActivity(),rightList);
         rightAdapter.setOnItemClickListener(new RightAdapter.OnItemClickListener() {
             @Override
             public void onItemClick(int position) {
                 startActivity(new Intent(getActivity(), GoodsActivity.class).putExtra("goodsId",rightList.get(position).getId()));
             }
         });

         recyclerViewRight.setAdapter(rightAdapter);
     }
    /**
     * 默认菜单
     * @param menuEntity
     */
    @Override
    public void showDefaultMenu(MenuEntity menuEntity) {
        //判断是自提还是外卖
        if(menuEntity.isTakeaway()){
            if(null!=menuEntity.getAddress()){
                //外送
                txShop.setText(menuEntity.getAddress().getAddress());
                txDistance.setText(menuEntity.getAddress().getPhone()+"  " +menuEntity.getAddress().getTitle());
            }


        }else{
            if(null!=menuEntity.getShop()){
                //自提
                txShop.setText(menuEntity.getShop().getTitle());
                txDistance.setText("距您"+ menuEntity.getShop().getDistance());

            }

        }
        if(NotNull.isNotNull(menuEntity.getMenus())){
            listLeft.clear();
            rightList.clear();
            defaultEntity= menuEntity;

            for(int i = 0; i< menuEntity.getMenus().size(); i++){
                //左边
                listLeft.add(menuEntity.getMenus().get(i).getName());
                //设置右边标题
                GoodsEntity titleBean=new GoodsEntity();
                titleBean.setBigName(listLeft.get(i));
                //设置为title
                titleBean.setTitle(true);
                //设置tag，方便获取position
                titleBean.setTag(String.valueOf(i));
                rightList.add(titleBean);
                //右边
//                rightList.addAll(menuEntity.getMenus().get(i).getGoods());
                for(int j = 0; j< menuEntity.getMenus().get(i).getGoods().size(); j++){
                    GoodsEntity goodsEntity= menuEntity.getMenus().get(i).getGoods().get(j);
                    goodsEntity.setTag(String.valueOf(i));
                    //右边
                    rightList.add(goodsEntity);
                }

            }
            ItemHeaderDecoration decoration=new ItemHeaderDecoration(getActivity(),rightList,listLeft);
            decoration.setData(rightList);
            recyclerViewRight.addItemDecoration(decoration);
            decoration.setCheckListener(this);
            leftAdapter.notifyDataSetChanged();
            rightAdapter.notifyDataSetChanged();
        }
    }

//    /**
//     * 自提门店菜单
//     * @param menuEntity
//     */
//    @Override
//    public void showShopMenu(MenuEntity menuEntity) {
//
//        txShop.setText(menuEntity.getShop().getTitle());
//        txDistance.setText("距您"+ menuEntity.getShop().getDistance());
//
//        if(NotNull.isNotNull(menuEntity.getMenus())){
//            listLeft.clear();
//            rightList.clear();
//            defaultEntity= menuEntity;
//
//            for(int i = 0; i< menuEntity.getMenus().size(); i++){
//                //左边
//                listLeft.add(menuEntity.getMenus().get(i).getName());
//                //设置右边标题
//                GoodsEntity titleBean=new GoodsEntity();
//                titleBean.setBigName(listLeft.get(i));
//                //设置为title
//                titleBean.setTitle(true);
//                //设置tag，方便获取position
//                titleBean.setTag(String.valueOf(i));
//                rightList.add(titleBean);
//                //右边
////                rightList.addAll(menuEntity.getMenus().get(i).getGoods());
//                for(int j = 0; j< menuEntity.getMenus().get(i).getGoods().size(); j++){
//                    GoodsEntity goodsEntity= menuEntity.getMenus().get(i).getGoods().get(j);
//                    goodsEntity.setTag(String.valueOf(i));
//                    //右边
//                    rightList.add(goodsEntity);
//                }
//
//            }
//            leftAdapter=new LeftAdapter(listLeft,getActivity());
//            leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(int position) {
//                    isChoose=true;
//                    choosePosition = position;
//                    startMove(position,true);
//                    moveToCenter(position);
//                }
//            });
//            recyclerViewLeft.setAdapter(leftAdapter);
//
//
//
//            rightAdapter=new RightAdapter(getActivity(),rightList);
//            rightAdapter.setOnItemClickListener(new RightAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(int position) {
//                    startActivity(new Intent(getActivity(), GoodsActivity.class).putExtra("goodsId",rightList.get(position).getId()));
//                }
//            });
//            if(0!=rightList.size()){
//
//            }
//            ItemHeaderDecoration decoration=new ItemHeaderDecoration(getActivity(),rightList,listLeft);
//            decoration.setData(rightList);
//            recyclerViewRight.addItemDecoration(decoration);
//            decoration.setCheckListener(this);
//            recyclerViewRight.setAdapter(rightAdapter);
//        }
//    }
//
//    /**
//     * 外送菜单回调
//     * @param menuEntity
//     */
//    @Override
//    public void showTakeAwayMenu(MenuEntity menuEntity) {
//        if(NotNull.isNotNull(menuEntity.getMenus())){
//            listLeft.clear();
//            rightList.clear();
//            defaultEntity= menuEntity;
//
//            for(int i = 0; i< menuEntity.getMenus().size(); i++){
//                //左边
//                listLeft.add(menuEntity.getMenus().get(i).getName());
//                //设置右边标题
//                GoodsEntity titleBean=new GoodsEntity();
//                titleBean.setBigName(listLeft.get(i));
//                //设置为title
//                titleBean.setTitle(true);
//                //设置tag，方便获取position
//                titleBean.setTag(String.valueOf(i));
//                rightList.add(titleBean);
//                //右边
////                rightList.addAll(menuEntity.getMenus().get(i).getGoods());
//                for(int j = 0; j< menuEntity.getMenus().get(i).getGoods().size(); j++){
//                    GoodsEntity goodsEntity= menuEntity.getMenus().get(i).getGoods().get(j);
//                    goodsEntity.setTag(String.valueOf(i));
//                    //右边
//                    rightList.add(goodsEntity);
//                }
//
//            }
//            leftAdapter=new LeftAdapter(listLeft,getActivity());
//            leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(int position) {
//                    isChoose=true;
//                    choosePosition = position;
//                    startMove(position,true);
//                    moveToCenter(position);
//                }
//            });
//            recyclerViewLeft.setAdapter(leftAdapter);
//
//
//
//            rightAdapter=new RightAdapter(getActivity(),rightList);
//            rightAdapter.setOnItemClickListener(new RightAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(int position) {
//                    startActivity(new Intent(getActivity(), GoodsActivity.class).putExtra("goodsId",rightList.get(position).getId()));
//                }
//            });
//            if(0!=rightList.size()){
//
//            }
//            ItemHeaderDecoration decoration=new ItemHeaderDecoration(getActivity(),rightList,listLeft);
//            decoration.setData(rightList);
//            recyclerViewRight.addItemDecoration(decoration);
//            decoration.setCheckListener(this);
//            recyclerViewRight.setAdapter(rightAdapter);
//        }
//    }

    @Override
    public void showGoods(GoodsDetailEntity goodsDetailEntity) {

    }

    @Override
    public void addCartMessage(String message) {

    }

    /**
     * 左边滑动
     * @param position
     * @param isLeft
     */
    private void startMove(int position, boolean isLeft) {

        if (isLeft) {
            //凡是点击左边，将左边点击的位置作为当前的tag
            leftAdapter.setClickPositon(position);
            int counts=0;
            for (int i=0;i<position;i++) {
                //position 为点击的position
                //计算需要滑动的城市数目
//                counts+=cityList.get(i).length;
                counts+=defaultEntity.getMenus().get(i).getGoods().size();
            }
            //加上title（省份）数目
            //右边滑动
            moveCounts=counts+position;
            recyclerViewRight.stopScroll();
            moveToPosition(moveCounts);
            //凡是点击左边，将左边点击的位置作为当前的tag
            ItemHeaderDecoration.setCurrentTag(String.valueOf(choosePosition));
        } else {

            if (isChoose) {
                isChoose = false;
            } else {
                leftAdapter.setClickPositon(position);
            }
            ItemHeaderDecoration.setCurrentTag(String.valueOf(position));
        }

    }

    /***
     *右边移动到指定位置
     */
    private void moveToPosition(int moveCounts) {
        //获取屏幕可见的第一个item的position
        int firstItem=linearLayoutManager.findFirstVisibleItemPosition();
        //获取屏幕可见的最后一个item的position
        int lastItem=linearLayoutManager.findLastVisibleItemPosition();
        if (moveCounts<firstItem)
        {
            recyclerViewRight.scrollToPosition(moveCounts);
        }
        else if (moveCounts<lastItem)
        {
            View aimsView=recyclerViewRight.getChildAt(moveCounts-firstItem);
            int top =aimsView.getTop();
            recyclerViewRight.scrollBy(0,top);
        }
        else {
            /*
            当往下滑动的position大于可见的最后一个item的时候，调用 recyclerView.scrollToPosition(moveCounts);
            只能讲item滑动到屏幕的底部。
             */
            /*
            第一种方案：先将item移动到底部，然后在调用scrollBy移动到顶部。不可行，不能讲item滑动到顶部，
            离上面还有一小段距离；
             recyclerView.scrollToPosition(moveCounts);
            int top=recyclerView.getHeight();
            recyclerView.scrollBy(0,top);

            第二种方案：直接计算要滑动的距离。程序崩溃，报空指针。看系统源码可知，当
            滑动的距离大于ChildCount（可见的item数目），将返回空。
            int top=recyclerView.getChildAt(moveCounts-firstItem).getTop();
            recyclerView.scrollBy(0,top);

            第三种解决方案：先将目标item滑动到底部，然后进行异步处理。调用滚动监听方法RecyclerViewListener。
            滑动到顶部

             */

//            int top=recyclerView.getHeight();
//            recyclerView.scrollBy(0,top);
//            int childcount=recyclerView.getChildCount();
//            Log.i("<<<<<<<<<<","childcount"+childcount);
//            int top=recyclerView.getChildAt(moveCounts-firstItem).getTop();
//            recyclerView.scrollBy(0,top);

            recyclerViewRight.scrollToPosition(moveCounts);
            move=true;
        }
    }
    /**
     * 左边adapter将当前选中的item居中
     */

    public   void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        Log.i(">>>>>>>>>",position - manager.findFirstVisibleItemPosition()+"eeeee");
        int itemPosition=position-manager.findFirstVisibleItemPosition();
        /*
        当往上滑动太快，会出现itemPosition为-1的情况。做下判断
         */
        if (0<itemPosition&&itemPosition<manager.getChildCount()){
            View childAt = recyclerViewLeft.getChildAt(position - manager.findFirstVisibleItemPosition());
            Log.i("<<<<<<",position - manager.findFirstVisibleItemPosition()+"");

            int y = (childAt.getTop() - recyclerViewLeft.getHeight() / 2);
            Log.i("<<<<<<",childAt.getTop()+"ssssss");
            Log.i("<<<<<<", y+"");
            recyclerViewLeft.smoothScrollBy(0, y);
        }

    }
}
