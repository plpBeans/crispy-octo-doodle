package vip.hengnai.wine.entity;

import java.util.List;

/**
 * on 2019/12/9.
 *
 * @author hua.
 */

public class CartEntity {

    /**
     * groupCount : 1
     * itemCount : 3
     * groups : [{"groupId":1,"shopId":1,"groupName":"精酿饮品","deliverType":"自提/外送","itemCount":1,"amount":40,"preferential":true,"concAmount":32,"concDescription":"用券可抵用¥5","items":[{"itemId":1,"goodsId":12,"goodsName":"青岛原浆1L","specId":12,"specName":"1L","quantity":12,"price":40,"preferential":true,"concPrice":32,"selling":true,"abnormityMessage":"商品暂无法购买","abnormityReason":"库存不足"}]}]
     */

    private int groupCount;
    private int itemCount;
    private List<GroupsBean> groups;

    public int getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(int groupCount) {
        this.groupCount = groupCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<GroupsBean> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupsBean> groups) {
        this.groups = groups;
    }

    public static class GroupsBean {
        /**
         * groupId : 1
         * shopId : 1
         * groupName : 精酿饮品
         * deliverType : 自提/外送
         * itemCount : 1
         * amount : 40
         * preferential : true
         * concAmount : 32
         * concDescription : 用券可抵用¥5
         * items : [{"itemId":1,"goodsId":12,"goodsName":"青岛原浆1L","specId":12,"specName":"1L","quantity":12,"price":40,"preferential":true,"concPrice":32,"selling":true,"abnormityMessage":"商品暂无法购买","abnormityReason":"库存不足"}]
         */

        private int groupId;
        private int shopId;
        private String groupName;
        private String deliverType;
        private int itemCount;
        private int amount;
        private boolean preferential;
        private int concAmount;
        private String concDescription;
        private List<ItemsBean> items;

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getDeliverType() {
            return deliverType;
        }

        public void setDeliverType(String deliverType) {
            this.deliverType = deliverType;
        }

        public int getItemCount() {
            return itemCount;
        }

        public void setItemCount(int itemCount) {
            this.itemCount = itemCount;
        }

        public int getAmount() {
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

        public int getConcAmount() {
            return concAmount;
        }

        public void setConcAmount(int concAmount) {
            this.concAmount = concAmount;
        }

        public String getConcDescription() {
            return concDescription;
        }

        public void setConcDescription(String concDescription) {
            this.concDescription = concDescription;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * itemId : 1
             * goodsId : 12
             * goodsName : 青岛原浆1L
             * specId : 12
             * specName : 1L
             * quantity : 12
             * price : 40
             * preferential : true
             * concPrice : 32
             * selling : true
             * image: "https://images.hengnai.vip/goods/a.png",
             * abnormityMessage : 商品暂无法购买
             * abnormityReason : 库存不足
             */

            private int itemId;
            private int goodsId;
            private String goodsName;
            private int specId;
            private String specName;
            private int quantity;
            private double price;
            private boolean preferential;
            private double concPrice;
            private boolean selling;
            private String abnormityMessage;
            private String abnormityReason;
            private String image;

            public String getImage() {
                return image == null ? "" : image;
            }

            public ItemsBean setImage(String image) {
                this.image = image;
                return this;
            }

            private boolean isChoose;

            public boolean isChoose() {
                return isChoose;
            }

            public ItemsBean setChoose(boolean choose) {
                isChoose = choose;
                return this;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

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

            public int getSpecId() {
                return specId;
            }

            public void setSpecId(int specId) {
                this.specId = specId;
            }

            public String getSpecName() {
                return specName;
            }

            public void setSpecName(String specName) {
                this.specName = specName;
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

            public double getConcPrice() {
                return concPrice;
            }

            public void setConcPrice(double concPrice) {
                this.concPrice = concPrice;
            }

            public boolean isSelling() {
                return selling;
            }

            public void setSelling(boolean selling) {
                this.selling = selling;
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
    }
}
