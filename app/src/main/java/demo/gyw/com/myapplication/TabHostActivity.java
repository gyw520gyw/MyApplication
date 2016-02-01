package demo.gyw.com.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import demo.gyw.com.myapplication.fragment.tab.FragmentPage1;
import demo.gyw.com.myapplication.fragment.tab.FragmentPage2;
import demo.gyw.com.myapplication.fragment.tab.FragmentPage3;


public class TabHostActivity extends FragmentActivity implements TabHost.OnTabChangeListener {

    private FragmentTabHost mTabHost;
    private TextView mTv;

    private Class fragmentArray[] = {FragmentPage1.class,FragmentPage2.class,FragmentPage3.class};

//    private int mImageViewArray[] = {R.drawable.tab_home_btn,R.drawable.tab_message_btn,R.drawable.tab_selfinfo_btn,
//            R.drawable.tab_square_btn,R.drawable.tab_more_btn};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "消息", "好友"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            System.out.println("gyw   savedInstanceState is not null");
            savedInstanceState.remove("android:support:fragments");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);

        System.out.println("gyw TabHostActivity  onCreate");

        initView();

//        mTabHost.setCurrentTab(0);

        mTabHost.setOnTabChangedListener(this);


        FragmentManager manager = getSupportFragmentManager();

        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }


    }

    private void initView() {
        mTv = (TextView)findViewById(R.id.tv_background);
        mTabHost  = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        int count = fragmentArray.length;

        for(int i = 0; i < count; i++){
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.main_rb_bg_selector);

        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){
        View view = View.inflate(this,R.layout.tab_item_view, null);

//        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
//        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        if ("首页".equals(tabId)) {
            mTv.setVisibility(View.GONE);
        } else if("消息".equals(tabId)) {
            mTv.setVisibility(View.VISIBLE);
        } else if("好友".equals(tabId)) {
            mTv.setVisibility(View.VISIBLE);
        }
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mTabHost.getCurrentTab() != 0) {
                mTabHost.setCurrentTab(0);
            } else {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();

                } else {
                    finish();
                    // System.exit(0);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("gyw TabHostActivity  onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("gyw TabHostActivity  onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("gyw TabHostActivity  onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("gyw TabHostActivity  onStop");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("gyw TabHostActivity  onDestroy");
    }
}
