package com.zm.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

/**
 * 动画工具类
 */
public class AnimationUtils {

    private static boolean isStop = false;

    public interface AnimationListener {
        void onFinish(int position);
    }

    /**
     * 水平翻转后渐变消失
     */
    public static void FlipAnimatorYViewShow(final View oldView, final View newView, final long time) {

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(oldView, "rotationY", 0, 90);
        final ObjectAnimator animator2 = ObjectAnimator.ofFloat(newView, "rotationY", -90, 0);
        final AlphaAnimation animator3 = new AlphaAnimation(1.0f, 0.0f);
        animator3.setDuration(500);
        animator3.setFillAfter(true);
        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                oldView.setVisibility(View.INVISIBLE);
                oldView.setRotationY(0);
                animator2.setDuration(time).start();
                newView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                newView.startAnimation(animator3);
                newView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator1.setDuration(time).start();
    }

    /**
     * 先渐入再渐出
     */
    public static void showAndDisappearAnim(final View view, final long time) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(time);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(alphaAnimation);
    }

    /**
     * 渐显后渐隐
     */
    public static void showFadeShowOutAnim(final View view, final long time, final long outTime) {
        view.clearAnimation();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(time);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();

                UIThread.postInHandlerThread(new Runnable() {
                    @Override
                    public void run() {
                        showFadeOutAnim(view, 500);
                    }
                }, outTime);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(alphaAnimation);
    }

    /**
     * 渐现
     */
    public static void showFadeShowAnim(final View view, final long time) {
        view.clearAnimation();
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(time);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(alphaAnimation);
    }

    /**
     * 渐隐
     */
    public static void showFadeOutAnim(final View view, final long time) {
        view.clearAnimation();
        if (view.getVisibility() != View.VISIBLE) {
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(time);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(alphaAnimation);
    }

    /**
     * 小视频侧边按钮动画
     *
     * @param targetId 动画结束时需要变更的背景图片id
     * @param view     目标view
     * @param isChange 是否需要变更背景
     */
    public static void heartBeatAnim(final int targetId, final View view, final boolean isChange, long time) {
        view.clearAnimation();
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.30f, 0.9f, 1.1f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.30f, 0.9f, 1.1f, 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY).setDuration(time);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.clearAnimation();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.clearAnimation();
                if (isChange) {
                    view.setBackgroundResource(targetId);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    /**
     * 播放视频页双击屏幕动画
     *
     * @param view
     */
    public static void heartBeatAnimPro(final View view) {

        if (null == view) {
            return;
        }
        view.clearAnimation();
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 0.2f, 1.5f, 0.8f, 1.2f, 0.9f, 1.0f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 0.2f, 1.5f, 0.8f, 1.2f, 0.9f, 1.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY).setDuration(500);
        final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
        alphaAnimator.setDuration(300);
        alphaAnimator.setStartDelay(600);
        alphaAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                view.setAlpha(1.0f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                alphaAnimator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    /**
     * 礼物列表点击抖动动画
     *
     * @param view
     */
    public static void pushGiftDoubleHitAnim(final View view) {
        if (null == view) {
            return;
        }
        view.clearAnimation();
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 0.2f, 1.5f, 0.8f, 1.2f, 0.9f, 1.0f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 0.2f, 1.5f, 0.8f, 1.2f, 0.9f, 1.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY).setDuration(500);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.clearAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    /**
     * 方法名：loadingAnim
     * 功能：小视频加载动画
     * 参数：view 动画视图
     * 返回值：无
     */
    public static void loadingAnim(final View view) {
        if (null == view) {
            return;
        }
        view.clearAnimation();
        ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setRepeatCount(-1);
        animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        view.setAnimation(animation);
        animation.start();
    }

    /**
     * 向上弹出
     *
     * @param view
     */
    public static void slideToUp(View view, long time) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        slide.setDuration(time);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        view.startAnimation(slide);

        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 向上并且渐现动画
     *
     * @param view
     * @param time 动画时长
     */
    public static void slideToUpAndFade(final View view, long time) {
        AnimationSet animationSet = new AnimationSet(true);

        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        slide.setDuration(time);
        slide.setFillAfter(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(time);
        alphaAnimation.setFillAfter(true);

        animationSet.addAnimation(slide);
        animationSet.addAnimation(alphaAnimation);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                UIThread.postInHandlerThread(new Runnable() {
                    @Override
                    public void run() {
                        showFadeOutAnim(view, 500);
                    }
                }, 5000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(animationSet);
    }

    /**
     * 诱导通话礼物按钮动画
     *
     * @param view
     */
    public static void startRotateAnim(final View view) {
        final AnimationSet animationSet = new AnimationSet(true);

        Animation animation1 = new RotateAnimation(0, 10,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation1.setDuration(200);

        Animation animation2 = new RotateAnimation(0, -20,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation2.setDuration(166);
        animation2.setStartOffset(200);

        Animation animation3 = new RotateAnimation(0, 10,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation3.setDuration(166);
        animation3.setStartOffset(366);

        Animation animation4 = new RotateAnimation(0, 5,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation4.setDuration(133);
        animation4.setStartOffset(532);

        Animation animation5 = new RotateAnimation(0, -5,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation5.setDuration(100);
        animation5.setStartOffset(665);


        animationSet.addAnimation(animation1);
        animationSet.addAnimation(animation2);
        animationSet.addAnimation(animation3);
        animationSet.addAnimation(animation4);
        animationSet.addAnimation(animation5);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                UIThread.postInHandlerThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!isStop) {
                            startRotateAnim(view);
                        } else {
                            view.clearAnimation();
                            animationSet.cancel();
                            animationSet.reset();
                        }
                    }
                }, 2000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(animationSet);
    }

    /**
     * 停止循环摇动动画
     *
     * @param view
     */
    public static void stopRotateAnim(final View view) {
        isStop = true;
        view.clearAnimation();
        view.setAnimation(null);
    }

    /**
     * 放大渐隐
     *
     * @param view
     */
    public static void enlargeHideAnim(final View view) {
        final AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 2f, 1f, 2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(400);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
                animationSet.cancel();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animationSet);
    }


    /**
     * 首页底部导航栏动画
     *
     * @param view
     */
    public static void indicatorScaleAnim(View view) {
        if (view == null) {
            return;
        }
        view.clearAnimation();
        ScaleAnimation animation = new ScaleAnimation(1.0f, 0.9f, 1.0f,
                0.9f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(150);
        animation.setRepeatCount(1);
        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);
    }

    /**
     * 从控件所在位置移动到控件的底部
     *
     * @return
     */
    public static TranslateAnimation moveToViewBottom() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHiddenAction.setDuration(250);
        return mHiddenAction;
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    public static TranslateAnimation moveToViewLocation() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mHiddenAction.setDuration(250);
        return mHiddenAction;
    }

    public static void showAlpgaAnim(View view, long time) {
        view.clearAnimation();
        view.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0, 1, 0);
        animator.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        animator.setRepeatMode(ValueAnimator.RESTART);//
        animator.setDuration(time);
        animator.start();
    }

    /**
     *  闪烁动画
     * @param view
     */
    public static void setViewbling(View view) {
        view.clearAnimation();
        AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.1f, 1.0f);
        // setDuration 设置闪烁一次的时间
        alphaAnimation1.setDuration(500);
        //设置闪烁次数 可以是具体数值，也可以是Animation.INFINITE（重复多次）
        alphaAnimation1.setRepeatCount(Animation.INFINITE);
        // setRepeatMode 动画结束后从头开始或从末尾开始,  Animation.REVERSE（从末尾开始） Animation.RESTART（从头开始）
        alphaAnimation1.setRepeatMode(Animation.REVERSE);
        // setAnimation将设置的动画添加到view上
        view.setAnimation(alphaAnimation1);
        alphaAnimation1.start();

    }

    /**
     * 向上翻转并且变换图片
     *
     * @param textView
     */
    public static void FlipXAnimation(final TextView textView, final int resId, long time) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(textView, "rotationX", 0, 90);
        animator1.setDuration(time / 2);
        final ObjectAnimator animator2 = ObjectAnimator.ofFloat(textView, "rotationX", -90, 0);
        animator2.setDuration(time / 2);
        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animator2.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                textView.setBackgroundResource(resId);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator1.start();
    }
}
