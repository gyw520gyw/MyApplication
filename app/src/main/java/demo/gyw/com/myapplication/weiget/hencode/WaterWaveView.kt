package demo.gyw.com.myapplication.weiget.hencode

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.support.v4.view.ViewConfigurationCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import demo.gyw.com.myapplication.ext.log
import kotlin.random.Random

class WaterWaveView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mTouchSlop = 0
    private var pointX = 0f
    private var pointY = 0f

    private var colorList = mutableListOf(Color.RED, Color.BLUE, Color.GREEN, Color.BLACK, Color.GRAY, Color.YELLOW)
    private var waveList = mutableListOf<Wave>()

    private var mHandler = Handler {

        if(it.what == 0) {

            //数据
            flushData()
            //刷新界面
            invalidate()

            log("isRunning  $isRunning")
            if(isRunning) {
                it.target.sendEmptyMessageDelayed(0, 50)
            }
         }

        false
    }

    private var isRunning = false

    private fun flushData() {

        var iterator = waveList.iterator()

        while (iterator.hasNext()) {

            var wave = iterator.next()

            if(wave.paint.alpha == 0) {
                iterator.remove()
                continue
            }

            wave.radius += 4

            wave.paint.alpha -= 8

            if(wave.paint.alpha < 8) {
                wave.paint.alpha = 0
            }

//            wave.paint.strokeWidth = wave.radius/5

        }

        if(waveList.isEmpty()) {
            isRunning = false
        }
    }


    init {
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(ViewConfiguration.get(context))
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var action = event.actionMasked
        when (action) {
            MotionEvent.ACTION_DOWN ,
            MotionEvent.ACTION_MOVE -> {
                pointX = event.x
                pointY = event.y

                addWave(pointX, pointY)
            }
        }
        return true
    }

    private fun addWave(pointX: Float, pointY: Float) {

        if(waveList.isEmpty()) { //首次

            addPoint2List(pointX, pointY)

            isRunning = true
            mHandler.sendEmptyMessage(0)
        } else {
            var wave = waveList[waveList.size -1]
            if(Math.abs(wave.pointX - pointX) > mTouchSlop || Math.abs(wave.pointY - pointY) > mTouchSlop) {
                addPoint2List(pointX, pointY)
            }
        }

    }

    private fun addPoint2List(pointX: Float, pointY: Float) {

        var newWave = Wave(pointX, pointY)

        newWave.paint.color = colorList[Random.nextInt(5)]
        waveList.add(newWave)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for(wave in waveList) {
            canvas.drawCircle(wave.pointX, wave.pointY, wave.radius, wave.paint)
        }
    }

    data class Wave(var pointX: Float,
                    var pointY: Float) {


        var radius: Float = 0f

        var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        mHandler.removeCallbacksAndMessages(0)
    }
}