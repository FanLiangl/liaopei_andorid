package com.zm.liaopei.app;

import android.app.Application;
import android.content.Context;

/**
 * @author fanliangliang
 * @description: 通用baseApplication
 * @date : 2021/4/25 16:53
 */
public class BaseApplication extends Application{
    private BaseApplication baseApplication;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationConfig.initApplicationConfig(this);
        if (baseApplication==null){
            baseApplication=this;
        }


    }
}
