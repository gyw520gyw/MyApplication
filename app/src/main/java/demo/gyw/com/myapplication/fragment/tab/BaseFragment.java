package demo.gyw.com.myapplication.fragment.tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment {

    private final String TAG = BaseFragment.class.getSimpleName();

    public static final int ERROR_RESPONSE = 0x99;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        setMenuVisibility(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // onAttach(getActivity());
        afterActivityCreated();
        super.onActivityCreated(savedInstanceState);
        onFragmentShow();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println(TAG + "    requestCode:" + requestCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onFragmentShow() {
        System.out.println(TAG + "1   onFragmentShow: " + this.getClass().getName());
    }

    public void onFragmentHide() {
        System.out.println(TAG + "    onFragmentHide: " + this.getClass().getName());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        System.out.println(TAG + "    onHiddenChanged: " + this.getClass().getName());

        if (!hidden) {
            onFragmentShow();
        } else {
            onFragmentHide();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        System.out.println(TAG + "    onResume: " + this.getClass().getName());
        super.onResume();
        // onFragmentResume();
    }

    protected void afterActivityCreated() {
    }

    @Override
    public void onPause() {
        System.out.println(TAG + "    onPause: " + this.getClass().getName());
        super.onPause();
        // onFragmentPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
