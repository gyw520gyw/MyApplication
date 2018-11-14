package demo.gyw.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Adapter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.gyw.com.myapplication.ui.progress.SimpleProgressActivity;
import demo.gyw.com.myapplication.ui.progress.SizeProgressActivity;
import demo.gyw.com.myapplication.utils.ExitAppUtil;


public class ProgressActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        ExitAppUtil.getInstance().addActivity(this);

        ButterKnife.inject(this);

    }

    @OnClick(R.id.btn_simple)
    void openSimpleProgressActivity() {
        startActivity(SimpleProgressActivity.class);
    }

    @OnClick(R.id.btn_size)
    void openSizeProgressActivity() {
        startActivity(SizeProgressActivity.class);
    }


    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }


    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExitAppUtil.getInstance().delActivity(this);
    }
}
