package demo.gyw.com.myapplication.fragment.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentPage2 extends BaseFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		System.out.println("gyw 消息  onCreateView");

		TextView tv = new TextView(getActivity());
		tv.setText("消息");

		return tv;
	}


	@Override
	public void onStart() {
		super.onStart();
		System.out.println("gyw 消息  onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		System.out.println("gyw 消息  onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		System.out.println("gyw 消息  onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		System.out.println("gyw 消息  onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		System.out.println("gyw 消息  onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("gyw 消息  onDestroy");
	}
}