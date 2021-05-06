package com.zm.liaopei.app;

import android.content.Context;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author fanliangliang
 * @description: 改类作一些初始化的操作
 * @date : 2021/4/25 16:54
 */
public class ApplicationConfig {
    private static BaseApplication appInstance;
    private static ApplicationConfig insance;

    public ApplicationConfig(){

    }

    public static void initApplicationConfig(final BaseApplication application){

        appInstance=application;
        insance=new ApplicationConfig();
        //下面两句话必须放到init前面,否则配置无效
        ARouter.openLog();  //打印ARouter日志
        ARouter.openDebug();  //开启ARouter的调试模式(如果在InstantRun模式下运行,必须开启调试模式,线上版本需要关闭，否则有安全风险),
        ARouter.init(getApplication());


    }

    /**
     *获取Application
     * @return
     */
    public static BaseApplication getApplication(){
        if (appInstance!=null){
            return appInstance;
        }
        throw new NullPointerException("u should init first");
    }

    public static ApplicationConfig getInsance(){
        return insance;
    }

    /**
     * 全局获取getApplicationContext
     * @return
     */
    public static Context getContext(){
        if (getApplication()!=null){
            return  getApplication().getApplicationContext();
        }
        throw new NullPointerException("u should init first");
    }



}
