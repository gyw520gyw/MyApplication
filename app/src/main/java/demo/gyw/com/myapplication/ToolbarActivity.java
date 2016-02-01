package demo.gyw.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import demo.gyw.com.myapplication.helper.Cheeses;

public class ToolbarActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar_recycler_view)
    RecyclerView mRecylerView;

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);

        ButterKnife.inject(this);


        initView();
//        TextView

    }

    private void initView() {

        setSupportActionBar(mToolbar);

        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mRecylerView.setAdapter(new ToolbarAdapter());
    }


    public class ToolbarAdapter extends RecyclerView.Adapter<ToolbarAdapter.MyViewHolder> {


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder mViewHolder = new MyViewHolder(LayoutInflater.from(ToolbarActivity.this)
                    .inflate(R.layout.item_recycler_view2, parent, false));
            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mTv.setText(Cheeses.sCheeseStrings[position]);
        }

        @Override
        public int getItemCount() {
            return Cheeses.sCheeseStrings.length;
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
