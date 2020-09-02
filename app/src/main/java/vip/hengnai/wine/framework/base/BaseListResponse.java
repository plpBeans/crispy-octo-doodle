package vip.hengnai.wine.framework.base;

import java.util.List;

/**
 *
 * @author hua
 * @date 2019/8/21
 */

public class BaseListResponse<T> extends BaseResponse{

    /**
     * response :
     */

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
