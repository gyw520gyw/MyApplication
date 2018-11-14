package demo.gyw.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;

import java.nio.channels.Selector;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.gyw.com.myapplication.bean.User;


public class XutilsActivity extends Activity {


    private DbUtils db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xutils);
        ButterKnife.inject(this);

        db = DbUtils.create(this);
    }

    @OnClick(R.id.btn_save)
    void saveData() {
        User user = new User();

        user.setEmail("123456@qq.com");
        user.setUserName("gyw");
        try {
            db.save(user);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.btn_del)
    void delData() {
        User user = new User();
        user.setId(1);
        user.setEmail("123456@qq.com");
        user.setUserName("gyw");
        try {
            db.delete(user);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.btn_update)
    void updateData() {
        User user = new User();
        user.setId(3);
        user.setEmail("123456@qq.com");
        user.setUserName("guoyongwei");
        try {
            db.update(user);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_find)
    void findData() {
        List<User> userList = null;
        try {
            userList = db.findAll(User.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        System.out.println(userList.toString());
    }

}
