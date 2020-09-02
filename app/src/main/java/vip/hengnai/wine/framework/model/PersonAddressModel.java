package vip.hengnai.wine.framework.model;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import vip.hengnai.wine.entity.PersonAddressEntity;
import vip.hengnai.wine.framework.base.BaseResponse;
import vip.hengnai.wine.framework.base.ResponseCompose;
import vip.hengnai.wine.framework.response.PersonAddressResponse;
import vip.hengnai.wine.framework.retrofit.RetrofitManager;

/**
 * @author Viiliz
 * @date 2019/11/14.
 * GitHub：
 * email：
 * description：
 */
public class PersonAddressModel {

    /**
     * 个人地址列表
     * @return
     */
    public Observable<List<PersonAddressEntity>> getPersonAddressList(){
        return RetrofitManager.getInstance().getPersonAddressList()
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<PersonAddressResponse, List<PersonAddressEntity>>() {
                    @Override
                    public List<PersonAddressEntity> convert(PersonAddressResponse value) {
                        List<PersonAddressEntity> response=value.getData();
                        return null== response ? Collections.<PersonAddressEntity>emptyList() : response;
                    }
                },PersonAddressResponse.class));
    }

    /**
     * 新增个人地址
     * @param requestBody
     * @return
     */
    public Observable<String> addPersonAddress(RequestBody requestBody){

        return RetrofitManager.getInstance().addPersonAddress(requestBody)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<BaseResponse,String>() {
                    @Override
                    public String convert(BaseResponse value) {
                        return value.getMessage();
                    }
                },BaseResponse.class));
    }
    /**
     * 修改个人地址
     * @param requestBody
     * @return
     */
    public Observable<String> modifyPersonAddress(int id,RequestBody requestBody){

        return RetrofitManager.getInstance().modifyPersonAddress(Integer.valueOf(id),requestBody)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<BaseResponse,String>() {
                    @Override
                    public String convert(BaseResponse value) {
                        return value.getMessage();
                    }
                },BaseResponse.class));
    }
    /**
     * 删除个人地址
     * @param addressId
     * @return
     */
    public Observable<String> deletePersonAddress(int addressId){
        return RetrofitManager.getInstance().deletePersonAddress(Integer.valueOf(addressId))
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<BaseResponse,String>() {
                    @Override
                    public String convert(BaseResponse value) {
                        return value.getMessage();
                    }
                },BaseResponse.class));
    }
}
