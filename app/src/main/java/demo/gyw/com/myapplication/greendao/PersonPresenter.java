package demo.gyw.com.myapplication.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import demo.gyw.com.myapplication.db.dao.DaoMaster;
import demo.gyw.com.myapplication.db.dao.DaoSession;
import demo.gyw.com.myapplication.db.model.Person;

/**
 * author: gyw
 * date: 2016/5/20.
 */
public class PersonPresenter {

    public static final String dbName = "myperson.db";
    DaoSession daoSession;
    DaoMaster daoMaster;
    DaoMaster.DevOpenHelper helper;

    public PersonPresenter(Context context) {
        helper = new DaoMaster.DevOpenHelper(context, dbName, null);//创建数据库
        daoMaster = new DaoMaster(helper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public long add(Person person) {
        try{
            return daoSession.insertOrReplace(person);
        } catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public void update(Person person) {
        try{
            daoSession.update(person);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public List<Person> loadAll() {
        try{
            return daoSession.getPersonDao().loadAll();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Person person) {
        try{
            daoSession.delete(person);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close() {
        daoSession.clear();
        daoSession = null;
        helper.close();
        helper = null;
    }
}
