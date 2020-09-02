package vip.hengnai.wine.ui.menu.personaddress;

import android.support.annotation.NonNull;

import java.util.List;

import okhttp3.RequestBody;
import vip.hengnai.wine.entity.PersonAddressEntity;
import vip.hengnai.wine.framework.BaseMvpListAuthPresenter;
import vip.hengnai.wine.framework.IMvpView;
import vip.hengnai.wine.framework.base.BaseCommonErrorSubscriber;
import vip.hengnai.wine.framework.model.PersonAddressModel;

/**
 * @author Viiliz
 * @date 2019/11/14.
 * GitHub：
 * email：
 * description：
 */
public class PersonAddressPresenter extends BaseMvpListAuthPresenter<IPersonAddressView,PersonAddressEntity> {
    private PersonAddressModel personAddressModel;

    public PersonAddressPresenter() {
        personAddressModel = new PersonAddressModel();
    }

    /**
     * 个人地址列表
     */
    public void getPersonAddressList(){
        addDisposable(personAddressModel.getPersonAddressList(), new BaseCommonErrorSubscriber<List<PersonAddressEntity>>() {
            @Override
            public void onNext(List<PersonAddressEntity> personAddressEntityList) {
                getView().showDatas(personAddressEntityList);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }
        });
    }
    /**
     * 新增个人地址
     * @param requestBody
     */
    public void addPersonAddress(RequestBody requestBody){

        addDisposable(personAddressModel.addPersonAddress(requestBody), new BaseCommonErrorSubscriber<String>() {
            @Override
            public void onNext(String message) {
                getView().modifyPersonAddressSuccess(message);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }
        });
    }

    /**
     * 修改个人地址
     * @param requestBody
     */
    public void modifyPersonAddress(int id,RequestBody requestBody){

        addDisposable(personAddressModel.modifyPersonAddress(id,requestBody), new BaseCommonErrorSubscriber<String>() {
            @Override
            public void onNext(String message) {
                getView().modifyPersonAddressSuccess(message);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }
        });
    }
    /**
     * 删除个人地址
     * @param addressId
     */
    public void deletePersonAddress(int addressId){

        addDisposable(personAddressModel.deletePersonAddress(addressId), new BaseCommonErrorSubscriber<String>() {
            @Override
            public void onNext(String message) {
                getView().deletePersonAddressSuccess(message);
            }

            @NonNull
            @Override
            public IMvpView getMvpView() {
                return getView();
            }
        });
    }

}
