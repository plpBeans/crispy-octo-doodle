package vip.hengnai.wine.entity;

import java.util.List;

/**
 * on 2020/2/5.
 *
 * @author hua.
 */

public class OrderConfirmEntity {

    /**
     * id : 1
     * type : 1
     * status : 立即自取
     * message : 预计16:51送达
     * bookable : false
     * bookingInfo : {"booking":true,"mode":"1","bookingDate":"2020-01-01","bookingTime":"17:00"}
     * items : [{"goodsId":12,"goodsName":"青岛原浆1L","specCode":"1L","specName":"1L","image":"https://images.hengnai.vip/goods/a.png","quantity":1,"price":40,"preferential":true,"concDescription":"新品8折","concPrice":32,"abnormal":false,"abnormityMessage":"商品暂无法购买","abnormityReason":"库存不足"}]
     * goodsSpecies : 2
     * goodsQuantity : 3
     * goodsAmount : 40
     * coupon : {"avaliable":true,"couponCount":3,"couponId":10011,"couponName":"精酿2折券","deduction":36}
     * points : {"avaliable":true,"avaliablePoints":2000,"avaliableDuduction":20,"used":true,"usedPoints":300,"deduction":3}
     * deliver : {"takeaway":true,"shop":{"id":1001,"title":"中山路店(No.1001)","type":"旗舰店","icon":"https://images.hengnai.vip/shop/1001/icon","favorite":true,"address":"中山路201号","geo":"120.120023,10.131324","hours":"15:00-22:00","distance":"512m","status":"营业/打烊","abnormityMessage":"该门店正在装修中,暂未开始营业","working":true,"abnormityReason":"装修中"},"address":{"id":11,"title":"张三先生","addressee":"张三","suffix":"1:先生,2:女士","phone":"13800000001","address":"上海市闵行区桂平路481号","room":"20号楼6楼","alias":"万达信息股份有限公司","geo":"120.120023,10.131324","tag":"家、公司、学校","defaultAddress":false},"price":5,"preferential":true,"concPrice":0,"concDescription":"面价满55元免配送费","concRules":["面价满55元免配送费"]}
     * amount : 210
     * preferential : true
     * deduction : 28
     * concItems : ["优惠券抵扣25元","积分抵扣3元"]
     * payment : 182
     * channels : [{"type":"alipay/wx","name":"支付宝/微信支付","icon":"https://images.hengnai.vip/payment/alipay/icon","memo":"抽matepro30","recommended":true}]
     * abnormal : false
     * abnormityMessage : 该门店当前无法接单，请选择其他打烊
     * abnormityReason : 店铺设备故障
     */

    private int id;
    private String type;
    private String status;
    private String message;
    private boolean bookable;
    private BookingInfoBean bookingInfo;
    private int goodsSpecies;
    private int goodsQuantity;
    private double goodsAmount;
    private CouponBean coupon;
    private PointsBean points;
    private DeliverBean deliver;
    private double amount;
    private boolean preferential;
    private double deduction;
    private double payment;
    private boolean abnormal;
    private String abnormityMessage;
    private String abnormityReason;
    private List<ItemsBean> items;
    private List<String> concItems;
    private List<ChannelsBean> channels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isBookable() {
        return bookable;
    }

    public void setBookable(boolean bookable) {
        this.bookable = bookable;
    }

    public BookingInfoBean getBookingInfo() {
        return bookingInfo;
    }

    public void setBookingInfo(BookingInfoBean bookingInfo) {
        this.bookingInfo = bookingInfo;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
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

    public List<String> getConcItems() {
        return concItems;
    }

    public void setConcItems(List<String> concItems) {
        this.concItems = concItems;
    }

    public List<ChannelsBean> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelsBean> channels) {
        this.channels = channels;
    }

    public static class BookingInfoBean {
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

    public static class CouponBean {
        /**
         * avaliable : true
         * couponCount : 3
         * couponId : 10011
         * couponName : 精酿2折券
         * deduction : 36
         */

        private boolean avaliable;
        private int couponCount;
        private int couponId;
        private String couponName;
        private int deduction;

        public boolean isAvaliable() {
            return avaliable;
        }

        public void setAvaliable(boolean avaliable) {
            this.avaliable = avaliable;
        }

        public int getCouponCount() {
            return couponCount;
        }

        public void setCouponCount(int couponCount) {
            this.couponCount = couponCount;
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

        public int getDeduction() {
            return deduction;
        }

        public void setDeduction(int deduction) {
            this.deduction = deduction;
        }
    }

    public static class PointsBean {
        /**
         * avaliable : true
         * avaliablePoints : 2000
         * avaliableDuduction : 20
         * used : true
         * usedPoints : 300
         * deduction : 3
         */

        private boolean avaliable;
        private int avaliablePoints;
        private int avaliableDuduction;
        private boolean used;
        private int usedPoints;
        private int deduction;

        public boolean isAvaliable() {
            return avaliable;
        }

        public void setAvaliable(boolean avaliable) {
            this.avaliable = avaliable;
        }

        public int getAvaliablePoints() {
            return avaliablePoints;
        }

        public void setAvaliablePoints(int avaliablePoints) {
            this.avaliablePoints = avaliablePoints;
        }

        public int getAvaliableDuduction() {
            return avaliableDuduction;
        }

        public void setAvaliableDuduction(int avaliableDuduction) {
            this.avaliableDuduction = avaliableDuduction;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public int getUsedPoints() {
            return usedPoints;
        }

        public void setUsedPoints(int usedPoints) {
            this.usedPoints = usedPoints;
        }

        public int getDeduction() {
            return deduction;
        }

        public void setDeduction(int deduction) {
            this.deduction = deduction;
        }
    }

    public static class DeliverBean {
        /**
         * takeaway : true
         * shop : {"id":1001,"title":"中山路店(No.1001)","type":"旗舰店","icon":"https://images.hengnai.vip/shop/1001/icon","favorite":true,"address":"中山路201号","geo":"120.120023,10.131324","hours":"15:00-22:00","distance":"512m","status":"营业/打烊","abnormityMessage":"该门店正在装修中,暂未开始营业","working":true,"abnormityReason":"装修中"}
         * address : {"id":11,"title":"张三先生","addressee":"张三","suffix":"1:先生,2:女士","phone":"13800000001","address":"上海市闵行区桂平路481号","room":"20号楼6楼","alias":"万达信息股份有限公司","geo":"120.120023,10.131324","tag":"家、公司、学校","defaultAddress":false}
         * price : 5
         * preferential : true
         * concPrice : 0
         * concDescription : 面价满55元免配送费
         * concRules : ["面价满55元免配送费"]
         */

        private boolean takeaway;
        private ShopBean shop;
        private AddressBean address;
        private double price;
        private boolean preferential;
        private double concPrice;
        private String concDescription;
        private List<String> concRules;

        public boolean isTakeaway() {
            return takeaway;
        }

        public void setTakeaway(boolean takeaway) {
            this.takeaway = takeaway;
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

        public List<String> getConcRules() {
            return concRules;
        }

        public void setConcRules(List<String> concRules) {
            this.concRules = concRules;
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

    public static class ChannelsBean {
        /**
         * type : alipay/wx
         * name : 支付宝/微信支付
         * icon : https://images.hengnai.vip/payment/alipay/icon
         * memo : 抽matepro30
         * recommended : true
         */

        private String type;
        private String name;
        private String icon;
        private String memo;
        private boolean recommended;

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public boolean isRecommended() {
            return recommended;
        }

        public void setRecommended(boolean recommended) {
            this.recommended = recommended;
        }
    }
}
