package demo.gyw.com.myapplication.fragment.hencode;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Toast;

import demo.gyw.com.myapplication.R;
import demo.gyw.com.myapplication.weiget.hencode.ThumbUpView;

public class PageFragment extends Fragment {
    @LayoutRes
    int sampleLayoutRes;
    @LayoutRes
    int practiceLayoutRes;

    ThumbUpView thumbUpView;
    boolean isAnimated = true;

    public static PageFragment newInstance(@LayoutRes int sampleLayoutRes, @LayoutRes int practiceLayoutRes) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        args.putInt("practiceLayoutRes", practiceLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        ViewStub sampleStub = (ViewStub) view.findViewById(R.id.sampleStub);
        sampleStub.setLayoutResource(sampleLayoutRes);
        sampleStub.inflate();


//        ViewStub practiceStub = (ViewStub) view.findViewById(R.id.practiceStub);
//        practiceStub.setLayoutResource(practiceLayoutRes);
//        practiceStub.inflate();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            sampleLayoutRes = args.getInt("sampleLayoutRes");
            practiceLayoutRes = args.getInt("practiceLayoutRes");
        }
    }

}
