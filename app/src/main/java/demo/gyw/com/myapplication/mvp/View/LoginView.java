package demo.gyw.com.myapplication.mvp.View;

/**
 * Created by Administrator on 2016/1/27.
 */
public interface LoginView extends IBaseView {

    String getUserName();

    String getPassword();

    void showFailed();

    void navigateToHome();
}
