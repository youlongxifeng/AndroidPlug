package com.youlong.plug.androidplug;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.youlong.openlibrary.AdsPlugSDK;

import java.io.File;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    //宿主Activity，专用于加载插件apk的Fragment

    private String apkPath;
    private String className;
    private DexClassLoader dexClassLoader;
    private Resources resources;
    private AssetManager assetManager;
    final String path = Environment.getExternalStorageDirectory() + "/";
    final String filename = "app-debug.apk";
    /**
     * "app-debug.apk";
     **/
    File file;
    Button open_view, open_fragment,bt_open_version,bt_open_network;
    RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file = new File(path, filename);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl_context);
        open_view = findViewById(R.id.bt_open_view);
        open_view.setOnClickListener(this);
        open_fragment = findViewById(R.id.bt_open_fragment);
        open_fragment.setOnClickListener(this);
        bt_open_version=findViewById(R.id.bt_open_version);
        bt_open_version.setOnClickListener(this);
        bt_open_network=findViewById(R.id.bt_open_network);
        bt_open_network.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_open_view:
                apkPath = AdsPlugSDK.getApkPath();
                //assetManager = createAssetManager(apkPath);
                resources = getBundleResource(MainActivity.this, apkPath);
                AdsPlugSDK.initPlugView(this, mRelativeLayout);
                break;
            case R.id.bt_open_fragment:
                apkPath = AdsPlugSDK.getApkPath();
                resources = getBundleResource(MainActivity.this, apkPath);
                Bundle bundle=new Bundle();
                bundle.putString("bundler","这是一个例子bundle");
                AdsPlugSDK.addPlugFragment(this, R.id.context_fragment,bundle);

               /*
                Fragment fragment = AdsPlugSDK.initFragment(MainActivity.this);

                if (fragment != null) {
                    Bundle bundle=new Bundle();
                    bundle.putString("bundle","这是一个例子bundle");
                    Log.i("TAG", "fragment===" + fragment.getClass().getName());
                    FragmentManager fm = getFragmentManager();
                    Log.i("TAG", "fragment===" + fragment.getClass().getName());
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    Log.i("TAG", "fragment===" + fragment.getClass().getName());
                    fragmentTransaction.add(R.id.context_fragment, fragment);//(R.id.my_fragment, fragment);
                    Log.i("TAG", "fragment===" + fragment.getClass().getName());
                    fragmentTransaction.commit();
                }*/
                break;
            case R.id.bt_open_version://获取插件版本
                AdsPlugSDK.WhetherUpdate(this);
                AdsPlugSDK.currentVersion(this);

                break;
            case R.id.bt_open_network://获取插件网络内容
                AdsPlugSDK.upgradePlug(this);
                break;
                default:
                    break;
        }

    }

    /**
     * 初始化Assets文件,因为引用插件里面的资源会被当成没有编译的源文件所以通过assets获取
     * @param apkPath
     * @return
     */
    private static AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            AssetManager.class.getDeclaredMethod("addAssetPath", String.class).invoke(
                    assetManager, apkPath);
            return assetManager;
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    /**
     * 获取资源文件
     * @param context
     * @param apkPath
     * @return
     */
    public static Resources getBundleResource(Context context, String apkPath) {
        AssetManager assetManager = createAssetManager(apkPath);
        return new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
    }


    @Override
    public ClassLoader getClassLoader() {
        LogUtils.i("TAG", "getClassLoader===" + (dexClassLoader == null));
        return dexClassLoader == null ? super.getClassLoader() : dexClassLoader;
    }
    /**
     * 因为需要获取插件里面的资源文件所以需要重写该方法
     * @return
     */
    @Override
    public Resources getResources() {
        return resources == null ? super.getResources() : resources;
    }


    /*@Override
    public AssetManager getAssets() {
        return assetManager == null ? super.getAssets() : assetManager;
    }*/
}
