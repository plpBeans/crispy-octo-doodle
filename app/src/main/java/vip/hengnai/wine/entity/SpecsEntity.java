package vip.hengnai.wine.entity;

/**
 * on 2019/12/7.
 *商品规格
 * @author hua.
 */

public class SpecsEntity {
    private String specs;
    private String price;

    public String getSpecs() {
        return specs == null ? "" : specs;
    }

    public SpecsEntity setSpecs(String specs) {
        this.specs = specs;
        return this;
    }

    public String getPrice() {
        return price == null ? "" : price;
    }

    public SpecsEntity setPrice(String price) {
        this.price = price;
        return this;
    }
}
