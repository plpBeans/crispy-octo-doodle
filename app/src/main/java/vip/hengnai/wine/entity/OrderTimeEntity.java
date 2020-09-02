package vip.hengnai.wine.entity;

import java.util.List;

/**
 * on 2020/2/23.
 *
 * @author hua.
 */

public class OrderTimeEntity {
    /**
     * mode : 1
     * date : ["2020-01-01","2020-01-02"]
     * time : ["16:00","16:30","17:30"]
     */

    private String mode;
    private List<String> date;
    private List<String> time;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }
}
