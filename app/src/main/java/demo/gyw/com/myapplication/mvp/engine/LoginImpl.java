package demo.gyw.com.myapplication.mvp.engine;

import android.os.Handler;
import android.os.SystemClock;

import demo.gyw.com.myapplication.mvp.model.User;

/**
 * Created by Administrator on 2016/1/27.
 */
public class LoginImpl implements Login {
    @Override
    public void login(final User user, final OnLoginListener listener) {
        //子线程模拟耗时操作
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ("gyw".equals(user.username) && "123".equals(user.password)) {
                    listener.loginSuccess();
                } else {
                    listener.loginFailed();
                }
            }
        }, 2000);
    }
}
