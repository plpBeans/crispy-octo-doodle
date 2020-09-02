package vip.hengnai.wine.framework.base;

/**
 *
 * @author hua
 * @date 2019/8/21
 */

public class MyException extends Exception {
    private int mCode;
    private String mMessage;

    public MyException() {
    }

    public MyException(int code, String message) {
        setCode(code);
        setMessage(message);
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}
