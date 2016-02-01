package demo.gyw.com.myapplication.ui.progress;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.github.glomadrian.dashedcircularprogress.DashedCircularProgress;

import demo.gyw.com.myapplication.R;

public class SizeProgressActivity extends ActionBarActivity {

    private static final String TAG = "SizeProgressActivity";
    private Button restartButton;
    private DashedCircularProgress dashedCircularProgress;
    private ImageView androidImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_progress);

        dashedCircularProgress = (DashedCircularProgress) findViewById(R.id.size);
        androidImage = (ImageView) findViewById(R.id.android_image);
        restartButton = (Button) findViewById(R.id.restart);

        dashedCircularProgress.setInterpolator(new AccelerateInterpolator());
        dashedCircularProgress.setValue(5);

        dashedCircularProgress.setOnValueChangeListener(new DashedCircularProgress.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                Log.d(TAG, value + "");
                androidImage.setScaleX(dashedCircularProgress.getScaleX() + value / 3);
                androidImage.setScaleY(dashedCircularProgress.getScaleX() + value / 3);
//                if(((int)value % 2) == 0) {
//                    androidImage.setImageResource(R.drawable.star);
//                } else {
//                    androidImage.setImageResource(R.drawable.vader_icon);
//                }
            }
        });


        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animate();
            }
        });
    }
    private void animate() {
        dashedCircularProgress.reset();
        dashedCircularProgress.setValue(5);
    }

}
