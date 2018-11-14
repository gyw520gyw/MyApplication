package demo.gyw.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.gyw.com.myapplication.mvp.activity.LoginActivity;

/**
 * 需求分析：
 *  1. 用户登录
 *     用户， 登录，
 */

public class MvpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn)
    void openLoginAction() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
