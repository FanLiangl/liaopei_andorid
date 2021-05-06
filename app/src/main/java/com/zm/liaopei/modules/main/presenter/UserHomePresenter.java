package com.zm.liaopei.modules.main.presenter;

import android.graphics.Color;

import com.zm.liaopei.base.BasePresenter;
import com.zm.liaopei.modules.main.contract.UserHomeContract;

/**
 * @author fanliangliang
 * @description:
 * @date : 2021/4/29 9:20
 */
public class UserHomePresenter extends BasePresenter<UserHomeContract>{



    /**
     * 颜色变化
     * @param color
     * @param fraction
     * @return
     */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }
}
