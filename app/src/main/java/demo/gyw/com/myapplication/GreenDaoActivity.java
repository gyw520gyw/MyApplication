package demo.gyw.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import demo.gyw.com.myapplication.db.model.Person;
import demo.gyw.com.myapplication.greendao.PersonPresenter;

public class GreenDaoActivity extends AppCompatActivity {

    private PersonPresenter p;


    @InjectView(R.id.ll_greendao_container)
    LinearLayout mContainerLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.inject(this);

        p = new PersonPresenter(this);
    }

    @OnClick({R.id.btn_greendao_add, R.id.btn_greendao_update, R.id.btn_greendao_load})
    public void startClick(View v) {
        switch (v.getId()) {
            case R.id.btn_greendao_add:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                for(long i = 0; i < 10 ;i++) {
                    Person person = new Person(i, "gyw"+i, 23, 175.0, 80.0, 123456L+i);
                    p.add(person);
                }

                break;

            case R.id.btn_greendao_update:
                Toast.makeText(this, "修改", Toast.LENGTH_SHORT).show();
                Person person = new Person(0L, "gyw", 20, 100.0, 80.0, 10000L);
                p.update(person);
                break;

            case R.id.btn_greendao_load:

                List<Person> personList =  p.loadAll();
                for(Person p : personList) {
                    Log.d("gyw", p.toString());
                    TextView tv = new TextView(this);
                    tv.setText(person.toString());
                    tv.setTextColor(getColor(R.color.default_back_color));
                    mContainerLl.addView(tv);
                }

                Toast.makeText(this, "查询" + personList, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
