package com.davis.uitrapulltorefresh.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.davis.ui.pulltorefresh.PtrClassicFrameLayout;
import com.davis.ui.pulltorefresh.PtrDefaultHandler2;
import com.davis.ui.pulltorefresh.PtrFrameLayout;
import com.davis.uitrapulltorefresh.demo.R;

public class ScrollViewActivity extends BaseTitleActivity implements View.OnClickListener {

    private PtrClassicFrameLayout mPtrFrame;
    private ScrollView scrollView;
    private LinearLayout layout_container;

    @Override
    public int setLayoutView() {
        return R.layout.activity_scrollview;
    }

    @Override
    public void init(){
        String title = getIntent().getStringExtra("title");

        setTopViewTitle(title);

        setTopViewBack();

        scrollView = (ScrollView)findViewById(R.id.scrollview);
        layout_container = (LinearLayout)findViewById(R.id.layout_container);

        mPtrFrame = (PtrClassicFrameLayout)findViewById(R.id.pulltorefresh);

        mPtrFrame.setLastUpdateTimeRelateObject(this);

        mPtrFrame.setPtrHandler(new PtrDefaultHandler2() {

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                //上拉加载
                //loadData(false);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //下拉刷新
                //设置当前页为1
                //setCurrentPage(1);
                layout_container.removeAllViews();
                loadData(true);
            }

            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {

                // false-禁止上拉加载
                return false;
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, scrollView, header);
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
                addView();
                mPtrFrame.refreshComplete();
            }
        }, 2000);
    }

    private void addView(){
        for(int i=0;i<5;i++){
            View view = (View) LayoutInflater.from(this).inflate(R.layout.item, null);
            TextView txt = (TextView)view.findViewById(R.id.textview);
            txt.setText(i + "");
            layout_container.addView(view);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtn_top_back:
                finish();
                break;
        }
    }
}
