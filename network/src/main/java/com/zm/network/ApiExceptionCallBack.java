package com.zm.network;

import com.zm.network.base.BaseRespone;

/**
 * @author fanll
 * @deprecated 请求发生错误的时候 使用该接口
 */
public interface ApiExceptionCallBack {
    /**
     * 抽象回调方法
     * @param respone
     */
    void CallBack(BaseRespone respone);
}
