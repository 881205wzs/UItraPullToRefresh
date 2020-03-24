package com.davis.uitrapulltorefresh.demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.davis.uitrapulltorefresh.demo.holder.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/1/2.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> listDatas = null;
    protected int mLayoutId;

    public CommonAdapter(Context context, List<T> data, int layoutId){
        this.mContext = context;
        this.listDatas = data;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return listDatas.size();
    }

    @Override
    public T getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 添加单条数据项
     * @param item
     */
    public void addItem(T item){
        this.listDatas.add(item);
    }

    /**
     * 设置数据源
     * @param data
     */
    public void setListDatas(List<T> data){
        this.listDatas = data;
    }

    /**
     * 清除数据源
     */
    public void clear(){
        this.listDatas.clear();
    }

    /**
     * 刷新数据源
     */
    public void refresh(){
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, mLayoutId);
        convert(holder, position, listDatas.get(position));
        return holder.getConvertView();
    }

    /**
     * 在子类中实现该方法
     * @param holder 列表项
     * @param item
     */
    public abstract void convert(ViewHolder holder, int position, T item);

}
