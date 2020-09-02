package vip.hengnai.wine.entity;

/**
 * on 2019/12/18.
 *
 * @author hua.
 */

public class DiscussEntity {
    private String content;

    public String getContent() {
        return content == null ? "" : content;
    }

    public DiscussEntity setContent(String content) {
        this.content = content;
        return this;
    }
}
