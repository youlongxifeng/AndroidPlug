package com.youlong.plug.androidplug;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.youlong.openlibrary.AdsPlugSDK;

import java.io.File;

/**
 * @author Administrator
 * @name AndroidPlug
 * @class name：com.youlong.plug.androidplug
 * @class describe
 * @time 2018/3/8 10:49
 * @change
 * @class describe
 */

public class UpdateActivity extends AppCompatActivity implements OnClickListener {
    Button bt_update, bt_open_install, bt_open_init2, bt_open_init1;
    private Resources resources;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        bt_open_install = findViewById(R.id.bt_open_install);
        bt_open_install.setOnClickListener(this);
        bt_update = findViewById(R.id.bt_update);
        bt_update.setOnClickListener(this);
        bt_open_init2 = findViewById(R.id.bt_open_init2);
        bt_open_init2.setOnClickListener(this);
        bt_open_init1 = findViewById(R.id.bt_open_init1);
        bt_open_init1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_update://检查更新版本

                break;
            case R.id.bt_open_install://安装新插件
                AdsPlugSDK.upgradePlug2(this);
                break;
            case R.id.bt_open_init1:
                String apkPath = AdsPlugSDK.getApkPath(this);
                resources = getBundleResource(UpdateActivity.this, apkPath);
                Bundle bundle2 = new Bundle();
                bundle2.putString("bundler", "这是一个例子bundle,更新前的例子");
                AdsPlugSDK.addPlugFragment(this, R.id.context_fragment_update, bundle2);
                break;
            case R.id.bt_open_init2:
                String apkPath2 = Environment.getExternalStorageDirectory() + File.separator + "app-debug2.apk";// 存放在sd卡里面的apk
                resources = getBundleResource(UpdateActivity.this, apkPath2);
                Bundle bundle = new Bundle();
                bundle.putString("bundler", "这是一个例子bundle,更新后的例子");
                AdsPlugSDK.addPlugFragment(this, R.id.context_fragment_update_2, bundle);
                break;
        }
    }

    /**
     * 因为需要获取插件里面的资源文件所以需要重写该方法
     *
     * @return
     */
    @Override
    public Resources getResources() {
        return resources == null ? super.getResources() : resources;
    }

    /**
     * 初始化Assets文件,因为引用插件里面的资源会被当成没有编译的源文件所以通过assets获取
     *
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
     *
     * @param context
     * @param apkPath
     * @return
     */
    public static Resources getBundleResource(Context context, String apkPath) {
        AssetManager assetManager = createAssetManager(apkPath);
        return new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
    }

}
