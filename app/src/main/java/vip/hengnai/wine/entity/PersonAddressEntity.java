package vip.hengnai.wine.entity;

import java.io.Serializable;

/**
 * @author Viiliz
 * @date 2019/11/14.
 * GitHub：
 * email：
 * description：个人地址管理界面实体类
 */
public class PersonAddressEntity implements Serializable {

    /**
     * id : 208
            * title : Miranda Leung女士
     * addressee : Miranda Leung
     * suffix : 女士
     * phone : 0571-88888888
            * address : 之江路158号
     * room : 20号楼1楼
     * alias : 杭州悦溪山庄
     * geo : 120.094771,30.169478
            * tag : 家
     * defaultAddress : true
            */

    private int id;
    private String title;
    private String addressee;
    private String suffix;
    private String phone;
    private String address;
    private String room;
    private String alias;
    private String geo;
    private String tag;
    private boolean defaultAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
