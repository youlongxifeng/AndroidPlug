package com.youlong.openlibrary;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.view.ViewGroup;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import dalvik.system.DexClassLoader;

/**
 * @author Administrator
 * @name AndroidPlug
 * @class name：com.youlong.openlibrary
 * @class describe
 * @time 2018/3/7 9:13
 * @change
 * @class describe
 */

public class AdsPlugSDK {

    /**
     * 插件源文件路径
     */
    private static String apkPath = null;// 存放在sd卡里面的apk


    public AdsPlugSDK() {
    }

    public static String getApkPath() {
        if(apkPath==null){
            apkPath = Environment.getExternalStorageDirectory() + File.separator + "app-debug.apk";// 存放在sd卡里面的apk
        }
        return apkPath;
    }

    public static void setApkPath(String apkPath) {
        AdsPlugSDK.apkPath = apkPath;
    }

    /**
     * 初始化广告加载反射模式
     *
     * @param context
     * @param viewGroup
     */
    public static void initPlugView(final Context context, final ViewGroup viewGroup) {

        try {
            DexClassLoader classLoader = new DexClassLoader(apkPath, context.getFilesDir().getAbsolutePath(),
                    null, context.getClassLoader());
            Class mLoadClass = classLoader.loadClass("com.doordu.loader.demo.DoorDuAds");

            //获取构造器
            Constructor constructor = mLoadClass.getConstructor(new Class[]{});
            //完成私有构造函数的调用
            constructor.setAccessible(true);
            mLoadClass.getInterfaces();
            //创建一个对象
            IDynamic TestBActivity = (IDynamic) constructor.newInstance();
            TestBActivity.addPlugView(context, viewGroup);
           /* //创建一个对象
            Object createObject = constructor.newInstance();
            Method getMoney = mLoadClass.getMethod("addAdsView", Context.class, ViewGroup.class);
            getMoney.setAccessible(true);
            Object money = getMoney.invoke(createObject, context, viewGroup);*/

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 、
     * 初始化fragment插件页面
     *
     * @param activity
     * @param containerViewId
     */
    public static void initFragment(Activity activity, @IdRes int containerViewId) {
        try {
            String className = "com.doordu.loader.demo.DynamicFragment";//这个要根据插件里面的文件
            DexClassLoader dexClassLoader = new DexClassLoader(apkPath,
                    activity.getDir("dex", Context.MODE_PRIVATE).getAbsolutePath(), null, activity.getClassLoader());
            Fragment fragment = (Fragment) dexClassLoader.loadClass(className).newInstance();
            FragmentManager fm = activity.getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.add(containerViewId, fragment);//(R.id.my_fragment, fragment);
            fragmentTransaction.commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Fragment initFragment(Activity activity) {
        String className = "com.doordu.loader.demo.DynamicFragment";
        Fragment fragment = null;
        try {
            DexClassLoader dexClassLoader = new DexClassLoader(apkPath,
                    activity.getDir("dex", Context.MODE_PRIVATE).getAbsolutePath(), null, activity.getClassLoader());
            fragment = (Fragment) dexClassLoader.loadClass(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return fragment;
    }

    /**
     * 加载广告fragment
     *
     * @param activity
     * @param containerViewId
     * @param build
     * @return
     */
    public static Fragment addPlugFragment(Activity activity, int containerViewId, Bundle build) {
        Fragment fragment = null;
        try {
            DexClassLoader classLoader = new DexClassLoader(apkPath,
                    activity.getDir("dex", Context.MODE_PRIVATE).getAbsolutePath(), null, activity.getClassLoader());
            Class mLoadClass = classLoader.loadClass("com.doordu.loader.demo.DoorDuAds");
            //获取构造器
            Constructor constructor = mLoadClass.getConstructor(new Class[]{});
            //创建一个对象
            IDynamic iDynamic = (IDynamic) constructor.newInstance();
            iDynamic.addPlugFragment(activity, containerViewId, build);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return fragment;
    }


    /**
     * 广告升级
     *
     * @return
     */
    public AdsPlugSDK AdsPlugUpgrade() {
        return this;
    }

    /**
     * 广告注销
     *
     * @return
     */
    public AdsPlugSDK AdsPlugDestroy() {
        return this;
    }
}
