package net.lucode.hackware.magicindicator.buildins.commonnavigator.titles;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;


/**
 * 带文本的指示器标题
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
public class SimplePagerTitleView extends TextView implements IMeasurablePagerTitleView {

    protected int mSelectedColor;
    protected int mNormalColor;
    protected float mSelectedTextSize;
    protected float mNormalTextSize;
    protected int mSelectedUnit = TypedValue.COMPLEX_UNIT_DIP;
    protected int mNormalUnit = TypedValue.COMPLEX_UNIT_DIP;

    protected boolean isTextBold = false; //选中标题字体是否加粗


    public SimplePagerTitleView(Context context) {
        super(context, null);
        init(context);
    }

    private void init(Context context) {
        setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
        int padding = UIUtil.dip2px(context, 10);
        setPadding(0, 0, 0, padding);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        setTextColor(mSelectedColor);
        setTextSize(mSelectedUnit, mSelectedTextSize);
        if (isTextBold) {
            getPaint().setTypeface(Typeface.DEFAULT_BOLD);
        }
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        setTextColor(mNormalColor);
        setTextSize(mNormalUnit, mNormalTextSize);
        getPaint().setTypeface(Typeface.DEFAULT);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
    }

    @Override
    public int getContentLeft() {
        Rect bound = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), bound);
        int contentWidth = bound.width();
        return getLeft() + getWidth() / 2 - contentWidth / 2;
    }

    @Override
    public int getContentTop() {
        Paint.FontMetrics metrics = getPaint().getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) (getHeight() / 2 - contentHeight / 2);
    }

    @Override
    public int getContentRight() {
        Rect bound = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), bound);
        int contentWidth = bound.width();
        return getLeft() + getWidth() / 2 + contentWidth / 2;
    }

    @Override
    public int getContentBottom() {
        Paint.FontMetrics metrics = getPaint().getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) (getHeight() / 2 + contentHeight / 2);
    }

    public int getSelectedColor() {
        return mSelectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        mSelectedColor = selectedColor;
    }


    public int getNormalColor() {
        return mNormalColor;
    }

    public void setNormalColor(int normalColor) {
        mNormalColor = normalColor;
    }

    public void setSelectedTextSize(int mSelectedUnit, float mSelectedTextSize) {
        this.mSelectedUnit = mSelectedUnit;
        this.mSelectedTextSize = mSelectedTextSize;
    }

    public void setNormalTextSize(int mNormalUnit, float mNormalTextSize) {
        this.mNormalUnit = mNormalUnit;
        this.mNormalTextSize = mNormalTextSize;
    }

    // 设置选中标题字体是否加粗
    public void setBoldText(boolean isBold) {
        this.isTextBold = isBold;
    }
}
