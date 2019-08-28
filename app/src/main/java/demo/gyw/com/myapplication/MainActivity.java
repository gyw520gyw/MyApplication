package demo.gyw.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RemoteViews;

import butterknife.ButterKnife;
import butterknife.InjectViews;
import butterknife.OnClick;
import demo.gyw.com.myapplication.utils.ExitAppUtil;
import demo.gyw.com.myapplication.weiget.LabViewActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        ExitAppUtil.getInstance().addActivity(this);

    }


    /*btn_hencode*/
    @OnClick(R.id.btn_hencode)
    void openHencodeActivity() {
        startActivity(HencodeActivity.class);
    }


    /*btn_weex*/
    @OnClick(R.id.btn_weex)
    void openWeexActivity() {
        startActivity(CommonWeexActivity.class);
    }


    /*btn_kotlinx*/
    @OnClick(R.id.btn_kotlinx)
    void openKotlinxActivity() {
        startActivity(KotlinxCoroutineActivity.class);
    }

    /*btn_greendao*/
    @OnClick(R.id.btn_room)
    void openRoomActivity() {
        startActivity(RoomActivity.class);
    }



    /*btn_greendao*/
    @OnClick(R.id.btn_greendao)
    void openGreenDaoActivity() {
        startActivity(GreenDaoActivity.class);
    }

    /*btn_shader*/
    @OnClick(R.id.btn_shader)
    void openShaderTestActivity() {
        startActivity(ShaderTestActivity.class);
    }



    /*btn_databind*/
    @OnClick(R.id.btn_databind)
    void openDataBindActivity() {
        startActivity(DataBindActivity.class);
    }

    /*btn_mvp*/
    @OnClick(R.id.btn_mvp)
    void openMvpActivity() {
        startActivity(MvpActivity.class);
    }


    /*btn_toolbar*/
//    @OnClick(R.id.btn_toolbar)
//    void openToolbarActivity() {
//        startActivity(ToolbarActivity.class);
//    }

   /* btn_staggeredgrid*/
   @OnClick(R.id.btn_staggeredgrid)
   void openStaggeredGridActivity() {
       startActivity(StaggeredGridViewActivity.class);
   }

    /*btn_retrofit*/
    @OnClick(R.id.btn_retrofit)
    void openRetrofitActivity() {
        startActivity(RetrofitActivity.class);
    }


    /*btn_recyclerview*/
    @OnClick(R.id.btn_recyclerview)
    void openRecyclerViewActivity() {
        startActivity(RecyclerViewActivity.class);
    }

    /*okhttp*/
    @OnClick(R.id.btn_okhttp)
    void openOkHttpActivity() {
        startActivity(OkHttpActivity.class);
    }

    /*eventbus*/
    @OnClick(R.id.btn_eventbus)
    void openEventBusActivity() {
        startActivity(EventBusActivity.class);
    }

    /**rxjava*/
    @OnClick(R.id.btn_rxjava)
    void openRxJavaActivity() {
        startActivity(RxJavaActivity.class);
    }

    /*TabStrip*/
    @OnClick(R.id.btn_tab_strip)
    void openTabStripActivity() {
        startActivity(TabStripActivity.class);
    }

     /*nifty*/
    @OnClick(R.id.btn_reactive_network)
    void openReactiveNetworkActivity() {
        startActivity(ReactiveNetworkActivity.class);
    }

     /*nifty*/
    @OnClick(R.id.btn_nifty)
    void openNiftyActivity() {
        startActivity(NiftyActivity.class);
    }


    /*snackbar*/
    @OnClick(R.id.btn_snack_bar)
    void openSnackBarActivity() {
        startActivity(SnackBarActivity.class);
    }


    /*进度条*/
    @OnClick(R.id.btn_xutils_db)
    void openXutilsActivity() {
        startActivity(XutilsActivity.class);
    }



    /*分段*/
    @OnClick(R.id.btn_segment)
    void openSegmetnActivity() {
        startActivity(SegmetnActivity.class);
    }


    /*进度条*/
    @OnClick(R.id.btn_progress)
    void openProgreassActivity() {
        startActivity(ProgressActivity.class);
    }

    /*标签云布局*/
    @OnClick(R.id.btn_tagview)
    void openTagViewActivity() {
        startActivity(TagViewActivity.class);
    }


    /*百分比布局*/
    @OnClick(R.id.btn_percent)
    void openPercentActivity() {
        startActivity(PercentActivity.class);
    }


    /*百分比布局*/
    @OnClick(R.id.btn_fitchart)
    void openFitChartActivity() {
        startActivity(FitChartActivity.class);
    }


    /*斜标签*/
    @OnClick(R.id.btn_labview)
    void openLabViewActivity() {
        startActivity(LabViewActivity.class);
    }

    /*TabHost*/
    @OnClick(R.id.btn_tabhost)
    void openTabHostActivity() {
        startActivity(TabHostActivity.class);
    }





    /** 通过Class跳转界面 **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /** 含有Bundle通过Class跳转界面 **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExitAppUtil.getInstance().delActivity(this);
    }
}
