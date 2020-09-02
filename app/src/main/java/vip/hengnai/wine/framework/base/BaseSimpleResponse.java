package vip.hengnai.wine.framework.base;

/**
 *
 * @author hua
 * @date 2019/8/21
 */

public class BaseSimpleResponse<T> extends BaseResponse{

    /**
     * data :
     */

    private T data;

    public T getData() {
        return data;
    }

}
