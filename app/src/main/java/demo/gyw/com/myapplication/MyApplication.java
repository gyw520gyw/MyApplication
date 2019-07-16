package demo.gyw.com.myapplication;

import android.app.Application;

import com.taobao.weex.InitConfig;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.adapter.DefaultWXHttpAdapter;

/**
 * author: gyw
 * date: 2016/5/20.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        DbCore.init(this);

        initWeex();

        initDebugEnvironment(true, " 192.168.1.195");
//        WXSDKEngine.reload();
    }

    private void initDebugEnvironment(boolean enable, String host) {
        WXEnvironment.sRemoteDebugMode = enable;
        WXEnvironment.sRemoteDebugProxyUrl = "ws://" + host + ":8089/debugProxy/native";
    }

    private void initWeex() {
        InitConfig config = new InitConfig.Builder()
                //图片库接口
//                .setImgAdapter(new FrescoImageAdapter())
                //网络库接口
                .setHttpAdapter(new DefaultWXHttpAdapter())
                .build();
        WXSDKEngine.initialize(this, config);
    }
}
