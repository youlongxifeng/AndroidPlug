package com.youlong.openlibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

/**
 * @author Administrator
 * @name AndroidPlug
 * @class name：com.youlong.openlibrary
 * @class describe
 * @time 2018/3/7 9:13
 * @change
 * @class describe
 */

public interface IDynamic {
    /**
     *
     * @param context
     * @param viewGroup 插件显示控件父类
     */
    void addPlugView(Context context, ViewGroup viewGroup);

    /**
     * 插件fragment显示类
     * @param context
     * @param containerViewId
     */
   void addPlugFragment(Activity context, int containerViewId,Bundle build) ;
}
