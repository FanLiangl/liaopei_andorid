package net.lucode.hackware.magicindicator.buildins.commonnavigator;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;

/**
 *
 * @author chengxiong
 * @date 2018/7/13 0013
 */

public class CustomTouchListener implements View.OnTouchListener{

    private boolean click = false;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                click = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                if (x > 0 && x < v.getWidth() && y > 0 && y < v.getHeight()) {
                    click = true;
                }else {
                    click = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (click) {
                    v.performClick();
                    indicatorScaleAnim(v);
                }
                break;
            default:
                break;
        }
        return true;
    }

    public void indicatorScaleAnim(View view){
        if (view == null) {
            return;
        }
        view.clearAnimation();
        ScaleAnimation animation = new ScaleAnimation(1.0f, 0.9f, 1.0f,
                0.9f, Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(150);
        animation.setRepeatCount(1);
        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);
    }
}
