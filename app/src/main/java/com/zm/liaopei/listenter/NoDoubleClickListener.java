package com.zm.liaopei.listenter;

import android.view.View;
import android.view.View.OnClickListener;

import java.util.Calendar;

/**
 * @author tongming
 * @ClassName NoDoubleClickListener
 * @Description TODO(这里用一句话描述这个类的作用)
 * @date 2016年8月5日 下午7:22:29
 * @history 1.YYYY-MM-DD author: description:
 */
public abstract class NoDoubleClickListener implements OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 1000;

    private long lastClickTime = 0;
    private int viewId = 0;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        //增加对view 的判断，同一个view的点击间隔不能少于1s.
        if ( viewId != v.getId() || currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            viewId = v.getId();
            onNoDoubleClick(v);
        }
    }

    public abstract void onNoDoubleClick(View v);

}
