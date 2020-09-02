package vip.hengnai.wine.entity;

import java.util.List;

/**
 * on 2020/1/3.
 *
 * @author hua.
 */

public class MenuEntity {


    /**
     * takeaway : false
     * menus : [{"code":"favorites","name":"我的最爱","icon":"https://images.hengnai.vip/category/favorites/icon.png","memo":"","goods":[{"id":1001,"code":"1001","category":"favorites","name":"我的最爱商品1001","memo":"","price":10,"preferential":false,"concPrice":10,"limit":20,"priceRange":"10.0","concPriceRange":"10.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":1002,"code":"1002","category":"favorites","name":"我的最爱商品1002","memo":"","price":20,"preferential":false,"concPrice":20,"limit":20,"priceRange":"20.0","concPriceRange":"20.0","specs":null,"multiple":false,"sellout":true,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":1003,"code":"1003","category":"favorites","name":"我的最爱商品1003","memo":"","price":30,"preferential":false,"concPrice":30,"limit":20,"priceRange":"30.0","concPriceRange":"30.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null}]},{"code":"popularity","name":"人气TOP","icon":"https://images.hengnai.vip/category/popularity/icon.png","memo":"","goods":[{"id":2001,"code":"2001","category":"popularity","name":"人气TOP商品2001","memo":"","price":10,"preferential":false,"concPrice":10,"limit":20,"priceRange":"10.0","concPriceRange":"10.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":2002,"code":"2002","category":"popularity","name":"人气TOP商品2002","memo":"","price":20,"preferential":false,"concPrice":20,"limit":20,"priceRange":"20.0","concPriceRange":"20.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null}]},{"code":"new","name":"新品上市","icon":"https://images.hengnai.vip/category/new/icon.png","memo":"","goods":[{"id":3001,"code":"3001","category":"new","name":"新品上市商品3001","memo":"","price":10,"preferential":true,"concPrice":8,"limit":20,"priceRange":"10.0","concPriceRange":"8.0","specs":null,"multiple":false,"sellout":true,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":3002,"code":"3002","category":"new","name":"新品上市商品3002","memo":"","price":20,"preferential":true,"concPrice":16,"limit":20,"priceRange":"20.0","concPriceRange":"16.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null}]},{"code":"brewed","name":"精酿打包","icon":"https://images.hengnai.vip/category/brewed/icon.png","memo":"","goods":[{"id":4001,"code":"4001","category":"brewed","name":"精酿打包商品4001","memo":"","price":10,"preferential":false,"concPrice":10,"limit":20,"priceRange":"10.0","concPriceRange":"10.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":4002,"code":"4002","category":"brewed","name":"精酿打包商品4002","memo":"","price":20,"preferential":false,"concPrice":20,"limit":20,"priceRange":"20.0","concPriceRange":"20.0","specs":null,"multiple":false,"sellout":true,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":4003,"code":"4003","category":"brewed","name":"精酿打包商品4003","memo":"","price":30,"preferential":false,"concPrice":30,"limit":20,"priceRange":"30.0","concPriceRange":"30.0","specs":null,"multiple":false,"sellout":true,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":4004,"code":"4004","category":"brewed","name":"精酿打包商品4004","memo":"","price":40,"preferential":false,"concPrice":40,"limit":20,"priceRange":"40.0","concPriceRange":"40.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":4005,"code":"4005","category":"brewed","name":"精酿打包商品4005","memo":"","price":50,"preferential":false,"concPrice":50,"limit":20,"priceRange":"50.0","concPriceRange":"50.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null}]},{"code":"fresh","name":"七日极鲜","icon":"https://images.hengnai.vip/category/fresh/icon.png","memo":"预计1周内送达","goods":[{"id":5001,"code":"5001","category":"fresh","name":"七日极鲜商品5001","memo":"","price":10,"preferential":false,"concPrice":10,"limit":20,"priceRange":"10.0","concPriceRange":"10.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":5002,"code":"5002","category":"fresh","name":"七日极鲜商品5002","memo":"","price":20,"preferential":false,"concPrice":20,"limit":20,"priceRange":"20.0","concPriceRange":"20.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null}]},{"code":"fashion","name":"周边潮品","icon":"https://images.hengnai.vip/category/fashion/icon.png","memo":"","goods":[{"id":5001,"code":"5001","category":"fashion","name":"周边潮品商品5001","memo":"","price":10,"preferential":false,"concPrice":10,"limit":20,"priceRange":"10.0","concPriceRange":"10.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":5002,"code":"5002","category":"fashion","name":"周边潮品商品5002","memo":"","price":20,"preferential":false,"concPrice":20,"limit":20,"priceRange":"20.0","concPriceRange":"20.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":5003,"code":"5003","category":"fashion","name":"周边潮品商品5003","memo":"","price":30,"preferential":false,"concPrice":30,"limit":20,"priceRange":"30.0","concPriceRange":"30.0","specs":null,"multiple":false,"sellout":true,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":5004,"code":"5004","category":"fashion","name":"周边潮品商品5004","memo":"","price":40,"preferential":false,"concPrice":40,"limit":20,"priceRange":"40.0","concPriceRange":"40.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null}]}]
     * shop : {"id":1001,"title":"中山路店(No.1001)","type":"旗舰店","icon":"https://images.hengnai.vip/shop/1001/icon","favorite":true,"address":"中山路201号","geo":"120.120023,10.131324","hours":"15:00-22:00","distance":"512m","status":"营业","working":true,"abnormityMessage":"该门店正在装修中,暂未开始营业","abnormityReason":"装修中"}
     * address : {"id":11,"title":"张三先生","addressee":"张三","suffix":"1:先生,2:女士","address":"上海市闵行区桂平路481号","room":"20号楼6楼","alias":"万达信息股份有限公司","geo":"120.120023,10.131324","tag":"家、公司、学校","defaultAddress":false}
     */

    private boolean takeaway;
    private ShopBean shop;
    private AddressBean address;
    private List<MenusBean> menus;

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

    public List<MenusBean> getMenus() {
        return menus;
    }

    public void setMenus(List<MenusBean> menus) {
        this.menus = menus;
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
         * status : 营业
         * working : true
         * abnormityMessage : 该门店正在装修中,暂未开始营业
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
        private boolean working;
        private String abnormityMessage;
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
    }

    public static class AddressBean {
        /**
         * id : 11
         * title : 张三先生
         * addressee : 张三
         * suffix : 1:先生,2:女士
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
        private String address;
        private String room;
        private String alias;
        private String geo;
        private String phone;

        public String getPhone() {
            return phone == null ? "" : phone;
        }

        public AddressBean setPhone(String phone) {
            this.phone = phone;
            return this;
        }

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

    public static class MenusBean {
        /**
         * code : favorites
         * name : 我的最爱
         * icon : https://images.hengnai.vip/category/favorites/icon.png
         * memo :
         * goods : [{"id":1001,"code":"1001","category":"favorites","name":"我的最爱商品1001","memo":"","price":10,"preferential":false,"concPrice":10,"limit":20,"priceRange":"10.0","concPriceRange":"10.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":1002,"code":"1002","category":"favorites","name":"我的最爱商品1002","memo":"","price":20,"preferential":false,"concPrice":20,"limit":20,"priceRange":"20.0","concPriceRange":"20.0","specs":null,"multiple":false,"sellout":true,"selling":true,"abnormityMessage":null,"abnormityReason":null},{"id":1003,"code":"1003","category":"favorites","name":"我的最爱商品1003","memo":"","price":30,"preferential":false,"concPrice":30,"limit":20,"priceRange":"30.0","concPriceRange":"30.0","specs":null,"multiple":false,"sellout":false,"selling":true,"abnormityMessage":null,"abnormityReason":null}]
         */

        private String code;
        private String name;
        private String icon;
        private String memo;
        private List<GoodsEntity> goods;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public List<GoodsEntity> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsEntity> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * id : 1001
             * code : 1001
             * category : favorites
             * name : 我的最爱商品1001
             * memo :
             * price : 10.0
             * preferential : false
             * concPrice : 10.0
             * limit : 20
             * priceRange : 10.0
             * concPriceRange : 10.0
             * specs : null
             * multiple : false
             * sellout : false
             * selling : true
             * abnormityMessage : null
             * abnormityReason : null
             */

            private int id;
            private String code;
            private String category;
            private String name;
            private String memo;
            private double price;
            private boolean preferential;
            private double concPrice;
            private int limit;
            private String priceRange;
            private String concPriceRange;
            private Object specs;
            private boolean multiple;
            private boolean sellout;
            private boolean selling;
            private Object abnormityMessage;
            private Object abnormityReason;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
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

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public String getPriceRange() {
                return priceRange;
            }

            public void setPriceRange(String priceRange) {
                this.priceRange = priceRange;
            }

            public String getConcPriceRange() {
                return concPriceRange;
            }

            public void setConcPriceRange(String concPriceRange) {
                this.concPriceRange = concPriceRange;
            }

            public Object getSpecs() {
                return specs;
            }

            public void setSpecs(Object specs) {
                this.specs = specs;
            }

            public boolean isMultiple() {
                return multiple;
            }

            public void setMultiple(boolean multiple) {
                this.multiple = multiple;
            }

            public boolean isSellout() {
                return sellout;
            }

            public void setSellout(boolean sellout) {
                this.sellout = sellout;
            }

            public boolean isSelling() {
                return selling;
            }

            public void setSelling(boolean selling) {
                this.selling = selling;
            }

            public Object getAbnormityMessage() {
                return abnormityMessage;
            }

            public void setAbnormityMessage(Object abnormityMessage) {
                this.abnormityMessage = abnormityMessage;
            }

            public Object getAbnormityReason() {
                return abnormityReason;
            }

            public void setAbnormityReason(Object abnormityReason) {
                this.abnormityReason = abnormityReason;
            }
        }
    }
}
