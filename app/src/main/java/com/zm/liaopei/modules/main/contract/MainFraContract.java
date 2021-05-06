package com.zm.liaopei.modules.main.contract;

import com.zm.liaopei.base.BaseView;

/**
 * @author fanliangliang
 * @description:
 * @date : 2021/4/26 17:42
 */
public interface MainFraContract extends BaseView {
    /**
     * 数据请求成功
     */
    void onSuccess();

    /**
     * 数据请求失败
     * @param message
     */
    void onFail(String message);

}
