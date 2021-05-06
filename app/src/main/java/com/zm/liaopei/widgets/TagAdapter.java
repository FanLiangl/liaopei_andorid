package com.zm.liaopei.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.zm.liaopei.R;
import com.zm.liaopei.widgets.flexboxFlowLayout.adapter.FlexboxBaseAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TagAdapter extends FlexboxBaseAdapter {
    private LayoutInflater inflater;
    private Context mContext;
    private int tagBackgroundRes;
    private int tagTextColorRes;

    // list类型的数据
    private List<String> dataList;
    // map类型的数据
    private Map<Integer, String> dataMap;

    // index类型结果
    private List<Integer> resultIndexList = new ArrayList<>();

//    public TagAdapter(Context context) {
//        this(context, -1, -1);
//    }

    private TagAdapter(Context context, int tagBackgroundRes, int textColorRes) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        this.tagBackgroundRes = tagBackgroundRes;
        this.tagTextColorRes = textColorRes;
    }

    /**
     * 设置list类型数据
     * @param dataList
     */
    public void setDataList(List<String> dataList) {
        this.dataList = dataList;

        if (listener != null) {
            listener.onDataChanged();
        }
    }

    /**
     * 设置map类型数据
     * @param dataMap
     */
    public void setDataMap(Map<Integer, String> dataMap) {
        this.dataMap = dataMap;

        if (listener != null) {
            listener.onDataChanged();
        }
    }

    /**
     * 设置tag的背景色
     * @param tagBackgroundRes
     */
    public void setTagBackgroundRes(int tagBackgroundRes) {
        this.tagBackgroundRes = tagBackgroundRes;
    }

    /**
     * 设置tag里text的颜色
     * @param tagTextColorRes
     */
    public void setTagTextColorRes(int tagTextColorRes) {
        this.tagTextColorRes = tagTextColorRes;
    }

    /**
     * 获取index结果列表
     * @return
     */
    public List<Integer> getResultIndexList() {
        return resultIndexList;
    }

    /**
     * 设置已选择的数据
     * @param dataList
     */
    public void setSelectedItem(List<Integer> dataList) {
        resultIndexList.addAll(dataList);
    }

    @Override
    public int getCount() {
        if (dataList != null)
            return dataList.size();
        else if (dataMap != null)
            return dataMap.size();

        return 0;
    }

    @Override
    public View getView(int position, ViewGroup parent) {
        View view = inflater.inflate(
                R.layout.layout_tag_view, parent, false);

        if (tagBackgroundRes != -1) {
            view.setBackgroundResource(tagBackgroundRes);
        }

        if (tagTextColorRes != -1) {
            TextView textView = view.findViewById(R.id.tagView);
            textView.setTextColor(mContext.getResources().getColor(tagTextColorRes));
        }

        return view;
    }

    @Override
    public void bindView(int position, View view) {
        TextView tv = view.findViewById(R.id.tagView);

        if ((dataList != null) && (dataList.size() != 0)) {
            tv.setText(dataList.get(position));
            tv.setTag(R.id.tag_view_id, position);

            if (resultIndexList.size() != 0) {
                // todo
            }
        } else if ((dataMap != null) && (dataMap.size() != 0)) {
            List<Integer> allIndex = new ArrayList<>(dataMap.keySet());
            tv.setText(dataMap.get(allIndex.get(position)));
            tv.setTag(R.id.tag_view_id, allIndex.get(position));

            if (resultIndexList.size() != 0) {
                if (resultIndexList.contains(allIndex.get(position))) {
                    view.setSelected(true);
                    tv.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
                }
            }
        }
    }

    @Override
    public void onItemClicked(int position, View view) {
        boolean isSelected = view.isSelected();

        TextView tv = view.findViewById(R.id.tagView);
        if (isSelected) {
            // 被选中了
            tv.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
        } else {
            // 返选
            tv.setTextColor(mContext.getResources().getColor(R.color.colorBlack50));
        }

        Integer indexTag = (Integer) tv.getTag(R.id.tag_view_id);
        if (resultIndexList.contains(indexTag)) {
            resultIndexList.remove(indexTag);
        } else {
            resultIndexList.add(indexTag);
        }

        LogUtils.i("all results: " + resultIndexList);
    }

    /**
     * 获取选中的结果
     * @return
     */
    public String getSelectTagsString() {
        StringBuilder builder = new StringBuilder();
        if (dataList != null) {
            for (int i : resultIndexList) {
                builder.append(dataList.get(i));
                builder.append(";");
            }
        } else if (dataMap != null) {
            for (int i : resultIndexList) {
                builder.append(dataMap.get(i));
                builder.append(";");
            }
        }

        return builder.toString();
    }

    /**
     * tag构造器
     */
    public static class Builder {
        private Context context;
        private int tagBackground = -1;
        private int tagTextColor = -1;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTagBackground(int tagBackground) {
            this.tagBackground = tagBackground;
            return this;
        }

        public Builder setTagTextColor(int tagTextColor) {
            this.tagTextColor = tagTextColor;
            return this;
        }

        public TagAdapter build() {
            return new TagAdapter(context, tagBackground, tagTextColor);
        }
    }
}
