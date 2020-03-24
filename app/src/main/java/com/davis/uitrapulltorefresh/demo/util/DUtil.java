package com.davis.uitrapulltorefresh.demo.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DUtil {

    public static float getDensity(Context context){
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static float sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        //CLog.i(MyGlobal.TAG, "sp2px scale : " + fontScale);
        return (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static float dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        //CLog.i(MyGlobal.TAG, "dip2px pxValue : " + dpValue);
        //CLog.i(MyGlobal.TAG, "dip2px scale : " + scale);
        return (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static float px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        //CLog.i(MyGlobal.TAG, "px2dip scale : " + scale);
        return (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static float px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        //CLog.i(MyGlobal.TAG, "sp2sp fontScale : " + fontScale);
        return (pxValue / fontScale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
