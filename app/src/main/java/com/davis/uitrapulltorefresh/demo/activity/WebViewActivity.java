package com.davis.uitrapulltorefresh.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.davis.ui.pulltorefresh.PtrClassicFrameLayout;
import com.davis.ui.pulltorefresh.PtrDefaultHandler2;
import com.davis.ui.pulltorefresh.PtrFrameLayout;
import com.davis.uitrapulltorefresh.demo.R;

public class WebViewActivity extends BaseTitleActivity {

    private PtrClassicFrameLayout mPtrFrame;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        init();
    }

    @Override
    public int setLayoutView() {
        return R.layout.activity_webview;
    }

    @Override
    public void init(){
        String title = getIntent().getStringExtra("title");

        setTopViewTitle(title);

        setTopViewBack();

        webView = (WebView)findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                mPtrFrame.refreshComplete();
            }
        });

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
                // false-禁止上拉加载
                return false;
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, webView, header);
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
                webView.loadUrl("http://www.baidu.com");
                //mPtrFrame.refreshComplete();
            }
        }, 2000);
    }

}
