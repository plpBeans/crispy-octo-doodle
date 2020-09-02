package vip.hengnai.wine.entity;

import java.util.List;

/**
 * on 2020/1/4.
 *
 * @author hua.
 */

public class ShopDetailEntity {
    /**
     * id : 1001
     * sn : No.1001
     * name : 中山路店
     * title : 中山路店(No.1001)
     * type : 旗舰店
     * icon : https://images.hengnai.vip/shop/1001/icon.png
     * images : ["https://images.hengnai.vip/shop/1001/images/0001.png"]
     * address : 中山路201号
     * geo : 120.120023,10.131324
     * hours : ["周一至周五 15:00-22:00","周末 14:00-24:00"]
     * description : 门店坐落于...
     * favorite : true
     * tags : ["干净"]
     * distance : 512m
     * status : 营业/打烊
     * working : true
     * abnormityMessage : 该门店正在装修中,暂未开始营业
     * abnormityReason : 装修中
     */

    private int id;
    private String sn;
    private String name;
    private String title;
    private String type;
    private String icon;
    private String address;
    private String geo;
    private String description;
    private boolean favorite;
    private String distance;
    private String status;
    private boolean working;
    private String abnormityMessage;
    private String abnormityReason;
    private List<String> images;
    private List<String> hours;
    private List<String> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
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

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public String getAbnormityMessage() {
        return abnormityMessage;
    }

    public void setAbnormityMessage(String abnormityMessage) {
        this.abnormityMessage = abnormityMessage;
    }

    public String getAbnormityReason() {
        return abnormityReason;
    }

    public void setAbnormityReason(String abnormityReason) {
        this.abnormityReason = abnormityReason;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getHours() {
        return hours;
    }

    public void setHours(List<String> hours) {
        this.hours = hours;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
