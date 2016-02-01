package demo.gyw.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.gitonway.lee.niftynotification.lib.Configuration;
import com.gitonway.lee.niftynotification.lib.Effects;
import com.gitonway.lee.niftynotification.lib.NiftyNotificationView;


public class NiftyActivity extends ActionBarActivity {

    private Effects effect;
    private NiftyNotificationView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nifty);
    }

    public void showNotify(View v) {

        String msg = "Today we’d like to share a couple of simple styles and effects for android notifications.";

        switch (v.getId()) {
            case R.id.scale:
                effect = Effects.scale;
                break;
            case R.id.thumbSlider:
                effect = Effects.thumbSlider;
                break;
            case R.id.jelly:
                effect = Effects.jelly;
                break;
            case R.id.slidein:
                effect = Effects.slideIn;
                break;
            case R.id.flip:
                effect = Effects.flip;
                break;
            case R.id.slideOnTop:
                effect = Effects.slideOnTop;
                break;
            case R.id.standard:
                effect = Effects.standard;
                break;

        }


//        NiftyNotificationView.build(this, msg, effect, R.id.mLyout)
//                .setIcon(R.drawable.lion)         //You must call this method if you use ThumbSlider effect
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        //add your code
//                        Toast.makeText(NiftyActivity.this, "Button clicked!", Toast.LENGTH_LONG).show();
//                    }
//                })
//                .show();


//        You can configure like this
//        The default

        Configuration cfg=new Configuration.Builder()
                .setAnimDuration(700)
                .setDispalyDuration(5000)
                .setBackgroundColor("#FFBDC3C7")
                .setTextColor("#FF444444")
                .setIconBackgroundColor("#FFFFFFFF")
                .setTextPadding(5)                      //dp
                .setViewHeight(48)                      //dp
                .setTextLines(2)                        //You had better use setViewHeight and setTextLines together
                .setTextGravity(Gravity.CENTER)         //only text def  Gravity.CENTER,contain icon Gravity.CENTER_VERTICAL
                .build();
        mView = NiftyNotificationView.build(this, msg, effect, R.id.mLyout, cfg)
                .setIcon(R.drawable.lion)               //remove this line ,only text
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //add your code
                        mView.hide();
                        Toast.makeText(NiftyActivity.this, "Button clicked!", Toast.LENGTH_LONG).show();
                    }
                });
        mView.show();                               //  show(boolean) allow duplicates   or showSticky() sticky notification,you can call removeSticky() method close it
    }
}
