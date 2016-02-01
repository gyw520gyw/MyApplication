package demo.gyw.com.myapplication.mvp.engine;

import android.os.Handler;

import java.util.List;

import demo.gyw.com.myapplication.mvp.model.Item;

/**
 * Created by Administrator on 2016/1/28.
 */
public class OperateImpl implements Operate {

    @Override
    public void findItem(final OnOperateListener lister) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lister.onFinished(getItemList());
            }
        }, 2000);
    }


    private List<String> getItemList() {

        Item item = new Item();

        return item.getList();

    }
}
