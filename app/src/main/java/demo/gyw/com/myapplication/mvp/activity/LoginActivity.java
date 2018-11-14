package demo.gyw.com.myapplication.mvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import demo.gyw.com.myapplication.R;
import demo.gyw.com.myapplication.mvp.View.LoginView;
import demo.gyw.com.myapplication.mvp.model.User;
import demo.gyw.com.myapplication.mvp.presenter.LoginPresenter;
import demo.gyw.com.myapplication.mvp.presenter.LoginPresenterImpl;

public class LoginActivity extends FragmentActivity implements LoginView {

    @InjectView(R.id.et_username)
    EditText mUsernameEt;

    @InjectView(R.id.et_password)
    EditText mPasswordEt;

    @InjectView(R.id.btn_login)
    Button mLoginBtn;

    @InjectView(R.id.progressbar)
    ProgressBar mProgressBar;

    @InjectView(R.id.tv_login)
    TextView mLoginTv;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        presenter = new LoginPresenterImpl(this, this);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public String getUserName() {
        return mUsernameEt.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mPasswordEt.getText().toString().trim();
    }

    @Override
    public void showFailed() {
        mLoginTv.setText("登录失败");
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, OperateActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_login)
    void login() {
        mLoginTv.setText("");
        User user = new User();
        user.setUsername(getUserName());
        user.setPassword(getPassword());

        presenter.checkData(user);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestory();
        super.onDestroy();
    }
}
