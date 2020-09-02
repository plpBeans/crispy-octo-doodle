package vip.hengnai.wine.ui.menu.personaddress;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import vip.hengnai.wine.Constants;
import vip.hengnai.wine.R;
import vip.hengnai.wine.entity.PersonAddressEntity;
import vip.hengnai.wine.entity.PoiAddressBean;
import vip.hengnai.wine.eventbus.AddressEvent;
import vip.hengnai.wine.framework.BaseMvpAppCompatActivity;
import vip.hengnai.wine.ui.menu.personaddress.choosedetailaddress.ChooseDetailAddressActivity;
import vip.hengnai.wine.util.NotNull;
import vip.hengnai.wine.view.SwitchView;

/**
 * @author dell
 *         个人地址编辑界面
 */
public class ModifyAddressActivity extends BaseMvpAppCompatActivity<IPersonAddressView, PersonAddressPresenter> implements IPersonAddressView {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_title_right)
    TextView textTitleRight;
    @BindView(R.id.img_arrow)
    ImageView imgArrow;
    @BindView(R.id.city_address_text)
    AppCompatTextView cityAddressText;
    @BindView(R.id.detail_address_text)
    EditText detailAddressText;
    @BindView(R.id.address_user_name)
    EditText addressUserName;
    @BindView(R.id.address_user_phone)
    EditText addressUserPhone;
    @BindView(R.id.address_home_text)
    AppCompatTextView addressHomeText;
    @BindView(R.id.address_company_text)
    AppCompatTextView addressCompanyText;
    @BindView(R.id.address_school_text)
    AppCompatTextView addressSchoolText;
    @BindView(R.id.item_switch)
    SwitchView itemSwitch;
    @BindView(R.id.address_delete_btn)
    Button addressDeleteBtn;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.line1)
    LinearLayout line1;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.image4)
    ImageView image4;
    @BindView(R.id.rl_detail_address)
    RelativeLayout rlDetailAddress;
    @BindView(R.id.image5)
    ImageView image5;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.rl_address_type)
    RelativeLayout rlAddressType;
    @BindView(R.id.rl_checkBox)
    RelativeLayout rlCheckBox;
    @BindView(R.id.address_save_btn)
    Button addressSaveBtn;
    @BindView(R.id.img_sex)
    ImageView imgSex;
    @BindView(R.id.img_man)
    ImageView imgMan;
    @BindView(R.id.tx_man)
    TextView txMan;
    @BindView(R.id.img_lady)
    ImageView imgLady;
    @BindView(R.id.tx_lady)
    TextView txLady;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.image_address_alias)
    ImageView imageAddressAlias;
    @BindView(R.id.city_address_alias)
    EditText cityAddressAlias;
    @BindView(R.id.rl_address_alias)
    RelativeLayout rlAddressAlias;

    private PersonAddressPresenter personAddressPresenter;
    /**
     * 用来判断是添加地址进来的还是修改地址进来的
     * 0 新增地址  1 修改地址
     */
    private String type = "";
    /**
     * 选择地址标签
     * 0 家  1 公司  2学校
     */
    private String addressFlag ;
    /**
     * 默认地址
     * 0 非默认 1 默认
     */
    private String isAcquiesce = "1";
    /**
     * 附近地址选中传过来的值
     */
    private String poiAddress;

    private PersonAddressEntity personAddressEntity = null;
    /**
     * 1:先生,2:女士
     */
    private String sex="";
    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    //选中地址
    private PoiAddressBean mPoiAddressBean;
    private int id=0;
    /**
     * 经纬度
     */
    private String geo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);
        ButterKnife.bind(this);
         /*设置头部栏高度*/
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rlTop.getLayoutParams();
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        rlTop.setLayoutParams(layoutParams);
        EventBus.getDefault().register(this);
        initView();
    }

    @Override
    protected PersonAddressPresenter initPresenter() {
        return personAddressPresenter = new PersonAddressPresenter();
    }


    private void initView() {
        type = getIntent().getStringExtra("type");
        if (Constants.VALUE_0.equals(type)) {
            //新增
            addressDeleteBtn.setVisibility(View.GONE);
            poiAddress = getIntent().getStringExtra("poiAddress");
            cityAddressText.setText(poiAddress);
        } else {
            //修改
            personAddressEntity = (PersonAddressEntity) getIntent().getSerializableExtra("personAddressEntity");
            id=personAddressEntity.getId();
            addressDeleteBtn.setVisibility(View.VISIBLE);
            addressUserName.setText(personAddressEntity.getAddressee());
            cityAddressText.setText(personAddressEntity.getAddress());
            detailAddressText.setText(personAddressEntity.getRoom());
            geo=personAddressEntity.getGeo();
            sex=personAddressEntity.getSuffix();
            if(Constants.SIR.equals(sex)){
                imgMan.setImageResource(R.mipmap.bg_xuan);
                imgLady.setImageResource(R.mipmap.bg_moren);
            }else{
                imgMan.setImageResource(R.mipmap.bg_moren);
                imgLady.setImageResource(R.mipmap.bg_xuan);
            }
            addressUserPhone.setText(personAddressEntity.getPhone());
            cityAddressAlias.setText(personAddressEntity.getAlias());
            if(personAddressEntity.isDefaultAddress()){
                itemSwitch.setOpened(true);
            }else{
                itemSwitch.setOpened(false);
            }
            addressFlag=personAddressEntity.getTag();
            if(Constants.HOME.equals(addressFlag)){
                addressHomeText.setTextColor(Color.parseColor("#FFFFFF"));
                addressCompanyText.setTextColor(Color.parseColor("#696E6F"));
                addressSchoolText.setTextColor(Color.parseColor("#696E6F"));
                addressHomeText.setBackgroundResource(R.drawable.address_choose_black);
                addressCompanyText.setBackgroundResource(R.drawable.address_choose_white);
                addressSchoolText.setBackgroundResource(R.drawable.address_choose_white);
            }else if(Constants.COMPANY.equals(addressFlag)){
                addressHomeText.setTextColor(Color.parseColor("#696E6F"));
                addressCompanyText.setTextColor(Color.parseColor("#FFFFFF"));
                addressSchoolText.setTextColor(Color.parseColor("#696E6F"));
                addressHomeText.setBackgroundResource(R.drawable.address_choose_white);
                addressCompanyText.setBackgroundResource(R.drawable.address_choose_black);
                addressSchoolText.setBackgroundResource(R.drawable.address_choose_white);
            }else{
                addressHomeText.setTextColor(Color.parseColor("#696E6F"));
                addressCompanyText.setTextColor(Color.parseColor("#696E6F"));
                addressSchoolText.setTextColor(Color.parseColor("#FFFFFF"));
                addressHomeText.setBackgroundResource(R.drawable.address_choose_white);
                addressCompanyText.setBackgroundResource(R.drawable.address_choose_white);
                addressSchoolText.setBackgroundResource(R.drawable.address_choose_black);
            }
        }

        imgBack.setVisibility(View.VISIBLE);
        textTitle.setText("地址编辑页");

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PoiAddressBean poiAddressBean ) {
        mPoiAddressBean=poiAddressBean;
        cityAddressText.setText(mPoiAddressBean.getDetailAddress()+mPoiAddressBean.getText());
        geo=mPoiAddressBean.getLongitude()+","+mPoiAddressBean.getLatitude();
    }


    @OnClick({R.id.img_back, R.id.address_home_text, R.id.address_company_text, R.id.address_school_text, R.id.address_delete_btn,R.id.address_save_btn,
            R.id.rl_address, R.id.img_man,R.id.img_lady})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.address_home_text:
                addressFlag = "家";
                addressHomeText.setTextColor(Color.parseColor("#FFFFFF"));
                addressCompanyText.setTextColor(Color.parseColor("#696E6F"));
                addressSchoolText.setTextColor(Color.parseColor("#696E6F"));
                addressHomeText.setBackgroundResource(R.drawable.address_choose_black);
                addressCompanyText.setBackgroundResource(R.drawable.address_choose_white);
                addressSchoolText.setBackgroundResource(R.drawable.address_choose_white);
                break;
            case R.id.address_company_text:
                addressFlag = "公司";
                addressHomeText.setTextColor(Color.parseColor("#696E6F"));
                addressCompanyText.setTextColor(Color.parseColor("#FFFFFF"));
                addressSchoolText.setTextColor(Color.parseColor("#696E6F"));
                addressHomeText.setBackgroundResource(R.drawable.address_choose_white);
                addressCompanyText.setBackgroundResource(R.drawable.address_choose_black);
                addressSchoolText.setBackgroundResource(R.drawable.address_choose_white);
                break;
            case R.id.address_school_text:
                addressFlag = "学校";
                addressHomeText.setTextColor(Color.parseColor("#696E6F"));
                addressCompanyText.setTextColor(Color.parseColor("#696E6F"));
                addressSchoolText.setTextColor(Color.parseColor("#FFFFFF"));
                addressHomeText.setBackgroundResource(R.drawable.address_choose_white);
                addressCompanyText.setBackgroundResource(R.drawable.address_choose_white);
                addressSchoolText.setBackgroundResource(R.drawable.address_choose_black);

                break;
            case R.id.img_man:
                imgMan.setImageResource(R.mipmap.bg_xuan);
                imgLady.setImageResource(R.mipmap.bg_moren);
                sex="先生";
                break;
            case R.id.img_lady:
                imgMan.setImageResource(R.mipmap.bg_moren);
                imgLady.setImageResource(R.mipmap.bg_xuan);
                sex="女士";
                break;
            case R.id.address_save_btn:
                if (!NotNull.isNotNull(addressUserName.getText().toString().trim())) {
                    showShortToast("姓名不能位空");
                    return;
                }else if(!NotNull.isNotNull(sex)){
                    showShortToast("请选择性别");
                    return;
                } else if (!NotNull.isNotNull(cityAddressText.getText().toString().trim())) {
                    showShortToast("请选择您的地址");
                    return;
                } else if (!NotNull.isNotNull(detailAddressText.getText().toString().trim())) {
                    showShortToast("请填写您的详细地址(门牌号等)");
                    return;
                } else if (!NotNull.isNotNull(addressUserPhone.getText().toString().trim())) {
                    showShortToast("请填写您的手机号码");
                    return;
                }
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("title", addressUserName.getText().toString()+sex);
                    jsonObject.put("addressee", addressUserName.getText().toString());
                    if(Constants.SIR.equals(sex)){
                        jsonObject.put("suffix", "1:先生");
                    }else{
                        jsonObject.put("suffix", "2:女士");
                    }
                    jsonObject.put("address", cityAddressText.getText().toString());
                    jsonObject.put("room", detailAddressText.getText().toString());
                    jsonObject.put("alias", cityAddressAlias.getText().toString());
                    jsonObject.put("phone", addressUserPhone.getText().toString());
                    jsonObject.put("geo", geo);
                    if(Constants.HOME.equals(addressFlag)){
                        jsonObject.put("tag", "家");
                    }else if(Constants.COMPANY.equals(addressFlag)){
                        jsonObject.put("tag", "公司");
                    }else{
                        jsonObject.put("tag", "学校");
                    }
                    jsonObject.put("defaultAddress", itemSwitch.isOpened());
                    RequestBody body = RequestBody.create(JSON, jsonObject.toString());
                /**
                 * 用来判断是添加地址进来的还是修改地址进来的
                 * 0 新增地址  1 修改地址
                 * 点击保存按钮返回不同的界面
                 */
                if (Constants.VALUE_0.equals(type)) {
                    /**
                     * 新增地址接口调用
                     */

                    personAddressPresenter.addPersonAddress(body);


                } else {
                    /**
                     * 修改地址接口调用
                     */
                    personAddressPresenter.modifyPersonAddress(id,body);

                }

                } catch (Exception e) {
            }
                break;
            case R.id.address_delete_btn:
                //删除
                personAddressPresenter.deletePersonAddress(id);
                break;
            case R.id.rl_address:
                startActivity(new Intent(this, ChooseDetailAddressActivity.class));
                break;
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
        showShortToast(message);
    }

    @Override
    public void forceToReLogin(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void deletePersonAddressSuccess(String message) {
        EventBus.getDefault().post(new AddressEvent());
        finish();
    }
    /**
     * 修改地址信息成功
     *
     * @param message
     */
    @Override
    public void modifyPersonAddressSuccess(String message) {
//            showLongToast(message);
            EventBus.getDefault().post(new AddressEvent());
            finish();
    }
}
