package demo.gyw.com.myapplication;

import android.test.InstrumentationTestCase;

import java.util.List;

import demo.gyw.com.myapplication.db.model.Person;
import demo.gyw.com.myapplication.greendao.PersonPresenter;

/**
 * author: gyw
 * date: 2016/5/20.
 */
public class TestGreenDao extends ApplicationTest {

    PersonPresenter p = new PersonPresenter(getContext());

    //Long id, String name, Integer age, Double height, Double weight, Long cardId
    public void testAddPerson() {
        Person person = new Person(1L, "gyw", 23, 175.0, 80.0, 123456L);
        p.add(person);
    }

    public void testLoadAll() {
        List<Person> personList =  p.loadAll();
        System.out.println("gyw : " + personList.get(0));
    }

    public void test() throws Exception {
        final int expected = 1;
        final int reality = 1;
        assertEquals(expected, reality);
    }

}
