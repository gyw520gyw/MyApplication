package demo.gyw.com.myapplication;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.List;

import demo.gyw.com.myapplication.db.model.Person;
import demo.gyw.com.myapplication.greendao.PersonPresenter;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {


    public ApplicationTest() {
        super(Application.class);
    }

}