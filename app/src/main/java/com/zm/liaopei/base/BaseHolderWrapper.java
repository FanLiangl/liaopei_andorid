package com.zm.liaopei.base;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zm.utils.CircleImageView;
import com.zm.utils.GlideUtils;
/**
 * @author fanliangliang
 * @description:-增加自己的拓展方法
 * @date : 2021/4/29 16:54
 */
public class BaseHolderWrapper extends BaseViewHolder {

    public BaseHolderWrapper(View view){
        super(view);
    }

    public void loadShow(Context context, int viewId, int errId, String url) {
        ImageView imageView = getView(viewId);
        GlideUtils.loadShow(context, errId, url, imageView);
    }

    public void loadShowCenter(Context context, int viewId, int errId, String url) {
        ImageView imageView = getView(viewId);
        if (imageView instanceof CircleImageView) {
            GlideUtils.loadShowCenter(context, errId, url, imageView);
        } else {
            loadCircleImage(context, viewId, errId, url);
        }
    }


    public void loadCircleImage(Context context, int viewId, int errId, String url) {
        ImageView imageView = getView(viewId);
        GlideUtils.loadShowCircle(context, errId, url, imageView);
    }

    public TextView getTextView(int id) {
        return getView(id);
    }

    public ImageView getImageView(int id) {
        return getView(id);
    }

    public BaseViewHolder setBackgroundResource(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public BaseViewHolder setGradientDrawableBackgroundResource(Context context, int viewId, int backgroundRes) {
        View view = getView(viewId);
        GradientDrawable drawable = (GradientDrawable) view.getBackground();
        drawable.setColor(ContextCompat.getColor(context, backgroundRes));
        return this;
    }
}
