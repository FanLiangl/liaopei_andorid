package com.zm.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author fanliangliang
 * @description:APP栈的管理
 * @date : 2021/4/26 10:59
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;
    private AppManager() {
    }

    /**
     * 单例管理Activity
     *
     * @return
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            synchronized (AppManager.class) {
                instance = new AppManager();
            }
        }
        return instance;
    }

    /**
     * 添加Activity到栈堆
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity(堆栈中最后一个压入的)
     *
     * @return
     */
    public static Activity currentActivity() {
        if (activityStack != null && !activityStack.empty()) {
            Activity activity = activityStack.lastElement();
            return activity;
        }
        return null;
    }

    public static Activity previousActivity() {
        Activity activity;
        if (activityStack.size() >= 2) {
            activity = activityStack.get(activityStack.size() - 2);
        } else {
            activity = currentActivity();
        }
        return activity;
    }

    /**
     * 结束当前Activity(堆栈中最后一个压入的)
     */
    public static void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public static void finishActivity(Activity activity) {
        if (null != activity) {
            activity.finish();
            activity = null;
        }
    }

    /**
     * 判断是否启动
     *
     * @param mClass
     * @return
     */
    public boolean isLauncher(Class<?> mClass) {
        if (activityStack == null || mClass == null) {
            return false;
        }
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(mClass)) {
                return true;
            }
        }
        return false;
    }

    public boolean isLauncher(String className) {
        if (TextUtils.isEmpty(className)) {
            return false;
        }
        if (activityStack == null) {
            return false;
        }
        for (Activity activity : activityStack) {
            if (className.equals(activity.getClass().getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法名：isUnique
     * 功能：判断是否只存在一个这个Activity
     * 参数：Class Activity对应的class
     * 返回值：true 只存在一个  false 存在多个
     */
    public boolean isUnique(Class<?> mClass) {
        if (activityStack == null) {
            return true;
        }
        int count = 0;
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(mClass)) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity(Class<?> aClass) {
        if (activityStack == null) {
            return;
        }
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(aClass)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                finishActivity(activityStack.get(i));
            }
        }
    }

    /**
     * 从栈中移除Activity，但不调用Activity的finish方法，用于非手动调用finishActivity导致Activity destroy情况
     */
    public static void removeActivity(Activity activity) {
        if (activityStack == null) {
            return;
        }
        activityStack.remove(activity);
    }

    /**
     * 从栈中移除Activity，但不调用Activity的finish方法，用于非手动调用finishActivity导致Activity destroy情况
     */
    public static void removeActivity(Class<?> aClass) {
        if (activityStack == null) {
            return;
        }
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(aClass)) {
                finishActivity(activity);
                activityStack.remove(activity);
                break;
            }
        }
    }

    public static void removeActivity(String className) {
        if (TextUtils.isEmpty(className)) {
            return;
        }
        if (activityStack == null) {
            return;
        }
        for (Activity activity : activityStack) {
            if (activity.getClass().getName().equals(className)) {
                finishActivity(activity);
                activityStack.remove(activity);
                break;
            }
        }
    }

    /**
     * 获取当前Acivity栈中的长度
     *
     * @return
     */
    public static int activitySize() {
        if (null == activityStack) {
            return 0;
        }
        return activityStack.size();
    }

    /**
     * 判断服务是否开启
     *
     * @return
     */
    public static boolean isServiceRunning(Context context, String serviceName) {
        if (TextUtils.isEmpty(serviceName)) {
            return false;
        }
        ActivityManager myManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager
                .getRunningServices(30);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString()
                    .equals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkApkExist(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName,
                            PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 判断应用是否已经启动
     *
     * @param context     一个context
     * @param packageName 要判断应用的包名
     * @return boolean
     */
    public static boolean isAppAlive(Context context, String packageName) {
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos
                = activityManager.getRunningAppProcesses();
        for (int i = 0; i < processInfos.size(); i++) {
            if (processInfos.get(i).processName.equals(packageName)) {
                Log.i("NotificationLaunch",
                        String.format("the %s is running, isAppAlive return true", packageName));
                return true;
            }
        }
        Log.i("NotificationLaunch",
                String.format("the %s is not running, isAppAlive return false", packageName));
        return false;
    }


    /**
     * 方法描述：判断某一应用是否正在运行
     *
     * @param context     上下文
     * @param packageName 应用的包名
     * @return true 表示正在运行，false 表示没有运行
     */
    public static boolean isAppRunning(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        if (list.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.baseActivity.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断界面是否在前台
     *
     * @param activity
     * @return
     */
    public static boolean isForeground(Activity activity) {
        return isForeground(activity, activity.getClass().getName());
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context   Context
     * @param className 界面的类名
     * @return 是否在前台显示
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 应用程序退出
     */
    public static void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        }
    }

    public static Activity getActivity(Class clz) {
        if (activityStack != null && activityStack.size() > 0) {
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(clz)) {
                    return activity;
                }
            }
        }
        return null;
    }

    /**
     * @return 获取栈中最后一个Activity
     */
    public Activity getLastActivityInStack() {
        if (activityStack != null && !activityStack.empty()) {
            Activity activity = activityStack.lastElement();
            return activity;
        }
        return null;
    }
}
