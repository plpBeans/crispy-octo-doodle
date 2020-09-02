package vip.hengnai.wine.entity;

/**
 * on 2019/12/18.
 *
 * @author hua.
 */

public class PayEntity {
    private String id;
    private String name;

    public String getId() {
        return id == null ? "" : id;
    }

    public PayEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public PayEntity setName(String name) {
        this.name = name;
        return this;
    }
}
