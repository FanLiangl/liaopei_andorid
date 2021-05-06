package com.zm.network.base;

/**
 * @author fanll
 * @param <T>
 * @deprecated 网络请求数据基类 此类可以根据接口返回规则进行适当的更改
 */
public class BaseRespone <T>{

    private boolean result;

    private int errorCode;

    private String errMsg;

    private T data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
