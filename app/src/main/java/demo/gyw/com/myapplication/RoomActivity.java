package demo.gyw.com.myapplication;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import demo.gyw.com.myapplication.room.AppDatabase;
import demo.gyw.com.myapplication.room.dao.UserDao;
import demo.gyw.com.myapplication.room.model.User;

public class RoomActivity extends AppCompatActivity {


    private UserDao mUserDao;
    private AppDatabase mDb;

    @InjectView(R.id.ll_container)
    LinearLayout mContainerLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        ButterKnife.inject(this);
        initDB();
    }

    private void initDB() {

        mDb = Room.inMemoryDatabaseBuilder(this, AppDatabase.class).build();
//        mDb = Room.databaseBuilder(this, AppDatabase.class, "TestDb").build();
        mUserDao = mDb.userDao();


    }

    @OnClick({R.id.btn_add, R.id.btn_update, R.id.btn_del, R.id.btn_find})
    void opetare(View view) {
        switch (view.getId()) {
            case R.id.btn_add:

                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        for(int i = 0; i < 10 ; i++) {
                            User user = new User();
                            user.uid = i;
                            user.age = String.valueOf(10+i);
                            user.name = "goyw"+i;
                            user.address = "知音广场" + i;
                            Log.d("goyw", "--- " + i);
                            mUserDao.insert(user);
                        }

                    }
                }.start();


                break;
            case R.id.btn_update:

                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        User user = new User();
                        user.uid = 1;
                        user.name = "xxxxxxxxxx";
                        mUserDao.update(user);

                        mUserDao.updateById(2, "uuuuuuuuuuuuuuuuuuuu");



                    }
                }.start();


                break;
            case R.id.btn_del:

                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        User user = new User();
                        user.uid = 0;
                        mUserDao.delete(user);

                        mUserDao.deleteById("5");

                    }
                }.start();

                break;
            case R.id.btn_find:

                mContainerLl.removeAllViews();
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        final List<User> userList = mUserDao.getAll();
                        Log.d("goyw", userList.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (User u: userList) {
                                    TextView tv = new TextView(RoomActivity.this);
                                    tv.setText(u.toString());
                                    tv.setTextColor(getResources().getColor(android.R.color.black));

                                    mContainerLl.addView(tv);
                                }
                            }
                        });
                    }
                }.start();





                break;
        }
    }
}
