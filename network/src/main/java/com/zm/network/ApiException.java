package com.zm.network;


/**
 * @范亮亮
 * @deprecated  自定义网络请求异常错误
 */
public class ApiException extends Exception{
    private String errMsg;

    public ApiException(String errMsg){
        this.errMsg=errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
