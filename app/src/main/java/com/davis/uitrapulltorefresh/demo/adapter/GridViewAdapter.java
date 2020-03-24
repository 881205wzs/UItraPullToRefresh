package com.davis.uitrapulltorefresh.demo.adapter;

import android.content.Context;

import com.davis.uitrapulltorefresh.demo.R;
import com.davis.uitrapulltorefresh.demo.bean.MainItemBean;
import com.davis.uitrapulltorefresh.demo.holder.ViewHolder;

import java.util.List;

public class GridViewAdapter extends CommonAdapter<MainItemBean> {

    public GridViewAdapter(Context context, List<MainItemBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, int position, MainItemBean item) {
        holder.getTextView(R.id.main_item_info).setText(item.getName());
    }
}
