package com.davis.uitrapulltorefresh.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.davis.uitrapulltorefresh.demo.R;

public abstract class BaseTitleActivity extends BaseActivity {

    public View topView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutView());
        init();
    }

    /**
     * 设置setContentView
     * @return
     */
    public abstract int setLayoutView();

    /**
     * 初始化
     */
    public abstract void init();

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        if(view instanceof LinearLayout){
            LinearLayout layout = (LinearLayout)view;
            topView = LayoutInflater.from(this).inflate(R.layout.top_view, null);
            layout.addView(topView, 0);
        }
        setContentView(view);
    }

    public void setTopViewTitle(String title){
        if(topView != null){
            TextView txt = (TextView)topView.findViewById(R.id.txt_top_title);
            txt.setText(title);
        }
    }

    public void setTopViewBack(){
        if(topView != null){
            ImageButton img = (ImageButton)topView.findViewById(R.id.imgbtn_top_back);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
