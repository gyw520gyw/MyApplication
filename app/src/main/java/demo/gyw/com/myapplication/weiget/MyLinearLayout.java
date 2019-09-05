package demo.gyw.com.myapplication.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {


	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return true;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		int width=getWidth()/getChildCount();
		int height = getHeight();
		int count=getChildCount();
		
		float eventX = event.getX();
		
		if (eventX<width){ // 滑动左边的 listView
			event.setLocation(width/2, event.getY());
			getChildAt(0).dispatchTouchEvent(event);
			return true;
			
		} else if (eventX > width && eventX < 2 * width) { //滑动中间的 listView
			float eventY = event.getY();
			if (eventY < height / 2) {
				event.setLocation(width / 2, event.getY());
				for (int i = 0; i < count; i++) {
					View child = getChildAt(i);
					try {
						child.dispatchTouchEvent(event);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				return true;
			} else if (eventY > height / 2) {
				event.setLocation(width / 2, event.getY());
				try {
					getChildAt(1).dispatchTouchEvent(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			}
		}else if (eventX>2*width){
			event.setLocation(width/2, event.getY());
			getChildAt(2).dispatchTouchEvent(event);
			return true;
		}
		
		return true;
	}
	
}
