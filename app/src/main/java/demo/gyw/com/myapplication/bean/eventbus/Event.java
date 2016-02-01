package demo.gyw.com.myapplication.bean.eventbus;

import java.util.List;

/**
 * Created by Administrator on 2015/11/5.
 */
public class Event {
    /** 列表加载事件 */
    public static class ItemListEvent
    {
        private List<Item> items;

        public ItemListEvent(List<Item> items)
        {
            this.items = items;
        }

        public List<Item> getItems()
        {
            return items;
        }
    }

}
