package demo.gyw.com.myapplication;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.gyw.com.myapplication.databind.User;

public class DataBindActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_data_bind);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind);
        User user = new User("Guo", "yongw");
        binding.setVariable(1, user);
    }
}
