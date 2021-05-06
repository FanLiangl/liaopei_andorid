package com.zm.liaopei.modules.main.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zm.liaopei.R;
import com.zm.liaopei.base.BaseHolderWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
/**
 * @author fanliangliang
 * @description:首页列表适配器
 * @date : 2021/4/29 16:52
 */
public class MainListAdapter extends BaseQuickAdapter<String, BaseHolderWrapper> {

    public MainListAdapter(int layoutResId, @Nullable List<String> data) {
        super(R.layout.main_list_adapter, data);
    }

    @Override
    protected void convert(@NotNull BaseHolderWrapper baseHolderWrapper, String s) {

    }
}
