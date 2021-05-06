package com.zm.network.common;

import android.content.Context;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface WindowManagerService extends IProvider{

    /**
     * 获取上层组件中的FloatWindowManager实例
     * @return
     */
    Object getFloatWindowManager(Context context);

    void removeFloatWindowManager(Context context);

    /**
     * 初始化FloatWindowManager
     * @param context
     */
    void initManager(Context context);
}
