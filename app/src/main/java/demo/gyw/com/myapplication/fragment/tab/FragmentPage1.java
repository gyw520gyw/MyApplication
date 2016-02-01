package demo.gyw.com.myapplication.fragment.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentPage1 extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("gyw 首页  onCreateView");

        TextView tv = new TextView(getActivity());
        tv.setText("首页");

        return tv;
    }


    @Override
    public void onStart() {
        super.onStart();
        System.out.println("gyw 首页  onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("gyw 首页  onResume");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("gyw 首页  onDestroy");
    }
}
