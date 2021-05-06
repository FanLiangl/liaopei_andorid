package com.zm.liaopei.widgets.flexboxFlowLayout.adapter;

import android.view.View;
import android.view.ViewGroup;

public abstract class FlexboxBaseAdapter {

    protected OnDataChangeListener listener;
    /**
     * 获取元素的总个数
     * @return
     */
    public abstract int getCount();

    /**
     * 创建所需要的View
     * @param position
     * @return
     */
    public abstract View getView(int position, ViewGroup parent);

    /**
     * 为view绑定数据
     * @param position
     * @param view
     */
    public abstract void bindView(int position, View view);

    /**
     * 某个view被点击后
     * @param position
     * @param view
     */
    public void onItemClicked(int position, View view) {}

    /**
     * 设置数据变化监听器
     * @param listener
     */
    public void setOnDataChangeListener(OnDataChangeListener listener) {
        this.listener = listener;
    }

    /**
     * 数据变化监听器
     */
    public interface OnDataChangeListener {
        /**
         * 数据变化了回调
         */
        void onDataChanged();
    }
}
