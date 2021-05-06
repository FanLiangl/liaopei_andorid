package com.zm.liaopei.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.zm.liaopei.R;
import com.zm.liaopei.widgets.flexboxFlowLayout.TagFlexboxLayout;
import java.util.List;

/**
 * 1. 左侧title文字
 * 2. 右侧edittext是否可编辑，点击事件
 * 3. 输入字符最大长度
 * 4. 右侧edittext输入的内容类型
 */
public class RowView extends LinearLayout {
    private final int TEXT = 0x00;
    private final int EDITABLE = 0x01;
    private final int LABELS = 0x02;
    private final int ARROW = 0x10;

    private final int LEFT = 0x03;
    private final int RIGHT = 0x05;
    private final int CENTER = 0x11;
    private int leftIcon;
    private String leftText;
    private String inputHint;
    private int contentType;
    private int contentGravity;
    private int maxLenght;
    private int inputType;

    private View myView;

    private TextView tvLeft, tvRight;
    private ImageView ivLeftIcon, ivRightArrow;
    private AutoRightEditText etRight;
    private TagFlexboxLayout tagLayout;
    private TagAdapter tagAdapter;

    public RowView(Context context) {
        super(context);
        init(context, null);
    }

    public RowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public RowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }
    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) return;

        // 获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RowView);
        contentType = typedArray.getInt(R.styleable.RowView_content_type, TEXT);
        contentGravity = typedArray.getInt(R.styleable.RowView_content_gravity, RIGHT);
        leftIcon = typedArray.getResourceId(R.styleable.RowView_left_icon, 0);
        leftText = typedArray.getString(R.styleable.RowView_left_text);
        inputHint = typedArray.getString(R.styleable.RowView_input_hint);
        maxLenght = typedArray.getInt(R.styleable.RowView_max_length, 80);
        inputType = typedArray.getInt(R.styleable.RowView_input_type, InputType.TYPE_CLASS_TEXT);

        typedArray.recycle();

        // 绑定layout布局
        myView = LayoutInflater.from(context).inflate(R.layout.row_view, this, false);
        ivLeftIcon = myView.findViewById(R.id.ivLeftIcon);
        tvLeft = myView.findViewById(R.id.tvLeft);
        tvRight = myView.findViewById(R.id.tvRight);
        etRight = myView.findViewById(R.id.etRight);
        tagLayout = myView.findViewById(R.id.tagLayout);
        ivRightArrow = myView.findViewById(R.id.ivRightArrow);

        tvRight.setGravity(contentGravity);
        etRight.setGravity(contentGravity);

        if (leftIcon != 0) {
            ivLeftIcon.setVisibility(VISIBLE);
            ivLeftIcon.setImageResource(leftIcon);
        } else {
            ivLeftIcon.setVisibility(GONE);
        }

        // 布局关联属性
        tvLeft.setText(leftText);
        int value = contentType & 0x0f;
        if (value == EDITABLE) {
            tvRight.setVisibility(GONE);
            tagLayout.setVisibility(GONE);

            etRight.setVisibility(VISIBLE);
            etRight.setHint(inputHint);
            etRight.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLenght)});
            etRight.setInputType(inputType);
        } else if (value == TEXT){
            tvRight.setVisibility(VISIBLE);
            tvRight.setText(inputHint);

            etRight.setVisibility(GONE);
            tagLayout.setVisibility(GONE);
        } else if (value == LABELS) {
            tvRight.setVisibility(GONE);
            etRight.setVisibility(GONE);

            tagLayout.setVisibility(VISIBLE);
            tagLayout.setItemClickable(false);
            tagAdapter = new TagAdapter.Builder(context)
                    .setTagTextColor(R.color.colorWhite)
                    .build();
            tagLayout.setAdapter(tagAdapter);
        }

        if ((contentType & 0xf0) == ARROW) {
            ivRightArrow.setVisibility(VISIBLE);
        } else {
            ivRightArrow.setVisibility(GONE);
        }

        addView(myView);
    }

    /**
     * 获取输入的内容
     * @return
     */
    public String getInputString() {
        if ((contentType & 0x0f) == EDITABLE)
            return etRight.getText().toString().trim();

        return "";
    }

    /**
     * 设置右侧输入框的默认值
     * @param value
     */
    public void setInputHint(String value) {
        if ((contentType & 0x0f) == EDITABLE) {
            etRight.setText(value);
        }
    }

    /**
     * 设置右侧textview显示的文字
     * @param text 要显示的内容
     * @return
     */
    public void setResultString(String text) {
        if ((contentType & 0x0f) == TEXT)
            tvRight.setText(text);
    }

    public String getRightTextString() {
        if (((contentType & 0x0f) == TEXT) && !TextUtils.isEmpty(tvRight.getText()))
        return tvRight.getText().toString().trim();

        return "";
    }

    /**
     * 设置要显示的labels
     * @param labels
     */
    public void setLabels(List<String> labels) {
        if ((contentType & 0x0f) == LABELS) {
            tagAdapter.setDataList(labels);
        }
    }

    /**
     * 设置每个label的背景色和字体颜色
     * 请在#setLabels之前调用
     * @param labelBackgroundColorRes
     * @param labelTextColorRes
     */
    public void setLabelResource(int labelBackgroundColorRes, int labelTextColorRes) {
        tagAdapter.setTagBackgroundRes(labelBackgroundColorRes);
        tagAdapter.setTagTextColorRes(labelTextColorRes);
    }

}
