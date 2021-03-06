package com.doordu.loader.demo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.youlong.openlibrary.IDynamic;
import com.youlong.plugapp.PlugFragment;
import com.youlong.plugapp.PlugView;


/**
 * @author Administrator
 * @name classLoaderDemo
 * @class name：com.doordu.loader.demo
 * @class describe
 * @time 2018/3/5 11:39
 * @change
 * @class describe
 */

public class DoorDuAds implements IDynamic {

    @Override
    public void addPlugView(Context context, ViewGroup viewGroup) {
        Log.i("TAG", "DoorDuAds   createAdsView=" + context.getClass().getName());
        PlugView advertisementView = new PlugView(context);
        viewGroup.addView(advertisementView);
        Toast.makeText(context, "这是一个初始化View", Toast.LENGTH_LONG).show();

    }


    @Override
    public void addPlugFragment(Activity context, int containerViewId,Bundle build) {
        Log.i("TAG", "DoorDuAds   createAdsView=" + context.getClass().getName());
        PlugFragment fragment = new     PlugFragment();
        fragment.setArguments(build);
        FragmentManager fm = context.getFragmentManager();
        Log.i("TAG", "fragment===" + fragment.getClass().getName());
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Log.i("TAG", "fragment===" + fragment.getClass().getName());
        fragmentTransaction.add(containerViewId, fragment);//(R.id.my_fragment, fragment);
        Log.i("TAG", "fragment===" + fragment.getClass().getName());
        fragmentTransaction.commit();

    }
}
