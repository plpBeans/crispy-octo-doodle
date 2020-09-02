package vip.hengnai.wine.entity;

import java.util.List;

/**
 * on 2020/1/6.
 *
 * @author hua.
 */

public class GoodsDetailEntity {

    /**
     * id : 5
     * code : HP05
     * category : A002
     * name : 青岛啤酒精酿IPA
     * masterTitle : 总酿说
     * masterQuotation : 青岛啤酒总酿造师说:...
     * memo : 商品的酿造工艺、口感等等
     * specs : [{"id":50,"code":null,"name":"1L","description":"1瓶装","packageUnit":"瓶","packageUnitAmount":1,"measureUnit":"L","measureUnitAmount":1,"price":40,"preferential":true,"concPrice":25,"limit":20,"sellout":false,"selling":true,"abnormityMessage":"","abnormityReason":""}]
     */

    private int id;
    private String code;
    private String category;
    private String name;
    private String masterTitle;
    private String masterQuotation;
    private String memo;
    private List<SpecsBean> specs;

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

    public String getMasterTitle() {
        return masterTitle;
    }

    public void setMasterTitle(String masterTitle) {
        this.masterTitle = masterTitle;
    }

    public String getMasterQuotation() {
        return masterQuotation;
    }

    public void setMasterQuotation(String masterQuotation) {
        this.masterQuotation = masterQuotation;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<SpecsBean> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecsBean> specs) {
        this.specs = specs;
    }

    public static class SpecsBean {
        /**
         * id : 50
         * code : null
         * name : 1L
         * description : 1瓶装
         * packageUnit : 瓶
         * packageUnitAmount : 1
         * measureUnit : L
         * measureUnitAmount : 1
         * price : 40
         * preferential : true
         * concPrice : 25
         * limit : 20
         * sellout : false
         * selling : true
         * abnormityMessage :
         * abnormityReason :
         */

        private int id;
        private String code;
        private String name;
        private String description;
        private String packageUnit;
        private int packageUnitAmount;
        private String measureUnit;
        private int measureUnitAmount;
        private double price;
        private boolean preferential;
        private double concPrice;
        private int limit;
        private boolean sellout;
        private boolean selling;
        private String abnormityMessage;
        private String abnormityReason;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPackageUnit() {
            return packageUnit;
        }

        public void setPackageUnit(String packageUnit) {
            this.packageUnit = packageUnit;
        }

        public int getPackageUnitAmount() {
            return packageUnitAmount;
        }

        public void setPackageUnitAmount(int packageUnitAmount) {
            this.packageUnitAmount = packageUnitAmount;
        }

        public String getMeasureUnit() {
            return measureUnit;
        }

        public void setMeasureUnit(String measureUnit) {
            this.measureUnit = measureUnit;
        }

        public int getMeasureUnitAmount() {
            return measureUnitAmount;
        }

        public void setMeasureUnitAmount(int measureUnitAmount) {
            this.measureUnitAmount = measureUnitAmount;
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
