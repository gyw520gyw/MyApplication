package demo.gyw.com.myapplication.ui.progress;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.glomadrian.dashedcircularprogress.DashedCircularProgress;

import demo.gyw.com.myapplication.R;
import demo.gyw.com.myapplication.utils.ExitAppUtil;

public class SimpleProgressActivity extends ActionBarActivity {

    private Button restartButton;
    private DashedCircularProgress dashedCircularProgress;
    private TextView numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_progress);

        ExitAppUtil.getInstance().addActivity(this);

        restartButton = (Button) findViewById(R.id.restart);
        dashedCircularProgress = (DashedCircularProgress) findViewById(R.id.simple);
        numbers = (TextView) findViewById(R.id.number);

        dashedCircularProgress.setMax(8000f);

        animate();

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });

        dashedCircularProgress.setOnValueChangeListener(
                new DashedCircularProgress.OnValueChangeListener() {
                    @Override
                    public void onValueChange(float value) {
                        numbers.setText((int) value + "");
                    }
                });
    }


    private void restart() {
        dashedCircularProgress.reset();
        animate();
    }

    private void animate() {
        dashedCircularProgress.setValue(1234);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExitAppUtil.getInstance().delActivity(this);
    }
}
