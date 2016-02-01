package demo.gyw.com.myapplication.mvp.presenter;

import android.content.Context;

import demo.gyw.com.myapplication.mvp.View.LoginView;
import demo.gyw.com.myapplication.mvp.engine.Login;
import demo.gyw.com.myapplication.mvp.engine.LoginImpl;
import demo.gyw.com.myapplication.mvp.engine.OnLoginListener;
import demo.gyw.com.myapplication.mvp.model.User;

/**
 * Created by Administrator on 2016/1/27.
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginListener {

    private LoginView mLoginView;
    private Context mContext;
    private Login loginImpl;

    public LoginPresenterImpl(Context context, LoginView loginView) {
        this.mContext = context;
        this.mLoginView = loginView;
        this.loginImpl = new LoginImpl();
    }

    @Override
    public void checkData(User user) {
        if(mLoginView != null) {
            mLoginView.showProgress();
        }
        loginImpl.login(user, this);
    }

    @Override
    public void onDestory() {
        mLoginView = null;
    }

    @Override
    public void loginSuccess() {
        if(mLoginView != null) {
            mLoginView.hideProgress();
            mLoginView.navigateToHome();
        }
    }

    @Override
    public void loginFailed() {
        if(mLoginView != null) {
            mLoginView.hideProgress();
            mLoginView.showFailed();
        }
    }
}
