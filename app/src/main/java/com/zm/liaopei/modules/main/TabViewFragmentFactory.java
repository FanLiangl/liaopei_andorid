package com.zm.liaopei.modules.main;

import android.util.SparseArray;
import androidx.fragment.app.Fragment;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zm.liaopei.constants.Constants;
import com.zm.liaopei.constants.RouterPathManager;

/**
 * @author fanliangliang
 * @description:
 * @date : 2021/4/27 13:40
 */
public class TabViewFragmentFactory {
    private static SparseArray<Fragment> fragmentMap = new SparseArray<>();


    public static Fragment getTabViewFragment(int fragmentId) {
        Fragment fragment = fragmentMap.get(fragmentId);
        if (fragment == null) {
            switch (fragmentId) {
                case Constants.CONST_ZERO:  {
                    fragment =(Fragment) ARouter.getInstance().build(RouterPathManager.Main.MAIN_FRAGMENT_PATH).navigation();
                    break;
                }
                case Constants.CONST_ONE:   {
                    fragment = (Fragment) ARouter.getInstance().build(RouterPathManager.Radios.RADIOS_FRAGMENT).navigation();
                    break;
                }
                case Constants.CONST_TWO: {
                    fragment = (Fragment) ARouter.getInstance().build(RouterPathManager.Message.MESSAGE_FRAGMENT).navigation();
                    break;
                }

                case Constants.CONST_THREE: {
                    fragment = (Fragment) ARouter.getInstance().build(RouterPathManager.Mine.MINE_FRAGMENT).navigation();
                    break;
                }

                default:
                    fragment = (Fragment) ARouter.getInstance().build(RouterPathManager.Main.MAIN_FRAGMENT_PATH).navigation();
                    break;
            }
        }

        return fragment;
    }

    public static void removeFragment(int fragmentId) {
        fragmentMap.remove(fragmentId);
    }

    public static void addFragment(int fragmentId, Fragment fragment) {
        fragmentMap.put(fragmentId, fragment);
    }

    /**
     * 获取第一个fragment, 用户列表
     * @return
     */
    public static Fragment getMainFragment() {
        return fragmentMap.get(Constants.CONST_ZERO);
    }
}
