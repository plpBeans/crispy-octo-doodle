package vip.hengnai.wine.framework.model;

import org.json.JSONObject;

import okhttp3.MediaType;
import vip.hengnai.wine.entity.MobileCodeEntity;
import vip.hengnai.wine.entity.UserInfoEntity;
import vip.hengnai.wine.framework.base.BaseResponse;
import vip.hengnai.wine.framework.base.ResponseCompose;
import vip.hengnai.wine.framework.response.LoginResponse;
import vip.hengnai.wine.framework.response.MobileCodeResponse;
import vip.hengnai.wine.framework.retrofit.RetrofitManager;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import vip.hengnai.wine.util.aes.LongforRsa;

/**
 * AUTHOR：dell
 * TIME:2019/7/24 2019/7/24
 * DESCRIPTION:LoginModel
 * 通过ResponseCompose对返回的json数据进行解析,返回bean或者集合或者status和message
 * @author dell
 */
public class LoginModel {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    /**
     * 获取短信验证码
     * @param mobile
     * @return
     */
    public Observable<MobileCodeEntity> getMobileCode(String mobile){
        return RetrofitManager.getInstance().getMobileCode(mobile)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<MobileCodeResponse,MobileCodeEntity>() {
                    @Override
                    public MobileCodeEntity convert(MobileCodeResponse value) {
                        return value.getData();
                    }
                },MobileCodeResponse.class));
    }
    /**
     * 短信验证码登录
     * @param mobile
     * @return
     */
    public Observable<UserInfoEntity> login(String mobile,String key,String code){
//        JSONObject jsonObject=new JSONObject();
//        try {
//            LongforRsa.encrypt("123456");
//            jsonObject.put("mobile", mobile);
//            jsonObject.put("key", key);
//            jsonObject.put("code", code);
//        } catch (Exception e) {
//        }
//        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        return RetrofitManager.getInstance().login(mobile,key,code)
                .compose(ResponseCompose.handleResponse(new ResponseCompose.Converter<LoginResponse,UserInfoEntity>() {
                    @Override
                    public UserInfoEntity convert(LoginResponse value) {
                        return value.getData();
                    }
                },LoginResponse.class));
    }
}
