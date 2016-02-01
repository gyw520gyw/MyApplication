package demo.gyw.com.myapplication.mvp.engine;

import demo.gyw.com.myapplication.mvp.model.User;

/**
 * Created by Administrator on 2016/1/27.
 */
public interface Login {
    void login(User user, OnLoginListener listener);
}
