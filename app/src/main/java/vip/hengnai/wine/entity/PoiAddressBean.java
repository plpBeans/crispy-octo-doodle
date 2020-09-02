package vip.hengnai.wine.entity;

import java.io.Serializable;


/**
 * @author Hugh
 */
public class PoiAddressBean implements Serializable {
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 信息内容
     */
    private String text;
    /**
     * 详细地址 (搜索的关键字)
     */
    public String detailAddress;
    /**
     * 省
     */
    public String province;
    /**
     * 城市
     */
    public String city;
    /**
     * 区域(宝安区)
     */
    public String district;

    public PoiAddressBean(String lat, String lon, String detailAddress, String text, String province, String city, String district){
        this.longitude = lon;
        this.latitude = lat;
        this.text = text;
        this.detailAddress = detailAddress;
        this.province = province;
        this.city = city;
        this.district = district;


    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getText() {
        return text;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

}
