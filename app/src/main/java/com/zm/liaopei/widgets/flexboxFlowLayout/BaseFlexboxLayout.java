package com.zm.liaopei.widgets.flexboxFlowLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.flexbox.FlexboxLayout;
import com.zm.liaopei.widgets.flexboxFlowLayout.adapter.FlexboxBaseAdapter;

public class BaseFlexboxLayout extends FlexboxLayout {

    protected FlexItemClickListener clickListener;

    public BaseFlexboxLayout(Context context) {
        super(context);
    }

    public BaseFlexboxLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseFlexboxLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onItemClick(int position, View view) {
        LogUtils.i("BaseFlexboxLayout onItemClick: " + position);
    }

    /**
     * 设置adapter
     * @param adapter
     */
    public void setAdapter(FlexboxBaseAdapter adapter) {
        initLayoutData(adapter);
    }

    /**
     * 数据发生变化
     */
    protected void initLayoutData(FlexboxBaseAdapter adapter) {
        removeAllViews();
        for (int i = 0; i < adapter.getCount(); i++) {
            View view = adapter.getView(i, this);
            adapter.bindView(i, view);
            addView(view);

            int finalI = i;
            view.setOnClickListener(v -> {
                onItemClick(finalI, v);
            });
        }
    }

    public void setClickListener(FlexItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface FlexItemClickListener {
        void onClick(int position, View view);
    }

}
