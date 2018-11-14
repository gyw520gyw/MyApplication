package demo.gyw.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Administrator on 2015/11/16.
 */
public class RecyclerViewActivity extends Activity {

    @InjectView(R.id.id_recycler_view)
    RecyclerView mRecyclerView;

    private ArrayList<String> strList = null;

    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.inject(this);

        initData();
        initView();
    }

    //ViewPager
//    FragmentPagerAdapter
    private void initData() {
        strList = new ArrayList<String>();
        for(int i = 'A' ; i <='Z'; i ++) {
            strList.add(""+(char)i);
        }
    }

    private void initView() {
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.HORIZONTAL));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());


    }

    public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder mViewHolder = new MyViewHolder(LayoutInflater.from(RecyclerViewActivity.this)
                    .inflate(R.layout.item_recycler_view, parent, false));
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mTv.setText(strList.get(position));
        }

        @Override
        public int getItemCount() {
            return strList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView mTv;

             public MyViewHolder(View itemView) {
                 super(itemView);
                 mTv = (TextView) itemView.findViewById(R.id.tv_recycler_view);
             }
         }

    }
}
