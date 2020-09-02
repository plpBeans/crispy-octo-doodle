package vip.hengnai.wine.entity;

/**
 * on 2020/2/26.
 *
 * @author hua.
 */

public class ChangeCartEntity {
    /**
     * groupId : 1
     * amount : 40
     * preferential : true
     * concAmount : 32
     * concDescription : 用券可抵用¥5
     */

    private int groupId;
    private double amount;
    private boolean preferential;
    private double concAmount;
    private String concDescription;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

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

    public double getConcAmount() {
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
}
