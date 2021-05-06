package net.lucode.hackware.magicindicator.buildins;

import android.content.Context;

/**
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
public final class UIUtil {

    public static int dip2px(Context context, double dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    /**
     * px转成dp
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context,float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}