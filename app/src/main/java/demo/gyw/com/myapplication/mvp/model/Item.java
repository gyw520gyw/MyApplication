package demo.gyw.com.myapplication.mvp.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/1/28.
 */
public class Item {

    public List<String> list;

    public List<String> getList() {
        return Arrays.asList(
                "Item 0",
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
                "Item 6",
                "Item 7",
                "Item 8",
                "Item 9",
                "Item 10"
        );
    }
}
