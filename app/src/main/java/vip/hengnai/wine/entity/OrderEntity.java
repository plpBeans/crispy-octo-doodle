package vip.hengnai.wine.entity;

import java.util.List;

/**
 * on 2019/12/10.
 *
 * @author hua.
 */

public class OrderEntity {


    /**
     * id : 1
     * type : 1
     * status : 待付款
     * finished : false
     * state : 1
     * booking : {"booking":true,"bookingDate":"2020-01-20","bookingTime":"19:30"}
     * message : {"type":2,"message":"请在{}前完成付款，过时订单将自动取消","data":"2020-01-19 00:00"}
     * deliverType : 自提
     * address : 宋城店(No.1002)
     * items : [{"goodsId":1002,"goodsName":"青岛原浆","specCode":"1L","specName":"1瓶装","image":"https://images.hengnai.vip/goods/a.png","quantity":2,"price":40,"preferential":true,"concPrice":32,"concDescription":"8折优惠","abnormal":false,"abnormityMessage":"","abnormityReason":""},{"goodsId":1003,"goodsName":"青岛白啤","specCode":"1L","specName":"1瓶装","image":"https://images.hengnai.vip/goods/a.png","quantity":1,"price":40,"preferential":false,"concPrice":40,"concDescription":"","abnormal":false,"abnormityMessage":"","abnormityReason":""},{"goodsId":1004,"goodsName":"青岛黑啤","specCode":"1L","specName":"1瓶装","image":"https://images.hengnai.vip/goods/a.png","quantity":1,"price":40,"preferential":false,"concPrice":20,"concDescription":"新品半价促销","abnormal":true,"abnormityMessage":"商品暂无法购买","abnormityReason":"库存不足"}]
     * goodsBrief : 青岛原浆
     * goodsSpecies : 3
     * goodsQuantity : 4
     * payment : 124
     * orderTime : 2020-01-21 16:20:12
     * pending :
     * memo :
     * actions : [{"type":9,"name":"取消订单","primary":false},{"type":1,"name":"去支付","primary":true}]
     * abnormal : false
     * abnormityMessage :
     * abnormityReason :
     */

    private int id;
    private int type;
    private String status;
    private boolean finished;
    private int state;
    private BookingBean booking;
    private MessageBean message;
    private String deliverType;
    private String address;
    private String goodsBrief;
    private int goodsSpecies;
    private int goodsQuantity;
    private double payment;
    private String orderTime;
    private String pending;
    private String memo;
    private boolean abnormal;
    private String abnormityMessage;
    private String abnormityReason;
    private List<ItemsBean> items;
    private List<ActionsBean> actions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public BookingBean getBooking() {
        return booking;
    }

    public void setBooking(BookingBean booking) {
        this.booking = booking;
    }

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public String getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(String deliverType) {
        this.deliverType = deliverType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGoodsBrief() {
        return goodsBrief;
    }

    public void setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief;
    }

    public int getGoodsSpecies() {
        return goodsSpecies;
    }

    public void setGoodsSpecies(int goodsSpecies) {
        this.goodsSpecies = goodsSpecies;
    }

    public int getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(int goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public boolean isAbnormal() {
        return abnormal;
    }

    public void setAbnormal(boolean abnormal) {
        this.abnormal = abnormal;
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

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public List<ActionsBean> getActions() {
        return actions;
    }

    public void setActions(List<ActionsBean> actions) {
        this.actions = actions;
    }

    public static class BookingBean {
        /**
         * booking : true
         * bookingDate : 2020-01-20
         * bookingTime : 19:30
         */

        private boolean booking;
        private String bookingDate;
        private String bookingTime;

        public boolean isBooking() {
            return booking;
        }

        public void setBooking(boolean booking) {
            this.booking = booking;
        }

        public String getBookingDate() {
            return bookingDate;
        }

        public void setBookingDate(String bookingDate) {
            this.bookingDate = bookingDate;
        }

        public String getBookingTime() {
            return bookingTime;
        }

        public void setBookingTime(String bookingTime) {
            this.bookingTime = bookingTime;
        }
    }

    public static class MessageBean {
        /**
         * type : 2
         * message : 请在{}前完成付款，过时订单将自动取消
         * data : 2020-01-19 00:00
         */

        private String type;
        private String message;
        private String data;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    public static class ItemsBean {
        /**
         * goodsId : 1002
         * goodsName : 青岛原浆
         * specCode : 1L
         * specName : 1瓶装
         * image : https://images.hengnai.vip/goods/a.png
         * quantity : 2
         * price : 40
         * preferential : true
         * concPrice : 32
         * concDescription : 8折优惠
         * abnormal : false
         * abnormityMessage :
         * abnormityReason :
         */

        private int goodsId;
        private String goodsName;
        private String specCode;
        private String specName;
        private String image;
        private int quantity;
        private int price;
        private boolean preferential;
        private int concPrice;
        private String concDescription;
        private boolean abnormal;
        private String abnormityMessage;
        private String abnormityReason;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getSpecCode() {
            return specCode;
        }

        public void setSpecCode(String specCode) {
            this.specCode = specCode;
        }

        public String getSpecName() {
            return specName;
        }

        public void setSpecName(String specName) {
            this.specName = specName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public boolean isPreferential() {
            return preferential;
        }

        public void setPreferential(boolean preferential) {
            this.preferential = preferential;
        }

        public int getConcPrice() {
            return concPrice;
        }

        public void setConcPrice(int concPrice) {
            this.concPrice = concPrice;
        }

        public String getConcDescription() {
            return concDescription;
        }

        public void setConcDescription(String concDescription) {
            this.concDescription = concDescription;
        }

        public boolean isAbnormal() {
            return abnormal;
        }

        public void setAbnormal(boolean abnormal) {
            this.abnormal = abnormal;
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
    }

    public static class ActionsBean {
        /**
         * type : 9
         * name : 取消订单
         * primary : false
         */

        private String type;
        private String name;
        private boolean primary;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPrimary() {
            return primary;
        }

        public void setPrimary(boolean primary) {
            this.primary = primary;
        }
    }
}
