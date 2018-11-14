package demo.gyw.com.myapplication;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.InjectView;
import demo.gyw.com.myapplication.weiget.staggeredGridView.StaggeredGridView;

public class StaggeredGridViewActivity extends Activity {

    @InjectView(R.id.staggered_grid_view)
    StaggeredGridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_view);
        ButterKnife.inject(this);


    }

}
