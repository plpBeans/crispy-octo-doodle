package vip.hengnai.wine.entity;

import java.util.List;

/**
 * on 2020/2/29.
 *
 * @author hua.
 */

public class OrderDetailEntity {
    /**
     * id : 1
     * type : 1
     * status : {"status":"3","name":"已接单","datetime":"2020-02-13 12:13:12","finished":true,"state":"1"}
     * booking : {"booking":true,"mode":"1","bookingDate":"2020-01-01","bookingTime":"17:00"}
     * message : {"type":2,"message":"请在{}内完成付款，过时订单将自动取消","data":"10:00"}
     * items : [{"goodsId":12,"goodsName":"青岛原浆1L","specCode":"1L","specName":"1L","image":"https://images.hengnai.vip/goods/a.png","quantity":1,"price":40,"preferential":true,"concDescription":"新品8折","concPrice":32,"abnormal":false,"abnormityMessage":"商品暂无法购买","abnormityReason":"库存不足"}]
     * goodsSpecies : 2
     * goodsQuantity : 3
     * goodsAmount : 40
     * coupon : {"used":true,"couponId":10011,"couponName":"精酿2折券","deduction":36}
     * points : {"used":true,"points":300,"deduction":3}
     * deliver : {"takeaway":true,"deliverType":"自提/外送/直邮","shop":{"id":1001,"title":"中山路店(No.1001)","type":"旗舰店","icon":"https://images.hengnai.vip/shop/1001/icon","favorite":true,"address":"中山路201号","geo":"120.120023,10.131324","hours":"15:00-22:00","distance":"512m","status":"营业/打烊","abnormityMessage":"该门店正在装修中,暂未开始营业","working":true,"abnormityReason":"装修中"},"address":{"id":11,"title":"张三先生","addressee":"张三","suffix":"1:先生,2:女士","phone":"13800000001","address":"上海市闵行区桂平路481号","room":"20号楼6楼","alias":"万达信息股份有限公司","geo":"120.120023,10.131324","tag":"家、公司、学校","defaultAddress":false},"price":5,"preferential":true,"concPrice":0,"concDescription":"面价满55元免配送费","state":{"status":"3","name":"已送达","datetime":"2020-12-13 12:13:12"},"pickupNumber":902,"courierNumber":"SF201801010001","courier":"张三","courierMobile":"13800000001","journals":["16:10:12 已送出","16:32:40 已送达"]}
     * payment : {"amount":210,"preferential":true,"deduction":28,"concItems":["优惠券抵扣25元","积分抵扣3元"],"payment":182}
     * actions : [{"type":1,"name":"去支付","primary":true}]
     * pending : 退款：退款中
     * memos : ["订单取消原因：门店设备故障","补偿：赠送5折优惠券一张"]
     * orderTime : 2020-01-01 15:12:24
     * journals : ["下单时间：2020-01-01 16:12:14","支付时间：2020-01-01 16:12:26","接单时间：2020-01-01 16:13:20","送达时间：2020-01-01 16:45:00"]
     * abnormal : false
     * abnormityMessage : string
     * abnormityReason : string
     */

    private int id;
    private int type;
    private StatusBean status;
    private BookingBean booking;
    private MessageBean message;
    private int goodsSpecies;
    private int goodsQuantity;
    private double goodsAmount;
    private CouponBean coupon;
    private PointsBean points;
    private DeliverBean deliver;
    private PaymentBean payment;
    private String pending;
    private String orderTime;
    private boolean abnormal;
    private String abnormityMessage;
    private String abnormityReason;
    private List<ItemsBean> items;
    private List<ActionsBean> actions;
    private List<String> memos;
    private List<String> journals;

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

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
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

    public double getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(double goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public CouponBean getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponBean coupon) {
        this.coupon = coupon;
    }

    public PointsBean getPoints() {
        return points;
    }

    public void setPoints(PointsBean points) {
        this.points = points;
    }

    public DeliverBean getDeliver() {
        return deliver;
    }

    public void setDeliver(DeliverBean deliver) {
        this.deliver = deliver;
    }

    public PaymentBean getPayment() {
        return payment;
    }

    public void setPayment(PaymentBean payment) {
        this.payment = payment;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
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

    public List<String> getMemos() {
        return memos;
    }

    public void setMemos(List<String> memos) {
        this.memos = memos;
    }

    public List<String> getJournals() {
        return journals;
    }

    public void setJournals(List<String> journals) {
        this.journals = journals;
    }

    public static class StatusBean {
        /**
         * status : 3
         * name : 已接单
         * datetime : 2020-02-13 12:13:12
         * finished : true
         * state : 1
         */

        private String status;
        private String name;
        private String datetime;
        private boolean finished;
        private String state;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    public static class BookingBean {
        /**
         * booking : true
         * mode : 1
         * bookingDate : 2020-01-01
         * bookingTime : 17:00
         */

        private boolean booking;
        private String mode;
        private String bookingDate;
        private String bookingTime;

        public boolean isBooking() {
            return booking;
        }

        public void setBooking(boolean booking) {
            this.booking = booking;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
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
         * message : 请在{}内完成付款，过时订单将自动取消
         * data : 10:00
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

    public static class CouponBean {
        /**
         * used : true
         * couponId : 10011
         * couponName : 精酿2折券
         * deduction : 36
         */

        private boolean used;
        private int couponId;
        private String couponName;
        private double deduction;

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public int getCouponId() {
            return couponId;
        }

        public void setCouponId(int couponId) {
            this.couponId = couponId;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public double getDeduction() {
            return deduction;
        }

        public void setDeduction(double deduction) {
            this.deduction = deduction;
        }
    }

    public static class PointsBean {
        /**
         * used : true
         * points : 300
         * deduction : 3
         */

        private boolean used;
        private int points;
        private double deduction;

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public double getDeduction() {
            return deduction;
        }

        public void setDeduction(double deduction) {
            this.deduction = deduction;
        }
    }

    public static class DeliverBean {
        /**
         * takeaway : true
         * deliverType : 自提/外送/直邮
         * shop : {"id":1001,"title":"中山路店(No.1001)","type":"旗舰店","icon":"https://images.hengnai.vip/shop/1001/icon","favorite":true,"address":"中山路201号","geo":"120.120023,10.131324","hours":"15:00-22:00","distance":"512m","status":"营业/打烊","abnormityMessage":"该门店正在装修中,暂未开始营业","working":true,"abnormityReason":"装修中"}
         * address : {"id":11,"title":"张三先生","addressee":"张三","suffix":"1:先生,2:女士","phone":"13800000001","address":"上海市闵行区桂平路481号","room":"20号楼6楼","alias":"万达信息股份有限公司","geo":"120.120023,10.131324","tag":"家、公司、学校","defaultAddress":false}
         * price : 5
         * preferential : true
         * concPrice : 0
         * concDescription : 面价满55元免配送费
         * state : {"status":"3","name":"已送达","datetime":"2020-12-13 12:13:12"}
         * pickupNumber : 902
         * courierNumber : SF201801010001
         * courier : 张三
         * courierMobile : 13800000001
         * journals : ["16:10:12 已送出","16:32:40 已送达"]
         */

        private boolean takeaway;
        private String deliverType;
        private ShopBean shop;
        private AddressBean address;
        private double price;
        private boolean preferential;
        private double concPrice;
        private String concDescription;
        private StateBean state;
        private int pickupNumber;
        private String courierNumber;
        private String courier;
        private String courierMobile;
        private List<String> journals;

        public boolean isTakeaway() {
            return takeaway;
        }

        public void setTakeaway(boolean takeaway) {
            this.takeaway = takeaway;
        }

        public String getDeliverType() {
            return deliverType;
        }

        public void setDeliverType(String deliverType) {
            this.deliverType = deliverType;
        }

        public ShopBean getShop() {
            return shop;
        }

        public void setShop(ShopBean shop) {
            this.shop = shop;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public boolean isPreferential() {
            return preferential;
        }

        public void setPreferential(boolean preferential) {
            this.preferential = preferential;
        }

        public double getConcPrice() {
            return concPrice;
        }

        public void setConcPrice(double concPrice) {
            this.concPrice = concPrice;
        }

        public String getConcDescription() {
            return concDescription;
        }

        public void setConcDescription(String concDescription) {
            this.concDescription = concDescription;
        }

        public StateBean getState() {
            return state;
        }

        public void setState(StateBean state) {
            this.state = state;
        }

        public int getPickupNumber() {
            return pickupNumber;
        }

        public void setPickupNumber(int pickupNumber) {
            this.pickupNumber = pickupNumber;
        }

        public String getCourierNumber() {
            return courierNumber;
        }

        public void setCourierNumber(String courierNumber) {
            this.courierNumber = courierNumber;
        }

        public String getCourier() {
            return courier;
        }

        public void setCourier(String courier) {
            this.courier = courier;
        }

        public String getCourierMobile() {
            return courierMobile;
        }

        public void setCourierMobile(String courierMobile) {
            this.courierMobile = courierMobile;
        }

        public List<String> getJournals() {
            return journals;
        }

        public void setJournals(List<String> journals) {
            this.journals = journals;
        }

        public static class ShopBean {
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

        public static class AddressBean {
            /**
             * id : 11
             * title : 张三先生
             * addressee : 张三
             * suffix : 1:先生,2:女士
             * phone : 13800000001
             * address : 上海市闵行区桂平路481号
             * room : 20号楼6楼
             * alias : 万达信息股份有限公司
             * geo : 120.120023,10.131324
             * tag : 家、公司、学校
             * defaultAddress : false
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

        public static class StateBean {
            /**
             * status : 3
             * name : 已送达
             * datetime : 2020-12-13 12:13:12
             */

            private String status;
            private String name;
            private String datetime;

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDatetime() {
                return datetime;
            }

            public void setDatetime(String datetime) {
                this.datetime = datetime;
            }
        }
    }

    public static class PaymentBean {
        /**
         * amount : 210
         * preferential : true
         * deduction : 28
         * concItems : ["优惠券抵扣25元","积分抵扣3元"]
         * payment : 182
         */

        private double amount;
        private boolean preferential;
        private double deduction;
        private double payment;
        private List<String> concItems;

        public double getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public boolean isPreferential() {
            return preferential;
        }

        public void setPreferential(boolean preferential) {
            this.preferential = preferential;
        }

        public double getDeduction() {
            return deduction;
        }

        public void setDeduction(int deduction) {
            this.deduction = deduction;
        }

        public double getPayment() {
            return payment;
        }

        public void setPayment(double payment) {
            this.payment = payment;
        }

        public List<String> getConcItems() {
            return concItems;
        }

        public void setConcItems(List<String> concItems) {
            this.concItems = concItems;
        }
    }

    public static class ItemsBean {
        /**
         * goodsId : 12
         * goodsName : 青岛原浆1L
         * specCode : 1L
         * specName : 1L
         * image : https://images.hengnai.vip/goods/a.png
         * quantity : 1
         * price : 40
         * preferential : true
         * concDescription : 新品8折
         * concPrice : 32
         * abnormal : false
         * abnormityMessage : 商品暂无法购买
         * abnormityReason : 库存不足
         */

        private int goodsId;
        private String goodsName;
        private String specCode;
        private String specName;
        private String image;
        private int quantity;
        private double price;
        private boolean preferential;
        private String concDescription;
        private double concPrice;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public boolean isPreferential() {
            return preferential;
        }

        public void setPreferential(boolean preferential) {
            this.preferential = preferential;
        }

        public String getConcDescription() {
            return concDescription;
        }

        public void setConcDescription(String concDescription) {
            this.concDescription = concDescription;
        }

        public double getConcPrice() {
            return concPrice;
        }

        public void setConcPrice(double concPrice) {
            this.concPrice = concPrice;
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
         * type : 1
         * name : 去支付
         * primary : true
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
