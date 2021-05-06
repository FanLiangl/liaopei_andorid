package com.zm.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * @author Administrator
 * @date 2017/12/15 0015
 * Glide图片加载工具
 */

public class GlideUtils {

    /**
     * 加载Gif Image
     */
    public static void loadGifImage(Context context, int error, ImageView img) {
        RequestOptions options = new RequestOptions()
                .override(img.getWidth(), img.getHeight());

        Glide.with(context).load(error)
                .apply(options)
                .into(img);
    }

    /**
     * 显示图片Imageview
     *
     * @param context 上下文
     * @param error   错误的资源图片
     * @param url     图片链接
     * @param img     组件
     */
    public static void loadShow(Context context, int error, String url,
                                ImageView img) {
        RequestOptions options = new RequestOptions()
                // 设置错误图片
                .error(error)
                // 设置占位图
                .placeholder(error)
                .fitCenter()
                // 缓存修改过的图片
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .override(img.getWidth(), img.getHeight());

        // 加载图片
        Glide.with(context.getApplicationContext()).load(url)
                .apply(options)
                .into(img);
    }


    /**
     * 以centerCrop显示图片
     */
    public static void loadShowCenter(Context context, int error, String url,
                                      ImageView img) {
        RequestOptions options = new RequestOptions()
                // 设置错误图片
                .error(error)
                .centerCrop()
                // 设置占位图
                .placeholder(error)
                // 缓存修改过的图片
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .override(img.getWidth(), img.getHeight());

        // 加载图片
        Glide.with(context.getApplicationContext()).load(url)
                .apply(options)
                .into(img);
    }

    /**
     * 矩形圆角 ImageView
     *
     * @param context 上下文
     * @param error   错误的资源图片
     * @param url     图片链接
     * @param img     组件
     */
    public static void loadShowRounded(Context context, int error, int round,
                                       String url, ImageView img) {

        // 设置矩形圆角

        RoundedCorners roundedCorners = new RoundedCorners(SizeUtils.dp2px(round));
        RequestOptions options = new RequestOptions()
                .transforms(new CenterCrop(), roundedCorners)
                .error(error)
                // 缓存修改过的图片
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .override(img.getWidth(), img.getHeight());

        Glide.with(context.getApplicationContext())
                .load(url)
                .apply(options)
                .into(img);
    }

    /**
     * 显示图片 圆角显示  ImageView
     *
     * @param context 上下文
     * @param error   错误的资源图片
     * @param url     图片链接
     * @param img     组件
     */
    public static void loadShowCircle(Context context, int error,
                                      String url, ImageView img) {
        RequestOptions options = new RequestOptions()
                // 设置错误图片
                .error(error)
                // 设置占位图
                .placeholder(error)
                .fitCenter()
                //圆角
                .transform(new GlideCircleTransform(context.getApplicationContext()))
                // 缓存修改过的图片
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .override(img.getWidth(), img.getHeight());

        // 加载图片
        Glide.with(context.getApplicationContext()).load(url)
                .apply(options)
                .into(img);
    }

    /**
     * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
     */
    public static void loadIntoUseFitWidth(Context context, final String imgUrl, int error, final ImageView img) {
        RequestOptions options = new RequestOptions()
                // 设置错误图片
                .error(error)
                // 设置占位图
                .placeholder(error)
                .centerCrop()
                //圆角
//                .transform(new GlideCircleTransform(context))
                // 缓存修改过的图片
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .override(img.getWidth(), img.getHeight());

        Glide.with(context.getApplicationContext())
                .load(imgUrl)
                .apply(options)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (img == null) {
                            return false;
                        }
                        if (img.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            img.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = img.getLayoutParams();
                        int vw = img.getWidth() - img.getPaddingLeft() - img.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + img.getPaddingTop() + img.getPaddingBottom();
                        img.setLayoutParams(params);
                        return false;
                    }
                }).into(img);
    }

    /**
     * 显示图片Imageview
     *
     * @param context 上下文
     * @param error   错误的资源图片
     * @param url     图片链接
     * @param img     组件
     */
    public static void loadShowFile(Context context, int error, File url,
                                    ImageView img) {
        RequestOptions options = new RequestOptions()
                // 设置错误图片
                .error(error)
                // 设置占位图
                .placeholder(error)
                .fitCenter()
                // 缓存修改过的图片
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .override(img.getWidth(), img.getHeight());

        // 加载图片
        Glide.with(context.getApplicationContext()).load(url)
                .apply(options)
                .into(img);
    }


    public static void loadBlurImg(Context context, int error, String url,
                                   ImageView img) {
        RequestOptions options = new RequestOptions()
                .error(error)
                .placeholder(error)
                .centerCrop()
                .transform(new BlurTransformation(25, 6));
        Glide.with(context).load(url).apply(options).into(img);
    }

    public static void loadBitmap(Context context, Bitmap bitmap, int error, ImageView imageView) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        RequestOptions options = new RequestOptions()
                .error(error)
                .placeholder(error)
                .centerCrop();
        Glide.with(context).load(bytes).
                apply(options).into(imageView);

    }
}
