package com.zm.liaopei.constants;
/**
 * @author fanliangliang
 * @description:路由地址管理器
 * @date : 2021/4/26 16:18
 */
public class RouterPathManager {
    /**
     * 主页路由管理
     */
    public static class Main{
        /**
         * 主页路由
         */
        public static final String MAINACTIVITY="/main/MainActivity";
        /**
         * 首页的fragment
         */
        public static final String MAIN_FRAGMENT_PATH = "/main/MainFaragment";
        /**
         * 首页头部标签列表路由
         */
        public static final String MAIN_LISTFRAGMENT_PATH="/main/MainListFragment";
        /**
         * 用户详情页面
         */
        public static final String USER_HOME_ACTIVITY_PATH="/main/UserHomeActivity";
        /**
         * 个人主页动态路由
         */
        public static final String DYNAMIC_HOME_FRAGMENT="/main/DynamicHomeFragment";
        /**
         * 个人主页fragment
         */
        public static final String USER_HOME_FRAGMENT="/main/UserHomeFragment";

    }

    /**
     * 电台路由管理
     */
    public static class Radios{
        /**
         * 电台fragment路由
         */
        public static final String RADIOS_FRAGMENT="/radios/RadiosFragment";
    }

    /**
     * 消息路由管理
     */
    public static class Message{
        /**
         * 消息fragment管理
         */
        public static final String MESSAGE_FRAGMENT="/message/MessageFragment";

    }

    /**
     * 我的页面路由管理
     */
    public static class Mine{
        /**
         * 我的页面fragment管理
         */
        public static final String MINE_FRAGMENT="/mine/MineFragment";

    }

    /**
     * 登录启动路由
     */
    public static class LoginStart{
        /**
         * 启动页面路由
         */
        public static final String START_UP_ACTIVITY="/login/StartUpActivity";
        /**
         * 登录页面的路由
         */
        public static final String LOGIN__ACTIVITY="/login/LoginActivity";
    }


}
