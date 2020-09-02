package vip.hengnai.wine.entity;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hua
 * @date 2018/1/24
 */
public class UserInfoEntity{
        private String token;

    public String getToken() {
        return token == null ? "" : token;
    }

    public UserInfoEntity setToken(String token) {
        this.token = token;
        return this;
    }
}
