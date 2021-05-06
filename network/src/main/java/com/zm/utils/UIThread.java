package com.zm.utils;

import android.os.Handler;

import com.blankj.utilcode.util.LogUtils;

import java.lang.ref.WeakReference;

/**
 * 线程异步消息处理工具
 * @author self
 * @date 2017/8/23
 */

public class UIThread {

    private static Handler handler;

    private static WeakReference<Thread> thread_ref;

    public static void initUIThread() {
        if (handler == null) {
            handler = new Handler();
        }
        if (thread_ref == null) {
            thread_ref = new WeakReference<>(Thread.currentThread());
        }
    }

    public static void runInHandlerThread(Runnable runnable) {
        if (handler != null) {
            if (Thread.currentThread() != thread_ref.get()) {
                handler.post(runnable);
            } else {
                try {
                    runnable.run();
                } catch (Throwable e) {
                    LogUtils.w("Error occurred in handler run thread " + e.toString());
                }
            }
        }
    }

    public static void postInHandlerThread(Runnable runnable, long delay) {
        if (handler != null) {
            handler.postDelayed(runnable, delay);
        }
    }

    public static void removeInHandlerThread(Runnable runnable) {
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    public static void removeCallbacksAndMessages(Object object) {
        if (handler != null) {
            handler.removeCallbacksAndMessages(object);
        }
    }

    public static boolean isMainThread() {
        if (handler != null) {
            return Thread.currentThread() == thread_ref.get();
        }
        return false;
    }

}