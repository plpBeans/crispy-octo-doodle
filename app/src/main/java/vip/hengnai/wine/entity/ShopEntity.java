package vip.hengnai.wine.entity;

import java.io.Serializable;

/**
 * on 2019/11/23.
 *
 * @author hua.
 */

public class ShopEntity implements Serializable {
    /**
     * id : 1001
     * title : 中山路店(No.1001)
     * type : 旗舰店
     * icon : https://images.hengnai.vip/shop/1001/icon
     * favorite : true
     * address : 中山路201号
     * geo : 120.120023,10.131324
     * hours : 15:00-22:00
     * distance : 512m
     * status : 营业/打烊
     * abnormityMessage : 该门店正在装修中,暂未开始营业
     * working : true
     * abnormityReason : 装修中
     */

    private int id;
    private String title;
    private String type;
    private String icon;
    private boolean favorite;
    private String address;
    private String geo;
    private String hours;
    private String distance;
    private String status;
    private String abnormityMessage;
    private boolean working;
    private String abnormityReason;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAbnormityMessage() {
        return abnormityMessage;
    }

    public void setAbnormityMessage(String abnormityMessage) {
        this.abnormityMessage = abnormityMessage;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public String getAbnormityReason() {
        return abnormityReason;
    }

    public void setAbnormityReason(String abnormityReason) {
        this.abnormityReason = abnormityReason;
    }
}
