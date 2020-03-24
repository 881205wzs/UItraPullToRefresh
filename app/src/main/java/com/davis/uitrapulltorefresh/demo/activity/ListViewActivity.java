package com.davis.uitrapulltorefresh.demo.activity;

import android.view.View;
import android.widget.ListView;

import com.davis.ui.pulltorefresh.PtrClassicFrameLayout;
import com.davis.ui.pulltorefresh.PtrDefaultHandler2;
import com.davis.ui.pulltorefresh.PtrFrameLayout;
import com.davis.uitrapulltorefresh.demo.R;
import com.davis.uitrapulltorefresh.demo.adapter.GridViewAdapter;
import com.davis.uitrapulltorefresh.demo.bean.MainItemBean;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends BaseTitleActivity {

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
    public void init(){
        String title = getIntent().getStringExtra("title");

        setTopViewTitle(title);

        setTopViewBack();

        listView = (ListView)findViewById(R.id.listview);
        adapter = new GridViewAdapter(this, datas, R.layout.main_item);
        listView.setAdapter(adapter);

        mPtrFrame = (PtrClassicFrameLayout)findViewById(R.id.pulltorefresh);

        mPtrFrame.setLastUpdateTimeRelateObject(this);

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

        mPtrFrame.setResistance(1.7f); // you can also set foot and header separately
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(1000);  // you can also set foot and header separately
        // default is false
        mPtrFrame.setPullToRefresh(false);

        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);

        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                // true-直接更新；false-有下拉的动作，然后在更新；
                mPtrFrame.autoRefresh(false);
            }
        }, 100);
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
