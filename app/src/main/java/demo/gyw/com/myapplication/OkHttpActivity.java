package demo.gyw.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class OkHttpActivity extends Activity {

    @InjectView(R.id.tv_ok_http)
    TextView mTv;

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.inject(this);

        View.inflate(this, R.layout.activity_ok_http, null);

        initAsyncData();

        initSynchData();

    }


    //异步
    private void initAsyncData() {

        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i("GYW", "请求失败");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                Headers h =  response.headers();
                for(int i = 0; i < h.size(); i++) {
                    System.out.println(h.name(i) + "  =  " +h.value(i));
                }

                final String str = response.body().string();

                System.out.println("异步 : "+str);

            }
        });
    }

    //同步
    private void initSynchData() {

        final Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();


        new Thread() {

            @Override
            public void run() {

                try {
                    Response response = client.newCall(request).execute();

                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    Headers h =  response.headers();
                    for(int i = 0; i < h.size(); i++) {
                        System.out.println(h.name(i) + "  =  " +h.value(i));
                    }

                    final String str = response.body().string();

                    System.out.println("同步 : "+str);

                    OkHttpActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTv.setText(str);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }
}
