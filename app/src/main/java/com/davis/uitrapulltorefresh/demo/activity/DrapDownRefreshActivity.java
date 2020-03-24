package com.davis.uitrapulltorefresh.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class DrapDownRefreshActivity extends ListViewActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPtrFrame.setPullToRefresh(true);
    }
}
