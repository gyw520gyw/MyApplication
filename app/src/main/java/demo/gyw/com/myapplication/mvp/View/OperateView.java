package demo.gyw.com.myapplication.mvp.View;

import java.util.List;

/**
 * Created by Administrator on 2016/1/28.
 */
public interface OperateView extends IBaseView {

    void setAdapter(List<String> items);

    void showMessage(String message);

    void setListener();

}
