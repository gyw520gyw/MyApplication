package demo.gyw.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import demo.gyw.com.myapplication.retrofit.AppDetailBean;
import demo.gyw.com.myapplication.retrofit.AppInfo;
import demo.gyw.com.myapplication.retrofit.MainFractory;
import demo.gyw.com.myapplication.retrofit.TeztApi;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RetrofitActivity extends AppCompatActivity {


    private static final String TAG = "RetrofitActivity";
    private String appid = "A000000000361";

    TeztApi mApi = MainFractory.getInstance();

    @InjectView(R.id.tv)
    TextView tv;

    @InjectView(R.id.tv2)
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.inject(this);


        AppInfo appInfo = new AppInfo();
        appInfo.app_id = appid;

        AppDetailBean appDetailBean = new AppDetailBean();
        AppDetailBean.ResEntity resEntity = new AppDetailBean.ResEntity();
        resEntity.setApp_id(appid);
        appDetailBean.setRes(resEntity);

        Call<AppDetailBean> call = mApi.getSoftwareDetail("App", "appinfo", appid);
        call.enqueue(new Callback<AppDetailBean>() {
            @Override
            public void onResponse(Response<AppDetailBean> response, Retrofit retrofit) {
                Log.d(TAG, " >>>> gyw 成功  " + response.body());
                tv.setText("获取到的数据 : " + response.body().toString());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, " >>>> gyw + 请求失败  " + t.toString());
                tv.setText("请求失败  : " + t.toString());
            }
        });


        String linkUrl = "http://hsapi.livedevice.cn/healthstore/index.php?m=App&a=appinfo&app_id=A000000000361";

        Observable<AppDetailBean> observable = mApi.getSoftwareDetail2(linkUrl);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<AppDetailBean, AppDetailBean.ResEntity>() {
            @Override
            public AppDetailBean.ResEntity call(AppDetailBean appDetailBean) {
                return appDetailBean.getRes();
            }
        }).subscribe(new Subscriber<AppDetailBean.ResEntity>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, " >>>> gyw 完成 2222  ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, " >>>> gyw 失败 2222  " + e.toString());
            }

            @Override
            public void onNext(AppDetailBean.ResEntity resEntity) {
                Log.d(TAG, " >>>> gyw 成功 2222  " + resEntity.toString());
                tv2.setText(resEntity.toString());
            }
        });
    }
}
