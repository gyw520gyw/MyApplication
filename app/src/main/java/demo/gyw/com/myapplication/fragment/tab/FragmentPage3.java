package demo.gyw.com.myapplication.fragment.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentPage3 extends BaseFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		TextView tv = new TextView(getActivity());
		tv.setText("好友");

		return tv;
	}	
}