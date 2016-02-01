package demo.gyw.com.myapplication.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2015/12/22.
 */
public class MainRetrofit {

    TeztApi mApi;

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").serializeNulls().create();

    MainRetrofit() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(10, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainFractory.Host)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        mApi = retrofit.create(TeztApi.class);
    }

    public TeztApi getServer() {
        return mApi;
    }
}
