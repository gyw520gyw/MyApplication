package demo.gyw.com.myapplication.room.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

//@Entity用来注解一个实体类，对应数据库一张表
@Entity
public class User {
    //@PrimaryKey 主键
    @PrimaryKey
    public int uid;

    public String name;

    public String age;

    //存表和字段不一样
    @ColumnInfo(name = "addr")
    public String address;

    //忽略性别
    @Ignore
    public String sex;


    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}

