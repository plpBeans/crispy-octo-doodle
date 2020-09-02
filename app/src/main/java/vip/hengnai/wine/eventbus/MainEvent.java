package vip.hengnai.wine.eventbus;

/**
 *
 * @author hua
 */
public class MainEvent {
    /**
     * 首页跳转第几个fragment
     */
   private String index;

    public String getIndex() {
        return index == null ? "" : index;
    }

    public MainEvent setIndex(String index) {
        this.index = index;
        return this;
    }
}
