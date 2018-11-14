package demo.gyw.com.myapplication.mvp.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import demo.gyw.com.myapplication.R;
import demo.gyw.com.myapplication.mvp.View.OperateView;
import demo.gyw.com.myapplication.mvp.engine.OnItemClickListener;
import demo.gyw.com.myapplication.mvp.presenter.OperatePresenter;
import demo.gyw.com.myapplication.mvp.presenter.OperatePresenterImpl;

public class OperateActivity extends Activity implements OperateView, OnItemClickListener {

    @InjectView(R.id.rv_operate)
    RecyclerView mRecyclerView;

    @InjectView(R.id.pb_operate)
    ProgressBar mProgressBar;

    private OperatePresenter presenter = new OperatePresenterImpl(this, this);
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate);
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestory();
        super.onDestroy();

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void setAdapter(List<String> items) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(myAdapter = new MyAdapter(items));
    }

    @Override
    public void setListener() {
        myAdapter.setOnItemClickListener(this);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }



    @Override
    public void onItemClick(int position) {
        presenter.onItemClick(position);
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener {

        private List<String> mList;
        private LayoutInflater inflater;

        private OnItemClickListener listener;

        public MyAdapter(List<String> list) {
            this.mList = list;
            inflater = LayoutInflater.from(OperateActivity.this);
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_recycler_view, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            view.setOnClickListener(this);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mTv.setText(mList.get(position));
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        @Override
        public void onClick(View v) {
            if(listener != null) {
                listener.onItemClick((int)v.getTag());
            }
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        @InjectView(R.id.tv_recycler_view)
        TextView mTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
