package demo.gyw.com.myapplication.mvp.engine;

import demo.gyw.com.myapplication.mvp.model.User;

/**
 * Created by Administrator on 2016/1/27.
 */
public interface OnLoginListener {
    // 登录成功
    void loginSuccess();
    // 登录失败
    void loginFailed();
}
