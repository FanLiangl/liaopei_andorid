package net.lucode.hackware.magicindicator.buildins.commonnavigator.titles;

import android.content.Context;
import android.util.Log;

import net.lucode.hackware.magicindicator.buildins.ArgbEvaluatorHolder;

/**
 * 作者：fanliangliang
 * 邮箱：fanjialiang1314@qq.com
 * 创建时间:2019/1/5 0005 下午 1:21
 * 项目名称:VChat-Android-5.0
 * 项目包名:net.lucode.hackware.magicindicator.buildins.commonnavigator.titles
 * 带颜色渐变和缩放的指示器标题
 */
public class NewScaleTransitionPagerTitleView extends ColorTransitionPagerTitleView {

    private String TAG = "ScaleTransitionPagerTitleView";

    private int mWidth;

    public NewScaleTransitionPagerTitleView(Context context) {
        super(context);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        int color = ArgbEvaluatorHolder.eval(enterPercent, mNormalColor, mSelectedColor);
        setTextColor(color);
        if (mWidth == 0) {
            mWidth = getWidth();
        }
        float mMinScale = (mSelectedTextSize - mNormalTextSize) / mNormalTextSize;
        setWidth((int) ((mWidth) * (1 + mMinScale * enterPercent)) + 2);
        setScaleX(1 + mMinScale * enterPercent);
        setScaleY(1 + mMinScale * enterPercent);
        Log.e(TAG, "onEnter: " + getWidth());
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        int color = ArgbEvaluatorHolder.eval(leavePercent, mSelectedColor, mNormalColor);
        setTextColor(color);
        if (mWidth == 0) {
            mWidth = getWidth();
        }
        float mMinScale = (mSelectedTextSize - mNormalTextSize) / mNormalTextSize;
        setWidth((int) ((mWidth) * ((1 + mMinScale) - mMinScale * leavePercent)) + 2);
        setScaleX((1 + mMinScale) - mMinScale * leavePercent);
        setScaleY((1 + mMinScale) - mMinScale * leavePercent);
        Log.e(TAG, "onLeave: " + getWidth());
    }

    @Override
    public void setSelectedTextSize(int mSelectedUnit, float mSelectedTextSize) {
        super.setSelectedTextSize(mSelectedUnit, mSelectedTextSize);
    }

    @Override
    public void setNormalTextSize(int mNormalUnit, float mNormalTextSize) {
        super.setNormalTextSize(mNormalUnit, mNormalTextSize);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        getPaint().setFakeBoldText(isTextBold);
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        getPaint().setFakeBoldText(false);
    }
}
