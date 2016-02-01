package demo.gyw.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import demo.gyw.com.myapplication.weiget.staggeredGridView.StaggeredGridView;

public class StaggeredGridViewActivity extends AppCompatActivity {

    @InjectView(R.id.staggered_grid_view)
    StaggeredGridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_view);
        ButterKnife.inject(this);


    }

}
