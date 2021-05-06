package com.zm.liaopei.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.zm.liaopei.R;

import org.greenrobot.eventbus.EventBus;
import java.util.List;

/**
 * @author fanliangliang
 * @description:fragment基类可以实拓展
 * @date : 2021/4/26 16:31
 */
public abstract class BaseMvpFragment <VDB extends ViewDataBinding, P extends BasePresenter> extends Fragment implements BaseView{

    private static final String BUNDLE_KEY = "bundle";
    protected P presenter;
    protected VDB binding;
    protected Context context;
    protected Bundle bundle;
    protected View rootView;
    private boolean isInit;
    protected List<String> mTitles;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        presenter = initPresenter();
        if (presenter != null) {
            presenter.onAttach((BaseView) this);
        }
        if (openEventBus()) {
            EventBus.getDefault().register(this);
        }

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            bundle = savedInstanceState.getBundle(BUNDLE_KEY);
        } else {
            bundle = getArguments();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (bundle != null) {
            outState.putBundle(BUNDLE_KEY, bundle);
        }
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTitles=initTitleList();
        if (rootView == null) {
            if (isUseDataBinding()) {
                binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
                rootView = binding.getRoot();
            } else {
                rootView = inflater.inflate(getLayoutId(), container, false);
            }
            initView(savedInstanceState);
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!isInit) {
            isInit = true;
            if (presenter != null) {
                presenter.onCreate();
            }
            initData();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        super.onDestroy();
        if (openEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 初始化顶部tab item样式
     * @param tabLayout
     */
    protected void initTabLayoutItem(TabLayout tabLayout) {

        for (int i = 0; i < mTitles.size(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);

            tab.setCustomView(R.layout.layout_tabview_top);
            ViewHolder viewHolder = new ViewHolder(tab.getCustomView());
            viewHolder.tvTitle.setText(mTitles.get(i));

            if (i == 0) {
                viewHolder.setSelected(true);
            } else {
                viewHolder.setSelected(false);
            }
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ViewHolder holder = new ViewHolder(tab.getCustomView());
                holder.setSelected(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ViewHolder holder = new ViewHolder(tab.getCustomView());
                holder.setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    class ViewHolder {
        TextView tvTitle;

        ViewHolder(View view) {
            tvTitle = view.findViewById(R.id.tvTitleTop);
        }

        public void setSelected(boolean selected) {
            if (selected) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvTitle.setTextAppearance(R.style.TabItemSelect);
                } else {
                    tvTitle.setTextSize(18);
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvTitle.setTextAppearance(R.style.TabItemUnselect);
                } else {
                    tvTitle.setTextSize(14);
                }
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
    /**
     * 是否开启EventBus
     *
     * @return true 开启 false 关闭
     */
    protected boolean openEventBus() {
        return false;
    }

    /**
     * 初始化Presenter
     *
     * @return 对应的Presenter
     */
    protected abstract P initPresenter();

    /**
     * 获取布局资源id
     *
     * @return 资源id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局
     *
     * @param savedInstanceState 销毁时存储的数据
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    protected abstract void initData();


    /**
     * 是否使用DataBinding
     *
     * @return true 使用 false 不使用用
     */
    protected boolean isUseDataBinding() {
        return true;
    }

    /**
     * 是否需要添加透明状态栏
     *
     * @return true 开启 false 关闭
     */
    protected boolean addStatusBar() {
        return true;
    }

    /**
     * 初始化tab
     * @return
     */
    protected  List<String> initTitleList(){
        return null;

    }
}
