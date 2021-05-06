package com.zm.liaopei.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;

import net.lucode.hackware.magicindicator.buildins.ArgbEvaluatorHolder;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/**
 * 作者：fanliangliang
 * 邮箱：fanjialiang1314@qq.com
 * 创建时间:2019/1/5 0005 下午 1:21
 * 项目名称:VChat-Android-5.0
 * 项目包名:net.lucode.hackware.magicindicator
 * 带颜色渐变和缩放的指示器标题ColorTransitionPagerTitleView
 */
public class ScaleTransitionPagerTitleView extends ColorTransitionPagerTitleView {

    private int mWidth;

    private int mHeight;

    private final int offSet;

    private float mMinScale;

    public ScaleTransitionPagerTitleView(Context context) {
        this(context,1);
    }

    public ScaleTransitionPagerTitleView(Context context, int offSet) {
        super(context);
        setGravity(Gravity.BOTTOM);
        this.offSet = UIUtil.dip2px(context,offSet);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        int color = ArgbEvaluatorHolder.eval(enterPercent, mNormalColor, mSelectedColor);
        setTextColor(color);
        if (mWidth == 0) {
            mWidth = getWidth();
        }
        if (mHeight == 0) {
            mHeight = getHeight();
        }
        setWidth((int) ((mWidth) * (1 + mMinScale * enterPercent)) + offSet);
        setPivotX(0);
        setPivotY(mHeight - UIUtil.dip2px(getContext(), 10));
        setScaleY(1 + mMinScale * enterPercent);
        setScaleX(1 + mMinScale * enterPercent);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        int color = ArgbEvaluatorHolder.eval(leavePercent, mSelectedColor, mNormalColor);
        setTextColor(color);
        if (mWidth == 0) {
            mWidth = getWidth();
        }
        if (mHeight == 0) {
            mHeight = getHeight();
        }
        setWidth((int) ((mWidth) * ((1 + mMinScale) - mMinScale * leavePercent)) + offSet);
        setPivotX(0);
        setPivotY(mHeight - UIUtil.dip2px(getContext(), 10));
        setScaleY((1 + mMinScale) - mMinScale * leavePercent);
        setScaleX((1 + mMinScale) - mMinScale * leavePercent);
    }

//    @Override
//    public void setSelectedTextSize(int mSelectedUnit, float mSelectedTextSize) {
//        super.setSelectedTextSize(mSelectedUnit, mSelectedTextSize);
//        if (mNormalTextSize * mSelectedTextSize != 0) {
//            mMinScale = (mSelectedTextSize - mNormalTextSize) / mNormalTextSize;
//        }
//    }
//
//    @Override
//    public void setNormalTextSize(int mNormalUnit, float mNormalTextSize) {
//        super.setNormalTextSize(mNormalUnit, mNormalTextSize);
//        if (mNormalTextSize * mSelectedTextSize != 0) {
//            mMinScale = (mSelectedTextSize - mNormalTextSize) / mNormalTextSize;
//        }
//    }
//
//    @Override
//    public void onSelected(int index, int totalCount) {
//        if (isTextBold) {
//            getPaint().setTypeface(Typeface.DEFAULT_BOLD);
//        }
//    }

    @Override
    public void onDeselected(int index, int totalCount) {
        getPaint().setTypeface(Typeface.DEFAULT);
    }
}
