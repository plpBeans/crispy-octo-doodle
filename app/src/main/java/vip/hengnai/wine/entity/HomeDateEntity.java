package vip.hengnai.wine.entity;

/**
 * AUTHOR：dell
 * TIME:2018/7/17 2018/7/17
 * DESCRIPTION:首页日历数据查询
 * @author Hugh
 */
public class HomeDateEntity {


    private String orderId;
    private String serveTime;
    private String oldName;
    private String serveAddress;
    private String orderStatus;
    private String gdzt;
    private String yjkssj;

    public String getOrderId() {
        return orderId == null ? "" : orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getServeTime() {
        return serveTime == null ? "" : serveTime;
    }

    public void setServeTime(String serveTime) {
        this.serveTime = serveTime;
    }

    public String getOldName() {
        return oldName == null ? "" : oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getServeAddress() {
        return serveAddress == null ? "" : serveAddress;
    }

    public void setServeAddress(String serveAddress) {
        this.serveAddress = serveAddress;
    }

    public String getOrderStatus() {
        return orderStatus == null ? "" : orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getGdzt() {
        return gdzt == null ? "" : gdzt;
    }

    public void setGdzt(String gdzt) {
        this.gdzt = gdzt;
    }

    public String getYjkssj() {
        return yjkssj == null ? "" : yjkssj;
    }

    public void setYjkssj(String yjkssj) {
        this.yjkssj = yjkssj;
    }
}
