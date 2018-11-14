package demo.gyw.com.myapplication.bean;

import com.lidroid.xutils.db.annotation.Id;

/**
 * Created by Administrator on 2015/8/14.
 */

public class User {
    @Id
    private int id;
    private String userName;
    private String email;


    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
