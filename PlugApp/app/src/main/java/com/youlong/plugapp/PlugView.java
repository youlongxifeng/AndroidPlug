package com.youlong.plugapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * @author Administrator
 * @name PlugApp
 * @class name：com.youlong.plugapp
 * @class describe
 * @time 2018/3/7 14:32
 * @change
 * @class describe
 */

public class PlugView extends FrameLayout {
    public PlugView(@NonNull Context context) {
        super(context);
        initUIView(context);
    }

    public PlugView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUIView(context);
    }

    public PlugView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUIView(context);
    }

    private static final String CACHE_NAME = "doordu/html5";




    /**
     * 初始化广告UI
     *
     * @param context
     */
    private void initUIView(Context context) {
        Log.i("TAG", "initUIView   context=" + context.getClass().getName());
        FrameLayout frame = new FrameLayout(context);
        final int m = ViewGroup.LayoutParams.MATCH_PARENT;
        ViewGroup.LayoutParams layout = new ViewGroup.LayoutParams(m, m);
        frame.setLayoutParams(layout);
        frame.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        ImageView mDefImage = new ImageView(context);
        mDefImage.setLayoutParams(layout);
        mDefImage.setScaleType(ScaleType.CENTER_CROP);
        mDefImage.setImageResource(R.drawable.saiyan);
        frame.addView(mDefImage);
        Log.i("TAG", "initUIView   mDefImage=" + mDefImage.getHeight()+"   mDefImage="+mDefImage.getWidth()+"  m="+m);
        addView(frame);
        // initWebView(context);
    }



}
