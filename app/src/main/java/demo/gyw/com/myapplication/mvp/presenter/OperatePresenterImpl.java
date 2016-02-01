package demo.gyw.com.myapplication.mvp.presenter;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import demo.gyw.com.myapplication.mvp.View.IBaseView;
import demo.gyw.com.myapplication.mvp.View.OperateView;
import demo.gyw.com.myapplication.mvp.engine.OnOperateListener;
import demo.gyw.com.myapplication.mvp.engine.Operate;
import demo.gyw.com.myapplication.mvp.engine.OperateImpl;

/**
 * Created by Administrator on 2016/1/28.
 */
public class OperatePresenterImpl implements OperatePresenter, OnOperateListener {

    private Context mContext;
    private OperateView mOperateView;
    private Operate operateImpl;

    public OperatePresenterImpl(Context context, OperateView operateView) {
        this.mContext = context;
        this.mOperateView = operateView;
        this.operateImpl = new OperateImpl();
    }

    @Override
    public void onResume() {
        if(mOperateView != null){
            mOperateView.showProgress();
        }

        operateImpl.findItem(this);
    }

    @Override
    public void onDestory() {
        mOperateView = null;
    }

    @Override
    public void onItemClick(int position) {
        if(mOperateView != null) {
            mOperateView.showMessage("点击了 " + position);
        }
    }


    @Override
    public void onFinished(List<String> items) {
        if(mOperateView != null) {
            mOperateView.hideProgress();
            mOperateView.setAdapter(items);
            mOperateView.setListener();
        }
    }
}
