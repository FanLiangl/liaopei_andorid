package com.zm.network.common;

import com.alibaba.android.arouter.facade.template.IProvider;


public interface CommonService extends IProvider {

    String getIdentify();

    String getSig();

    String getIdentifierPre();

    void joinTxBigGroup();

    void checkNetworkAvailable();

    void creatRoomNotification(String s);
}
