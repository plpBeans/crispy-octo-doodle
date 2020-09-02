package vip.hengnai.wine.util;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;


import vip.hengnai.wine.util.aes.AesUtils;


/**
 *
 * @author hua
 * @date 2016/12/15
 */

public class AuthUtil {

    public static String AES_KEY=null;
    /**
     * 登录成功信息保存的Preference Key
     */
    public static final String TOKEN = "token";
    public static final String USER_ID = "userId";
    public static final String USERNAME = "username";
    /**
     * 手势密码key
     */
    public static final String GESTURELOCK_KEY = "gesturelock_key";

    /**
     * 是否设置手势解锁key
     */
    public static final String ISGESTURELOCK_KEY = "isgesturelock_key";
//    public static final List<NurseFloorEntity.LiveMemberDTOListBeanXX> LOCAL_PERSON_LIST=new ArrayList<>();


    private static AuthUtil authUtil;

    private SharedPreferences mPreferences;

    public static AuthUtil getAuthUtil(@NonNull Context context) {
//        if (authUtil == null) {
//            synchronized (AuthUtil.class) {
                if (authUtil == null) {
        authUtil = new AuthUtil(context);
        AES_KEY= AesUtils.getAeskey(context.getPackageName()+"124567890987654321");
                }
//            }
//        }
        return authUtil;
    }

    private AuthUtil(@NonNull Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public AuthUtil(SharedPreferences preferences) {
        mPreferences = preferences;
    }

    public void exitClear(){
        logout();
    }
    public boolean getIsgesturelockKey() {
        boolean  isgesturelockKey = false;
        if (null == mPreferences) {
            return false;
        }
        try {
            isgesturelockKey=   mPreferences.getBoolean(ISGESTURELOCK_KEY, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isgesturelockKey;

    }
    public void setIsgesturelockKey(boolean isgesturelockKey){
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putBoolean(ISGESTURELOCK_KEY, isgesturelockKey);
        edit.commit();
    }

    public String getGesturelockKey() {
        String  gesturelock=null;
        if (null == mPreferences) {
            return null;
        }
        try {
            gesturelock=   AesUtils.aesDecrypt(AES_KEY,  mPreferences.getString(GESTURELOCK_KEY, ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gesturelock;

    }
    public void setGesturelockKey(String gesturelockKey){
        SharedPreferences.Editor edit = mPreferences.edit();
        try {
            edit.putString(GESTURELOCK_KEY,  AesUtils.aesEncrypt(AES_KEY,gesturelockKey));
        } catch (Exception e) {
            e.printStackTrace();
        }

        edit.commit();
    }



    public String getUsername() {
        String  usernameString=null;
        if (null == mPreferences) {
            return null;
        }
        try {
            usernameString=   AesUtils.aesDecrypt(AES_KEY,  mPreferences.getString(USERNAME, ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usernameString;

    }
    public void setUsername(String username){
        SharedPreferences.Editor edit = mPreferences.edit();
        try {
            edit.putString(USERNAME,  AesUtils.aesEncrypt(AES_KEY,username));
        } catch (Exception e) {
            e.printStackTrace();
        }

        edit.commit();
    }



//    public void save(UserInfoEntity userInfoEntity) {
//        save(userInfoEntity.getToken(),userInfoEntity.getUserId(),userInfoEntity.getUsername(),userInfoEntity.getUserType(),userInfoEntity.getStatus(),userInfoEntity.getStatus()
//                ,userInfoEntity.getEmail(),userInfoEntity.getRealName(),userInfoEntity.getOrgCode(),userInfoEntity.getOrgName());
//    }




    public void save(String token, String userId,String userName) {
        if (null == mPreferences) {
            return;
        }
        SharedPreferences.Editor dataEdit = mPreferences.edit();
        try {
            dataEdit.putString(TOKEN, AesUtils.aesEncrypt(AES_KEY,token));
            dataEdit.putString(USER_ID, AesUtils.aesEncrypt(AES_KEY,userId) );
            dataEdit.putString(USERNAME, AesUtils.aesEncrypt(AES_KEY,userName) );


        } catch (Exception e) {
            e.printStackTrace();
        }

        dataEdit.commit();
    }
    public void setToken(String token) {
        SharedPreferences.Editor edit = mPreferences.edit();
        try {
            edit.putString(TOKEN,  AesUtils.aesEncrypt(AES_KEY,token));
        } catch (Exception e) {
            e.printStackTrace();
        }

        edit.commit();
    }
    public String getToken() {
        String  tokenString=null;
        if (null == mPreferences) {
            return null;
        }
        try {
            tokenString=   AesUtils.aesDecrypt(AES_KEY, mPreferences.getString(TOKEN, ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenString;
    }

    public String getUserId() {
        String  userIdString=null;
        if (null == mPreferences) {
            return null;
        }
        try {
            userIdString=   AesUtils.aesDecrypt(AES_KEY,mPreferences.getString(USER_ID, ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userIdString;
    }

    public void logout() {
        if (null == mPreferences) {
            return;
        }
        save("", "", "");
    }

    /**
     * 是否已经登录
     *
     * @return
     */
    public boolean hasLoginIn() {
//        if (NotNull.isNotNull(getToken()) || NotNull.isNotNull(getUserId())) {
//            return true;
//        }
        if (NotNull.isNotNull(getToken())) {
            return true;
        }
        return false;
    }

}
