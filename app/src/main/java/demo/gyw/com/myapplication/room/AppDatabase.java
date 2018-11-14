package demo.gyw.com.myapplication.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


import demo.gyw.com.myapplication.room.dao.UserDao;
import demo.gyw.com.myapplication.room.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

