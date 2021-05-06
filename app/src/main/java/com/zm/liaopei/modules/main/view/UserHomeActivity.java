package com.zm.liaopei.modules.main.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.appbar.AppBarLayout;
import com.zm.liaopei.R;
import com.zm.liaopei.base.BaseMvpActivity;
import com.zm.liaopei.constants.RouterPathManager;
import com.zm.liaopei.databinding.UserHomeActivityBinding;
import com.zm.liaopei.modules.main.UserHomeAdapter;
import com.zm.liaopei.modules.main.contract.UserHomeContract;
import com.zm.liaopei.modules.main.presenter.UserHomePresenter;
import com.zm.liaopei.widgets.ScaleTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fanliangliang
 * @description:主播详情页面
 * @date : 2021/4/29 9:19
 */
@Route(path = RouterPathManager.Main.USER_HOME_ACTIVITY_PATH)
public class UserHomeActivity extends BaseMvpActivity<UserHomeActivityBinding, UserHomePresenter> implements UserHomeContract {
    private ArrayList<Fragment> fragmentList=new ArrayList<>();
    private List<String> titleList= Arrays.asList("主页","动态");
    /**
     * 右边上角的举报的喜欢按钮
     */
    private ImageView ivMenu;
    /**
     * 头部名称
     */
    private TextView titleName;
    /**
     * 返回按钮
     */
    private ImageView imgBack;
    /**
     * 判断折叠的时候 某一些变化只执行一次
     */
    private int appbarTop;
    /**
     * 判断缩放的时候 某一些变化只执行一次
     */
    private int appbarBottom;
    @Override
    protected UserHomePresenter initPresenter() {
        return new UserHomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_home_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getLayoutInflater().inflate(R.layout.new_vh_toolbar, binding.toolbar);
        imgBack = findViewById(R.id.iv_back);
        ivMenu = findViewById(R.id.img_tv_block);
        titleName = findViewById(R.id.tv_title);
        fragmentList.add((Fragment) ARouter.getInstance().build(RouterPathManager.Main.USER_HOME_FRAGMENT).navigation());
        fragmentList.add((Fragment) ARouter.getInstance().build(RouterPathManager.Main.DYNAMIC_HOME_FRAGMENT).navigation());
        binding.viewpager.setAdapter(new UserHomeAdapter(getSupportFragmentManager(),fragmentList,titleList));
        initMagicIndicator(titleList);
        appBarChange();


    }

    private void initMagicIndicator(List<String> titleList) {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(false);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titleList == null ? 0 : titleList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ScaleTransitionPagerTitleView titleView = new ScaleTransitionPagerTitleView(context);
                titleView.setSelectedColor(ContextCompat.getColor(context, R.color.color_33333));
                titleView.setNormalColor(ContextCompat.getColor(context, R.color.color666666));
                titleView.setSelectedTextSize(TypedValue.COMPLEX_UNIT_DIP, 23.2f);
                titleView.setNormalTextSize(TypedValue.COMPLEX_UNIT_DIP, 14.2f);
                titleView.setBoldText(true);
                titleView.setText(titleList.get(index));

                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.viewpager.setCurrentItem(index);
                    }
                });
                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors();
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineWidth(UIUtil.dip2px(context, 13));
                indicator.setLineHeight(UIUtil.dip2px(context, 4));
                indicator.setRoundRadius(UIUtil.dip2px(context, 2));
                indicator.setYOffset(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setGradient(true);
                indicator.setColors(ContextCompat.getColor(context, R.color.color_FF3698), ContextCompat.getColor(context, R.color.color_FF3698));
                return indicator;
            }
        });
        binding.tabLayout.setNavigator(commonNavigator);
        binding.tabLayout.setNavigator(commonNavigator);
        LinearLayout container = commonNavigator.getTitleContainer();
        container.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        container.setDividerDrawable(new ColorDrawable() {
            @Override
            public int getIntrinsicWidth() {
                return  UIUtil.dip2px(getApplicationContext(), 16);
            }
        });
        ViewPagerHelper.bind(binding.tabLayout, binding.viewpager);
    }
    /**
     * appBar的状态改变和颜色变化的监听
     */
    private void appBarChange() {
//        setStatusBarFontColor(ScreenNotchUtils.hasNotch(NewVHomepageActivity.this));
        binding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) > appBarLayout.getTotalScrollRange() / 2) {
                    appbarTop = appbarTop + 1;
                    appbarBottom = 0;
                    if (appbarTop == 1) {
//                        imgBack.setImageResource(R.mipmap.back_o);
//                        ivMenu.setImageResource(R.mipmap.video_dian_hui);
                        setStatusBarFontColor(true);
                    }
                    titleName.setTextColor(presenter.changeAlpha(getResources().getColor(R.color.color_black), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
                    binding.toolbar.setBackgroundColor(presenter.changeAlpha(getResources().getColor(R.color.colorWhite), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
                } else {
                    appbarTop = 0;
                    appbarBottom = appbarBottom + 1;
                    if (appbarBottom == 1) {
//                        if (isMagic) {
//                            //加载成功后开启按钮变色
//                            back.setImageResource(R.mipmap.back_w);
//                            ivMenu.setImageResource(R.mipmap.video_dian);
//                        } else {
//                            back.setImageResource(R.mipmap.back_o);
//                            ivMenu.setImageResource(R.mipmap.video_dian_hui);
//                        }

                        binding.toolbar.setBackgroundResource(R.mipmap.big_v_home_mask_s);
//                        if (!ScreenNotchUtils.hasNotch(NewVHomepageActivity.this)) {
//                            setStatusBarFontColor(false);
//                        }

                    }
                    titleName.setTextColor(presenter.changeAlpha(getResources().getColor(R.color.colorWhite), Math.abs(verticalOffset * 1.0f) / appBarLayout.getTotalScrollRange()));
                }
            }
        });
    }

}
