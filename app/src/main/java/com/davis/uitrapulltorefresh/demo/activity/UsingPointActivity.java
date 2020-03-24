package com.davis.uitrapulltorefresh.demo.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.davis.ui.pulltorefresh.PtrClassicFrameLayout;
import com.davis.ui.pulltorefresh.PtrDefaultHandler2;
import com.davis.ui.pulltorefresh.PtrFrameLayout;
import com.davis.ui.pulltorefresh.header.StoreHouseHeader;
import com.davis.uitrapulltorefresh.demo.R;
import com.davis.uitrapulltorefresh.demo.adapter.GridViewAdapter;
import com.davis.uitrapulltorefresh.demo.bean.MainItemBean;
import com.davis.uitrapulltorefresh.demo.util.DUtil;

import java.util.ArrayList;
import java.util.List;

public class UsingPointActivity extends BaseTitleActivity {

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
        listView.setBackgroundColor(this.getResources().getColor(R.color.white));
        adapter = new GridViewAdapter(this, datas, R.layout.main_item);
        listView.setAdapter(adapter);

        mPtrFrame = (PtrClassicFrameLayout)findViewById(R.id.pulltorefresh);
        mPtrFrame.setBackgroundColor(getResources().getColor(R.color.gray));

        mPtrFrame.setLastUpdateTimeRelateObject(this);

        // header
        final StoreHouseHeader header = new StoreHouseHeader(this);
        header.setPadding(0, (int) DUtil.dip2px(this,15), 0, (int)DUtil.dip2px(this,0));

        header.initWithPointList(getPointList());

        mPtrFrame.setLoadingMinTime(500);
        mPtrFrame.setDurationToCloseHeader(1000);
        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);
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

    private ArrayList<float[]> getPointList() {
        // this point is taken from https://github.com/cloay/CRefreshLayout
        List<Point> startPoints = new ArrayList<Point>();
        startPoints.add(new Point(240, 80));
        startPoints.add(new Point(270, 80));
        startPoints.add(new Point(265, 103));
        startPoints.add(new Point(255, 65));
        startPoints.add(new Point(275, 80));
        startPoints.add(new Point(275, 80));
        startPoints.add(new Point(302, 80));
        startPoints.add(new Point(275, 107));

        startPoints.add(new Point(320, 70));
        startPoints.add(new Point(313, 80));
        startPoints.add(new Point(330, 63));
        startPoints.add(new Point(315, 87));
        startPoints.add(new Point(330, 80));
        startPoints.add(new Point(315, 100));
        startPoints.add(new Point(330, 90));
        startPoints.add(new Point(315, 110));
        startPoints.add(new Point(345, 65));
        startPoints.add(new Point(357, 67));
        startPoints.add(new Point(363, 103));

        startPoints.add(new Point(375, 80));
        startPoints.add(new Point(375, 80));
        startPoints.add(new Point(425, 80));
        startPoints.add(new Point(380, 95));
        startPoints.add(new Point(400, 63));

        List<Point> endPoints = new ArrayList<Point>();
        endPoints.add(new Point(270, 80));
        endPoints.add(new Point(270, 110));
        endPoints.add(new Point(270, 110));
        endPoints.add(new Point(250, 110));
        endPoints.add(new Point(275, 107));
        endPoints.add(new Point(302, 80));
        endPoints.add(new Point(302, 107));
        endPoints.add(new Point(302, 107));

        endPoints.add(new Point(340, 70));
        endPoints.add(new Point(360, 80));
        endPoints.add(new Point(330, 80));
        endPoints.add(new Point(340, 87));
        endPoints.add(new Point(315, 100));
        endPoints.add(new Point(345, 98));
        endPoints.add(new Point(330, 120));
        endPoints.add(new Point(345, 108));
        endPoints.add(new Point(360, 120));
        endPoints.add(new Point(363, 75));
        endPoints.add(new Point(345, 117));

        endPoints.add(new Point(380, 95));
        endPoints.add(new Point(425, 80));
        endPoints.add(new Point(420, 95));
        endPoints.add(new Point(420, 95));
        endPoints.add(new Point(400, 120));
        ArrayList<float[]> list = new ArrayList<float[]>();

        int offsetX = Integer.MAX_VALUE;
        int offsetY = Integer.MAX_VALUE;

        for (int i = 0; i < startPoints.size(); i++) {
            offsetX = Math.min(startPoints.get(i).x, offsetX);
            offsetY = Math.min(startPoints.get(i).y, offsetY);
        }
        for (int i = 0; i < endPoints.size(); i++) {
            float[] point = new float[4];
            point[0] = startPoints.get(i).x - offsetX;
            point[1] = startPoints.get(i).y - offsetY;
            point[2] = endPoints.get(i).x - offsetX;
            point[3] = endPoints.get(i).y - offsetY;
            list.add(point);
        }
        return list;
    }
}
