package com.davis.uitrapulltorefresh.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class AutoRefreshActivity extends ListViewActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                // true-直接更新；false-有下拉的动作，然后在更新；
                mPtrFrame.autoRefresh(true);
            }
        }, 150);
    }
}
