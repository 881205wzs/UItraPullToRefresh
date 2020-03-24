package com.davis.uitrapulltorefresh.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.davis.uitrapulltorefresh.demo.activity.AutoRefreshActivity;
import com.davis.uitrapulltorefresh.demo.activity.BaseActivity;
import com.davis.uitrapulltorefresh.demo.activity.DrapDownRefreshActivity;
import com.davis.uitrapulltorefresh.demo.activity.GridViewActivity;
import com.davis.uitrapulltorefresh.demo.activity.ListViewActivity;
import com.davis.uitrapulltorefresh.demo.activity.MaterialStyleActivity;
import com.davis.uitrapulltorefresh.demo.activity.ReleaseRefreshActivity;
import com.davis.uitrapulltorefresh.demo.activity.RentalsStyleActivity;
import com.davis.uitrapulltorefresh.demo.activity.ScrollViewActivity;
import com.davis.uitrapulltorefresh.demo.activity.StoreHouseArrayActivity;
import com.davis.uitrapulltorefresh.demo.activity.StoreHouseStringActivity;
import com.davis.uitrapulltorefresh.demo.activity.UsingPointActivity;
import com.davis.uitrapulltorefresh.demo.activity.WebViewActivity;
import com.davis.uitrapulltorefresh.demo.adapter.GridViewAdapter;
import com.davis.uitrapulltorefresh.demo.bean.MainItemBean;
import com.davis.uitrapulltorefresh.demo.util.DUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener {

    private View topView;
    private GridView gridView;
    private GridViewAdapter adapter;
    private List<MainItemBean> datas = new ArrayList<MainItemBean>();
    private ImageButton img_back;
    private TextView txt_title;

    private static String[] titles = new String[]{
            "包含GridView",
            "包含ListView",
            "包含ScrollView",
            "包含WebView",
            "释放刷新",
            "下拉刷新",
            "自动刷新",
            "StoreHouse Style 使用xml中的字符串数组",
            "StoreHouse Style 使用字符串",
            "Using Point List",
            "Material Style",
            "Rentals Style"
    };

    private static Class[] classes = new Class[]{
            GridViewActivity.class,
            ListViewActivity.class,
            ScrollViewActivity.class,
            WebViewActivity.class,
            ReleaseRefreshActivity.class,
            DrapDownRefreshActivity.class,
            AutoRefreshActivity.class,
            StoreHouseArrayActivity.class,
            StoreHouseStringActivity.class,
            UsingPointActivity.class,
            MaterialStyleActivity.class,
            RentalsStyleActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        loadMenu();
        View view = (View)findViewById(R.id.top_view);
        img_back = (ImageButton)view.findViewById(R.id.imgbtn_top_back);
        img_back.setVisibility(View.INVISIBLE);

        txt_title = (TextView)view.findViewById(R.id.txt_top_title);

        gridView = (GridView)findViewById(R.id.main_gridview);

        gridView.setNumColumns(GridView.AUTO_FIT);
        gridView.setVerticalSpacing((int) DUtil.dip2px(this, 10));
        gridView.setHorizontalSpacing((int) DUtil.dip2px(this, 10));
        gridView.setColumnWidth((int)DUtil.dip2px(this, 130));

        adapter = new GridViewAdapter(this, datas, R.layout.main_item);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(this);
    }

    private void loadMenu(){
        datas.clear();
        for(int i=0;i<titles.length;i++){
            MainItemBean item = new MainItemBean(i, titles[i], classes[i]);
            datas.add(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try{
            MainItemBean item = (MainItemBean)parent.getAdapter().getItem(position);
            Intent intent = new Intent(MainActivity.this, item.getaClass());
            intent.putExtra("title", item.getName());
            startActivity(intent);
        }catch (Exception ex){

        }
    }
}
