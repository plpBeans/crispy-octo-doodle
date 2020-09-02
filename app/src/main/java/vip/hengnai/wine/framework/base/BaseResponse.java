package vip.hengnai.wine.framework.base;

/**
 *
 * @author hua
 * @date 2019/8/21
 */

public class BaseResponse {
    /**
     * message : 获取列表成功
     * data :
     * status : 1
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
