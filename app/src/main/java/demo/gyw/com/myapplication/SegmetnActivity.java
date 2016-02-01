package demo.gyw.com.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sevenheaven.segmentcontrol.SegmentControl;


public class SegmetnActivity extends ActionBarActivity {

    SegmentControl mSegmentControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segmetn);

        mSegmentControl = (SegmentControl) findViewById(R.id.segment_control);

        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                Toast.makeText(SegmetnActivity.this, "index : " + index, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
