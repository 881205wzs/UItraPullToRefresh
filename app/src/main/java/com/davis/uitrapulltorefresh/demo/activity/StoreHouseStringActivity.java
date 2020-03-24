package com.davis.uitrapulltorefresh.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.davis.ui.pulltorefresh.PtrClassicFrameLayout;
import com.davis.ui.pulltorefresh.PtrDefaultHandler2;
import com.davis.ui.pulltorefresh.PtrFrameLayout;
import com.davis.ui.pulltorefresh.PtrUIHandler;
import com.davis.ui.pulltorefresh.header.StoreHouseHeader;
import com.davis.ui.pulltorefresh.indicator.PtrIndicator;
import com.davis.uitrapulltorefresh.demo.R;
import com.davis.uitrapulltorefresh.demo.adapter.GridViewAdapter;
import com.davis.uitrapulltorefresh.demo.bean.MainItemBean;
import com.davis.uitrapulltorefresh.demo.util.DUtil;

import java.util.ArrayList;
import java.util.List;

public class StoreHouseStringActivity extends BaseTitleActivity {

    final String[] mStringList = {"DAVIS", "TMALL 11-11"};
    private String mTitlePre;

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

        listView = (ListView) findViewById(R.id.listview);
        listView.setBackgroundColor(this.getResources().getColor(R.color.white));

        adapter = new GridViewAdapter(this, datas, R.layout.main_item);
        listView.setAdapter(adapter);

        mPtrFrame = (PtrClassicFrameLayout)findViewById(R.id.pulltorefresh);
        mPtrFrame.setBackgroundColor(getResources().getColor(R.color.gray));

        // header
        final StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, (int) DUtil.dip2px(this,15), 0, 0);

        header.initWithString(mStringList[0]);
        //header.setBackgroundColor(getResources().getColor(R.color.gray));
        header.setTextColor(getResources().getColor(R.color.colorAccent));
        header.setLineWidth((int)DUtil.dip2px(this, 2));

        // for changing string
        mPtrFrame.addPtrUIHandler(new PtrUIHandler() {

            private int mLoadTime = 0;

            @Override
            public void onUIReset(PtrFrameLayout frame) {
                mLoadTime++;
                String string = mStringList[mLoadTime % mStringList.length];
                header.initWithString(string);
            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {
                String string = mStringList[mLoadTime % mStringList.length];
                //setHeaderTitle(mTitlePre + string);
            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });

        mPtrFrame.setLoadingMinTime(500);
        mPtrFrame.setDurationToCloseHeader(1000);
        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                // true-直接更新；false-有下拉的动作，然后在更新；
                mPtrFrame.autoRefresh(false);
            }
        }, 100);

        /*mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                loadData(true);
            }
        });*/

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
