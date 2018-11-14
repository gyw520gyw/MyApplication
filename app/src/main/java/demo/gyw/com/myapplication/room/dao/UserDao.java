package demo.gyw.com.myapplication.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import demo.gyw.com.myapplication.room.model.User;


@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE name LIKE :name")
    User findByName(String name);

    @Insert
    void insertAll(User... users);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user WHERE uid = (:uid)")
    void deleteById(String uid);


    @Update
    void update(User user);


    @Query("UPDATE user SET name = (:str) WHERE uid = (:i)")
    void updateById(int i, String str);
}
