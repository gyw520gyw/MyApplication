package demo.gyw.com.myapplication.fragment.eventbus;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import demo.gyw.com.myapplication.R;
import demo.gyw.com.myapplication.bean.eventbus.Item;

public class ItemDetailFragment extends Fragment {

    @InjectView(R.id.tv)
     TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        ButterKnife.inject(this,view);
//        tv = (TextView) view.findViewById(R.id.tv);

        return view;
    }

    public void onEventMainThread(Item item) {
        if(item != null) {
            tv.setText(item.content);
        }
    }
}

















