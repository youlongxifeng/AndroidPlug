package com.youlong.openlibrary;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
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
    public static final String TAG = AdsPlugSDK.class.getSimpleName();
    private static final String mDoorDuAds="com.doordu.loader.demo.DoorDuAds";
    public static final String DEX_CATALOG="dex";///data/data/package_name/files/dex目录

    static IDynamic sIDynamic;

    /**
     * 插件源文件路径
     */
    private static String apkPath = null;// 存放在sd卡里面的apk


    public AdsPlugSDK() {

    }

    public static void init(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                initDexClassLoader(context);
            }
        }).start();

    }

    /**
     * 获取原插件路径，第一次过去路径时为空，会从accessts里面将初始化的apk拷贝到指定目录下
     *
     * @param context
     * @return
     */
    public static String getApkPath(final Context context) {
        if (apkPath == null) {
            // apkPath = Environment.getExternalStorageDirectory() + File.separator + "app-debug.apk";// 存放在sd卡里面的apk
            final String appName = "doorduAdsapp.apk";
            final String dapp_debugpath = appName;
            // 文件是否已经存在？直接删除重来
            String pluginFilePath = context.getExternalFilesDir("apk").getAbsolutePath() + File.separator + appName;
            File pluginFile = new File(pluginFilePath);
            Log.i("TAG", "pluginFile==" + pluginFile.getAbsolutePath() + " " + pluginFile.exists());
            if (pluginFile.exists()) {
                //FileUtils.deleteQuietly(pluginFile);
                apkPath = pluginFile.getAbsolutePath();
            } else {
                apkPath = FileUtils.copyAssetsFileToAppFiles(context, dapp_debugpath, appName);
            }
        }

        return apkPath;
    }

    /**
     * 初始化 sIDynamic
     *
     * @param context
     * @return
     */
    public static IDynamic initDexClassLoader(final Context context) {
        if (sIDynamic == null) {
            Log.i(TAG, "initDexClassLoader======start");
            String defaultPath=getApkPath(context);
            if(defaultPath!=null){
            DexClassLoader classLoader = new DexClassLoader(defaultPath,
                    context.getDir(DEX_CATALOG, Context.MODE_PRIVATE).getAbsolutePath(), null, context.getClassLoader());
            Class mLoadClass = null;
            try {
                mLoadClass = classLoader.loadClass(mDoorDuAds);
                //获取构造器
                Constructor constructor = mLoadClass.getConstructor(new Class[]{});
                //创建一个对象
                sIDynamic = (IDynamic) constructor.newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }}
        }

        return sIDynamic;
    }

    /**
     * 初始化广告加载反射模式插件
     *
     * @param context
     * @param viewGroup
     */
    public static View initPlugView(final Context context, final ViewGroup viewGroup) {
        IDynamic iDynamics = initDexClassLoader(context);
        View mPlugView = null;
        if (iDynamics != null) {
            mPlugView = iDynamics.addPlugView(context, viewGroup);
        }
        return mPlugView;
    }

    /**
     * 加载一个fragment插件
     *
     * @param activity
     * @return
     */
    public static Fragment addPlugFragment(Activity activity) {
        IDynamic iDynamics = initDexClassLoader(activity);
        Fragment fragment = null;
        if (iDynamics != null) {
            fragment = iDynamics.addPlugFragment(activity);
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
        IDynamic iDynamics = initDexClassLoader(activity);
        Fragment fragment = null;
        if (iDynamics != null) {
            fragment = iDynamics.addPlugFragment(activity, containerViewId, build);
        }
        return fragment;
    }

    /**
     * s是否更新 正式发布不需要,升级功能会在插件中自动完成
     *
     * @return
     */
    public static boolean whetherUpdate(Activity activity) {
        boolean isUpdate = false;
        IDynamic iDynamics = initDexClassLoader(activity);
        if (iDynamics != null) {
            isUpdate = iDynamics.whetherUpdate();
        }
        Log.i("TAG", "WhetherUpdate==" + isUpdate);
        return isUpdate;
    }


    /**
     * 当前版本 正式发布不需要,升级功能会在插件中自动完成
     *
     * @return
     */
    public static int currentVersion(Activity activity) {
        int current = 0;
        IDynamic iDynamics = initDexClassLoader(activity);
        if (iDynamics != null) {
            current = iDynamics.currentVersion();
        }
        Log.i("TAG", "currentVersion==" + current);
        return current;
    }


    /**
     * 升级插件 正式发布不需要,升级功能会在插件中自动完成
     */
    public static void upgradePlug(Activity activity) {
        IDynamic iDynamics = initDexClassLoader(activity);
        if (iDynamics != null) {
            iDynamics.upgradePlug();
        }
        Log.i("TAG", "upgradePlug==");
    }


    /**
     * 升级插件
     */
    public static void upgradePlug2(Activity activity) {
        String apkPath2 = Environment.getExternalStorageDirectory() + File.separator + "app-debug2.apk";// 存放在sd卡里面的apk
        DexClassLoader classLoader = new DexClassLoader(apkPath2,
                activity.getDir(DEX_CATALOG, Context.MODE_PRIVATE).getAbsolutePath(), null, activity.getClassLoader());
        Class mLoadClass = null;
        try {
            mLoadClass = classLoader.loadClass(mDoorDuAds);
            //获取构造器
            Constructor constructor = mLoadClass.getConstructor(new Class[]{});
            //创建一个对象
            sIDynamic = (IDynamic) constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Log.i("TAG", "upgradePlug==");
    }


    /**
     * 、
     * 初始化fragment插件页面
     *
     * @param activity
     * @param containerViewId
     */
    /*public static void initFragment(Activity activity, @IdRes int containerViewId) {
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
    }*/

}
