package demo.gyw.com.myapplication.retrofit;

/**
 * Created by Administrator on 2015/12/22.
 */
public class MainFractory {

    private static TeztApi mApi;

    protected static final Object lock = new Object();

    //测试
    public static final String Host = "http://hsapi.livedevice.cn/";//http://hsapi.livedevice.cn/healthstore/index.php?m=App&a=appinfo

    public static TeztApi getInstance() {
        synchronized (lock) {
            if(mApi == null) {
                mApi = new MainRetrofit().getServer();
            }
        return mApi;
        }
    }
}
