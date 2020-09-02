package vip.hengnai.wine.entity;

/**
 * on 2019/12/11.
 *
 * @author hua.
 */

public class OrderCouponEntity {

    /**
     * id : 1
     * name : 精酿1折券
     * discount : 1折
     * expiration : 2020-03-20
     * remaining : 剩1天
     * rule : 本券可用于精酿的1折优惠购买...
     * deduction : 35
     * used : false
     */

    private int id;
    private String name;
    private String discount;
    private String expiration;
    private String remaining;
    private String rule;
    private double deduction;
    private boolean used;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
