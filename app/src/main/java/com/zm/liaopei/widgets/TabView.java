package com.zm.liaopei.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.zm.liaopei.R;
/**
 * @author fanliangliang
 * @description:用于首页底部tab
 * @date : 2021/4/27 11:08
 */
public class TabView extends FrameLayout {
    private ImageView ivIcon;
    private ImageView ivIconSelect;
    private TextView tvTitle;
    private TextView tvBadge;

    public TabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    private void initViews(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_tabview_bottom, this, false);
        ivIcon = view.findViewById(R.id.ivTabIcon);
        ivIconSelect = view.findViewById(R.id.ivTabIconSelect);
        tvTitle = view.findViewById(R.id.tvTabTitle);
        tvBadge = view.findViewById(R.id.tvBadge);

        addView(view);
    }

    /**
     * 设置要显示的icon和文字
     * @param iconId
     * @param iconSelectId
     * @param text
     */
    public void setIconResource(int iconId, int iconSelectId, String text) {
        ivIcon.setImageResource(iconId);
        ivIconSelect.setImageResource(iconSelectId);
        tvTitle.setText(text);
    }

    /**
     * 设置tabview是否选中
     * @param selected
     */
    public void setSelected(boolean selected) {
        if (selected) {
            ivIcon.setVisibility(GONE);
            ivIconSelect.setVisibility(VISIBLE);
            tvTitle.setTextColor(getResources().getColor(R.color.colorFF0061));
        } else {
            ivIcon.setVisibility(VISIBLE);
            ivIconSelect.setVisibility(GONE);
            tvTitle.setTextColor(getResources().getColor(R.color.color666666));
        }
    }

    /**
     * 是否展示badge
     * @param show
     */
    public void showBadge(boolean show) {
        if (show) {
            tvBadge.setVisibility(VISIBLE);
        } else {
            tvBadge.setVisibility(GONE);
        }
    }
}
