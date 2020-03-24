package com.davis.uitrapulltorefresh.demo.activity;

import android.view.View;
import android.widget.ListView;

import com.davis.ui.pulltorefresh.PtrClassicFrameLayout;
import com.davis.ui.pulltorefresh.PtrDefaultHandler2;
import com.davis.ui.pulltorefresh.PtrFrameLayout;
import com.davis.ui.pulltorefresh.header.RentalsSunHeaderView;
import com.davis.uitrapulltorefresh.demo.R;
import com.davis.uitrapulltorefresh.demo.adapter.GridViewAdapter;
import com.davis.uitrapulltorefresh.demo.bean.MainItemBean;
import com.davis.uitrapulltorefresh.demo.util.DUtil;

import java.util.ArrayList;
import java.util.List;

public class RentalsStyleActivity extends BaseTitleActivity {

    public PtrClassicFrameLayout mPtrFrame;
    private ListView listView;
    private GridViewAdapter adapter;
    private List<MainItemBean> datas = new ArrayList<MainItemBean>();
    private int count = 0;

    @Override
    public int setLayoutView() {
        return R.layout.activity_listview;
    }

    @Override
    public void init() {
        String title = getIntent().getStringExtra("title");

        setTopViewTitle(title);

        setTopViewBack();

        listView = (ListView)findViewById(R.id.listview);
        adapter = new GridViewAdapter(this, datas, R.layout.main_item);
        listView.setAdapter(adapter);

        mPtrFrame = (PtrClassicFrameLayout)findViewById(R.id.pulltorefresh);

        mPtrFrame.setLastUpdateTimeRelateObject(this);

        // header
        final RentalsSunHeaderView header = new RentalsSunHeaderView(this);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, (int) DUtil.dip2px(this,15), 0, (int)DUtil.dip2px(this,10));
        header.setUp(mPtrFrame);

        mPtrFrame.setLoadingMinTime(500);
        mPtrFrame.setDurationToCloseHeader(1000);
        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);
        // frame.setPullToRefresh(true);
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                // true-直接更新；false-有下拉的动作，然后在更新；
                mPtrFrame.autoRefresh(true);
            }
        }, 100);

        mPtrFrame.setPtrHandler(new PtrDefaultHandler2() {

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                //上拉加载
                loadData(false);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //下拉刷新
                //设置当前页为1
                //setCurrentPage(1);
                loadData(true);
            }

            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                if(datas.size() > 0){
                    return super.checkCanDoLoadMore(frame, listView, footer);
                }
                // false-禁止上拉加载
                return false;
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, listView, header);
            }
        });
    }

    private void loadData(final boolean firstPage){
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(firstPage){
                    datas.clear();
                    count = 1;
                }

                for(int i=0;i<12;i++){
                    MainItemBean itemBean = new MainItemBean(count, "" + count);
                    datas.add(itemBean);
                    count++;
                }

                adapter.refresh();
                mPtrFrame.refreshComplete();
            }
        }, 2000);
    }
}
