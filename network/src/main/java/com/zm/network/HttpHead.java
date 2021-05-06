package com.zm.network;

import android.content.Context;
import android.os.Build;
import java.util.HashMap;
import java.util.Map;
import io.reactivex.android.BuildConfig;

/**
 * @author fanll
 * @deprecated 接口请求头文件
 */
public class HttpHead {
    public static final long READ_TIME_OUT = 30;
    public static final long CONNECT_TIME_OUT = 30;
    public static final long WRITE_TIME_OUT = 30;
    /**
     * 渠道号头参数 Key
     */
    private static final String PHONE_BRAND = "phoneBrand";
    private static final String PHONE_SYSTEM = "phoneSystem";
    private static final String PHONE_MODELS = "phoneModels";
    private static final String APP_MARKET = "appMarket";
    private static final String APP_VERSIONCODE = "appVersionCode";
    private static final String APP_VERSION_NAME = "appVersionName";
    private static final String API_VERSION = "apiVersion";
    private static final String PKG_NAME = "packageName";

    private static Map<String, String> headParams = new HashMap<>();

    public static void init(String market, Context context) {
        if (headParams == null) {
            headParams = new HashMap<>(8);
        }

        headParams.put(PHONE_BRAND, Build.BRAND);
        headParams.put(PHONE_SYSTEM, "android");
        headParams.put(PHONE_MODELS, Build.MODEL);
        headParams.put(APP_MARKET, market);
        headParams.put(APP_VERSIONCODE, "" + BuildConfig.VERSION_CODE);
        headParams.put(APP_VERSION_NAME, BuildConfig.VERSION_NAME);
        headParams.put(API_VERSION, "5001");
        headParams.put(PKG_NAME, context.getPackageName());
    }

    public static Map<String, String> getHeadParams() {
        return headParams;
    }
}
