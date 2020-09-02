package vip.hengnai.wine.ui.menu.goods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.GoodsDetailEntity;
import vip.hengnai.wine.entity.MenuEntity;
import vip.hengnai.wine.entity.GoodsEntity;
import vip.hengnai.wine.entity.SpecsEntity;
import vip.hengnai.wine.eventbus.MainEvent;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.menu.IMenuView;
import vip.hengnai.wine.ui.menu.MenuPresenter;
import vip.hengnai.wine.ui.menu.shopaddress.shopdetail.photoview.ShowPhotoActivity;
import vip.hengnai.wine.ui.order.confirm.OrderConfirmActivity;
import vip.hengnai.wine.util.glide.MyGlideModule;
import vip.hengnai.wine.view.AddDeleteView;
import vip.hengnai.wine.view.NewBanner;
import vip.hengnai.wine.view.SuperTextView;

/**
 * 商品界面
 *
 * @author Hugh
 */
public class GoodsActivity extends BaseMvpAppCompatActivity<IMenuView, MenuPresenter> implements IMenuView {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_title_right)
    TextView textTitleRight;
    @BindView(R.id.img_arrow)
    ImageView imgArrow;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.banner)
    NewBanner banner;
    @BindView(R.id.img_size)
    TextView imgSize;
    @BindView(R.id.tx_goodsName)
    TextView txGoodsName;
    @BindView(R.id.tx_EXP)
    TextView txEXP;
    @BindView(R.id.tx_potency)
    TextView txPotency;
    @BindView(R.id.tx_layIn)
    TextView txLayIn;
    @BindView(R.id.tx_drinkTime)
    TextView txDrinkTime;
    @BindView(R.id.recyclerview_specs)
    RecyclerView recyclerviewSpecs;
    @BindView(R.id.tx_introduce)
    SuperTextView txIntroduce;
    @BindView(R.id.tx_goodsPrice)
    TextView txGoodsPrice;
    @BindView(R.id.tx_goodsSpecs)
    TextView txGoodsSpecs;
    @BindView(R.id.addView)
    AddDeleteView addView;
    @BindView(R.id.tx_buy)
    TextView txBuy;
    @BindView(R.id.tx_shoppingCar)
    TextView txShoppingCar;
    @BindView(R.id.rl_bottom)
    LinearLayout rlBottom;
    @BindView(R.id.recyclerview_goodsTrait)
    RecyclerView recyclerviewGoodsTrait;
    private MenuPresenter menuPresenter;
    private List<String> urlList = new ArrayList<>();
    private int count = 1;
    private String specs = "";
    private double price ;
    private int goodsId;
    private GoodsDetailEntity mGoodsDetailEntity;
    /**
     * 上传规格信息
     */
    private String specCode="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        ButterKnife.bind(this);
           /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        goodsId = getIntent().getIntExtra("goodsId",0);
        menuPresenter.getGoods(goodsId);

    }

    private void initView( GoodsDetailEntity goodsDetailEntity) {
        mGoodsDetailEntity=goodsDetailEntity;
        txGoodsName.setText(goodsDetailEntity.getName());
        textTitle.setText("商品详情页");
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        urlList.add("http://ww4.sinaimg.cn/large/0.jpg");
        imgSize.setText("1/" + urlList.size());
        //设置图片集合
        banner.setImages(urlList);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                MyGlideModule.loadImage(context, imageView, (String) path, 0);
            }
        });
        //设置自动轮播，默认为true
        banner.isAutoPlay(false);
        //设置轮播时间
        banner.setDelayTime(2000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent1 = new Intent(GoodsActivity.this, ShowPhotoActivity.class);

                intent1.putStringArrayListExtra("imageurls", (ArrayList<String>) urlList);
                intent1.putExtra("currentItem", position);
                startActivity(intent1);
            }
        });
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                imgSize.setText(position + 1 + "/" + urlList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //商品特性
        setTraitAdapter();
        //商品规格
        setSpecsAdapter(goodsDetailEntity);
        addView.setNumber(count);
        addView.setOnAddDelClickListener(new AddDeleteView.OnAddDelClickListener() {
            @Override
            public void onAddClick(View v) {
                count = addView.getNumber() + 1;
                addView.setNumber(count);
            }

            @Override
            public void onDelClick(View v) {
                if (1 < addView.getNumber()) {
                    count = addView.getNumber() - 1;
                    addView.setNumber(count);
                } else {
                    showLongToast("已经最小值了");
                }
            }
        });
    }

    /**
     * 规格
     */
    private void setSpecsAdapter( GoodsDetailEntity goodsDetailEntity) {
        List<GoodsDetailEntity.SpecsBean> specsEntityList = new ArrayList<>();
//        specsEntityList.add(new SpecsEntity().setPrice("50").setSpecs("ILx2瓶"));
//        specsEntityList.add(new SpecsEntity().setPrice("100").setSpecs("2Lx2瓶"));
//        specsEntityList.add(new SpecsEntity().setPrice("75").setSpecs("1Lx3瓶"));
        specsEntityList.addAll(goodsDetailEntity.getSpecs());
        if(0!=specsEntityList.size()){
            specs = specsEntityList.get(0).getDescription();
            price = specsEntityList.get(0).getConcPrice();
            //商品规格
            specCode=specsEntityList.get(0).getCode();
        }

        txGoodsPrice.setText("￥" + price);
        txGoodsSpecs.setText("规格"+specs);
        recyclerviewSpecs.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));

        MultiTypeAdapter mPatientsAdapter = new MultiTypeAdapter();

        SpecsProvider specsProvider = new SpecsProvider(this);
        //点击监听
        specsProvider.setOnClickListener(new SpecsProvider.OnClickListener() {
            @Override
            public void onItemClick(int position, GoodsDetailEntity.SpecsBean specsBean) {
                specsProvider.setSelect(position);
                //规格赋值
                specCode=specsEntityList.get(position).getCode();
                specs = specsEntityList.get(position).getDescription();
                price = specsEntityList.get(position).getPrice();
                txGoodsPrice.setText("￥" + price);
                txGoodsSpecs.setText(specs);
            }
        });

        mPatientsAdapter.register(GoodsDetailEntity.SpecsBean.class, specsProvider);

        mPatientsAdapter.setItems(specsEntityList);
        recyclerviewSpecs.setAdapter(mPatientsAdapter);
    }

    /**
     * 商品特性adapter
     */
    private void setTraitAdapter() {
        List<String> traitList = new ArrayList<>();
        traitList.add("1111");
        traitList.add("2222222222222222222");
        traitList.add("6666666666666666666666666666666");
        traitList.add("88888888888888888888888888888888888888888888888888888888");
        traitList.add("33333333333333333333333333333333333333333333333333333333333");
        traitList.add("44444");
        traitList.add("00000");
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                /**
                 * 重点在这里，这个getSpanSize方法的返回值其实指的是单个item所占用的我们设置的每行的item个数
                 */
                int stringLenth = traitList.get(i).length();

                 if (5 < stringLenth && stringLenth < 12) {
                    return 2;
                } else if (12 < stringLenth) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        recyclerviewGoodsTrait.setLayoutManager(gridLayoutManager);

        MultiTypeAdapter mPatientsAdapter = new MultiTypeAdapter();

        TraitProvider traitProvider = new TraitProvider(this);

        mPatientsAdapter.register(String.class, traitProvider);

        mPatientsAdapter.setItems(traitList);
        recyclerviewGoodsTrait.setAdapter(mPatientsAdapter);
    }


    @Override
    protected MenuPresenter initPresenter() {
        return menuPresenter = new MenuPresenter();
    }

    @Override
    public void showLoadingView() {
        showProgressDialog(null, "正在加载", null, true);
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

    @Override
    public void check(int position, boolean isScroll) {

    }

    @Override
    public void showDefaultMenu(MenuEntity menuEntity) {

    }


    /**
     * 获取商品详情信息
     * @param goodsDetailEntity
     */
    @Override
    public void showGoods(GoodsDetailEntity goodsDetailEntity) {
        initView(goodsDetailEntity);
    }

    /**
     * 添加购物车成功
     * @param message
     */
    @Override
    public void addCartMessage(String message) {
        finish();
    }

    @OnClick({R.id.img_back, R.id.tx_buy, R.id.tx_shoppingCar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tx_buy:
                //立即购买 type为0的时候代表从商品接入，type为1的时候代表从购物车进入
                startActivity(new Intent(GoodsActivity.this, OrderConfirmActivity.class).putExtra("type","0")
                        .putExtra("goodsId",goodsId).putExtra("specCode",specCode).putExtra("quantity",count));
                break;
            case R.id.tx_shoppingCar:
//                EventBus.getDefault().post(new MainEvent().setIndex("3"));
                //添加购物车请求
                menuPresenter.addCart(goodsId,specCode,addView.getNumber());

                break;
        }
    }
}
