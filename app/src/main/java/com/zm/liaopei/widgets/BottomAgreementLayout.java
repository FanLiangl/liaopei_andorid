package com.zm.liaopei.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.zm.liaopei.R;
import com.zm.liaopei.listenter.NoDoubleClickListener;

/**
 * @author fanliangliang
 * @description:底部的用户协议
 * @date : 2021/4/28 13:46
 */
public class BottomAgreementLayout extends LinearLayout {
    /**
     * 用户协议
     */
    private TextView tvPrivacyProtocol;
    /**
     * 隐私协议
     */
    private TextView tvConceal;


    public BottomAgreementLayout(Context context) {
        super(context);
        initView();
    }

    public BottomAgreementLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BottomAgreementLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public BottomAgreementLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    /**
     * 初始布局
     */
    private void initView(){
        inflate(getContext(), R.layout.bottom_agreement,this);

        tvPrivacyProtocol=findViewById(R.id.tv_privacy_protocol);
        tvConceal=findViewById(R.id.tv_user_agreement);
        tvPrivacyProtocol.setOnClickListener(listener);
        tvConceal.setOnClickListener(listener);


    }

    private NoDoubleClickListener listener=new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (v.getId()==R.id.tv_privacy_protocol){
                //跳转到用户协议
            }else if (v.getId()==R.id.tv_user_agreement){
                //跳转到隐私协议

            }

        }
    };
}
