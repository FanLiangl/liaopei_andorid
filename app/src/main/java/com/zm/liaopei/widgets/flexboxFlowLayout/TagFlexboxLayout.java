package com.zm.liaopei.widgets.flexboxFlowLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.zm.liaopei.widgets.flexboxFlowLayout.adapter.FlexboxBaseAdapter;

public class TagFlexboxLayout extends BaseFlexboxLayout implements FlexboxBaseAdapter.OnDataChangeListener {

    private FlexboxBaseAdapter tagAdapter;

    private boolean itemClickable = true;

    public TagFlexboxLayout(Context context) {
        super(context);
    }

    public TagFlexboxLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagFlexboxLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(FlexboxBaseAdapter adapter) {
        super.setAdapter(adapter);
        tagAdapter = adapter;
        tagAdapter.setOnDataChangeListener(this);
    }

    @Override
    protected void onItemClick(int position, View view) {
        if (itemClickable) {
            boolean isSelected = view.isSelected();
            view.setSelected(!isSelected);

            tagAdapter.onItemClicked(position, view);

            if (clickListener != null) {
                clickListener.onClick(position, view);
            }
        }
    }

    public void setItemClickable(boolean itemClickable) {
        this.itemClickable = itemClickable;
    }

    /**
     * FlexboxBaseAdapter.OnDataChangeListener
     */
    @Override
    public void onDataChanged() {
        initLayoutData(tagAdapter);
    }
}
