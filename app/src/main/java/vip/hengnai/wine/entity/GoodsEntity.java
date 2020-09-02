package vip.hengnai.wine.entity;


import java.io.Serializable;

/**
 * @author Hugh
 */
public class GoodsEntity implements Serializable {
    /**
     * 判断是否为省份，来进行加载数据
     */
    public boolean isTitle;
    public String bigName;

    public String getBigName() {
        return bigName == null ? "" : bigName;
    }

    public GoodsEntity setBigName(String bigName) {
        this.bigName = bigName;
        return this;
    }

    /**
     * 一个position，同时将城市与省份绑定
     */
    public String tag;
    /**
     * id : 12
     * code : HP01
     * category : A001
     * name : 精酿原浆1L
     * memo : 备注
     * price : 40
     * preferential : true
     * concPrice : 32
     * limit : 20
     * priceRange : 30~50
     * concPriceRange : 24~40
     * specs : 大/中/小
     * multiple : false
     * sellout : false
     * selling : true
     * abnormityMessage : 商品暂无法购买
     * abnormityReason : 库存不足
     */

    private int id;
    private String code;
    private String category;
    private String name;
    private String memo;
    private String image;
    private double price;
    private boolean preferential;
    private double concPrice;
    private int limit;
    private String priceRange;
    private String concPriceRange;
    private String specs;
    private boolean multiple;
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
    public String getImage() {
        return image == null ? "" : image;
    }

    public GoodsEntity setImage(String image) {
        this.image = image;
        return this;
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

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
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



    public void setTitle(boolean title)
    {
        isTitle=title;
    }

    public boolean isTitle()
    {
        return  isTitle;
    }

    public void setTag(String tag)
    {
        this.tag=tag;
    }
    public String getTag()
    {
        return tag;
    }
}
