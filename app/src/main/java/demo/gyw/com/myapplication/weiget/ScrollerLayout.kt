package demo.gyw.com.myapplication.weiget

import android.content.Context
import android.support.v4.view.ViewConfigurationCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.Scroller

/**
 * 2019/9/3 14:30
 * gyw ScrollerLayout 自定义ViewGroup
 */
class ScrollerLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private var mTouchSlop = 0
    private var mScroller = Scroller(context)

    private var leftBroder = 0
    private var rightBorder = 0

    private var downX = 0f

    init {
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val count = childCount
        for (i in 0..(count-1)) {
            var childView = getChildAt(i)
            measureChild(childView, widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount
        var width = 0
        for(i in 0..(count-1)) {
            var childView= getChildAt(i)
            childView.layout(width, 0, width + childView.measuredWidth, childView.measuredHeight)
            width += childView.measuredWidth
        }
        leftBroder = getChildAt(0).left
        rightBorder = getChildAt(count-1).right

//        log(" leftBroder  $leftBroder  rightBorder $rightBorder")
    }



    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        var action = ev.actionMasked
        when(action) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.rawX
            }
            MotionEvent.ACTION_MOVE -> {
                var moveX = ev.rawX
                var absX = Math.abs(moveX - downX)

                //当拖动的值大于固定值 拦截 让自己处理
                if(absX > mTouchSlop) {
                    return true
                }
            }
        }

        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        var action = event.actionMasked
        when(action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.rawX
            }
            MotionEvent.ACTION_MOVE -> {
                var moveX = event.rawX
                // 反着写
                var diffX = downX - moveX

                //判断给定当前的增量移动后, 是否能够超出边界
                when {
                    diffX + scrollX < leftBroder -> scrollTo(leftBroder, 0)
                    diffX + scrollX + width > rightBorder -> scrollTo(rightBorder-width, 0)
                    else -> scrollBy(diffX.toInt(), 0)
                }

                downX = moveX
            }

            MotionEvent.ACTION_UP -> {
                var targetIndex = (scrollX + width/2)/width
                var dx = targetIndex * width - scrollX
//log("scrollX  $scrollX    targetIndex $targetIndex   dx  $dx")
                mScroller.startScroll(scrollX, 0, dx, 0)
                invalidate()

            }
        }

        return true
    }


    override fun computeScroll() {

        if(mScroller.computeScrollOffset()) {
            var currX = mScroller.currX
            scrollTo(currX, 0)
            invalidate()
        }

        super.computeScroll()
    }

}