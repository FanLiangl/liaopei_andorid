package com.zm.network.common;


import com.alibaba.android.arouter.facade.template.IProvider;


public interface IUrlObtainService extends IProvider {

    String[] urlObtain();

    String getDefaultUrl();
}
