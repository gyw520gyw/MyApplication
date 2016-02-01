package demo.gyw.com.myapplication.mvp.presenter;

import demo.gyw.com.myapplication.mvp.model.User;

/**
 * Created by Administrator on 2016/1/27.
 */
public interface LoginPresenter {

    void checkData(User user);

    void onDestory();

}
