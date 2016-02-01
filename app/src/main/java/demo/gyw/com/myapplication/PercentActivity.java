package demo.gyw.com.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.gyw.com.myapplication.ui.percent.PercentFragmentActivity;
import demo.gyw.com.myapplication.ui.percent.PercentLinearLayoutActivity;
import demo.gyw.com.myapplication.ui.percent.PercentRelativeActivity;
import demo.gyw.com.myapplication.ui.percent.PercentRelativeActivity2;

public class PercentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);
        ButterKnife.inject(this);
    }


    @OnClick(R.id.btn_relative)
    void openPercentRelativeActivity() {
        startActivity(PercentRelativeActivity.class);
    }


    @OnClick(R.id.btn_relative2)
    void openPercentRelativeActivity2() {
        startActivity(PercentRelativeActivity2.class);
    }

    @OnClick(R.id.btn_fragment)
    void openPercentFragmentActivity() {
        startActivity(PercentFragmentActivity.class);
    }

    @OnClick(R.id.btn_linearLayout)
    void openPercentLinearLayoutActivity() {
        startActivity(PercentLinearLayoutActivity.class);
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
}
