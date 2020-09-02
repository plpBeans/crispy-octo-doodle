package vip.hengnai.wine.entity;

/**
 * on 2019/12/25.
 *
 * @author hua.
 */

public class MobileCodeEntity {
    private String key;

    public String getKey() {
        return key == null ? "" : key;
    }

    public MobileCodeEntity setKey(String key) {
        this.key = key;
        return this;
    }
}
