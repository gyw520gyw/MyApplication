package demo.gyw.com.myapplication.weiget.hencode

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import demo.gyw.com.myapplication.ext.log
import java.util.*

/**
 * 2019/8/26 17:29
 * gyw 雪片类
 */
class Snowflake {

    private var scopeX = 0
    private var scopeY = 0

    private var random = Random()

    private var presentX = 0f
    private var presentY = 0f
    private var presentAngle = getAngle()
    private var presentSpeed = getSpeed()
    private var presentRadius = getRadius()

    private var speedX = 0f
    private var speedY = 0f


    private var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#e6e8db")
        alpha = 180
    }


    // 雪花的范围
    fun setScope(width: Int, height: Int): Snowflake {
        scopeX = width
        scopeY = height
//log("scopeX $scopeX  scopeY $scopeY")
        return this
    }


    fun init(): Snowflake {

        reset()

        return this
    }


    fun draw(canvas: Canvas) {
        moveX()
        moveY()

        if(presentX == 0f || presentY == 0f || presentX > scopeX || presentY > scopeY*3/4) {
            reset()
        }
        canvas.drawCircle(presentX, presentY, presentRadius, paint)

    }

    private fun getSpeed(): Float {
        return random.nextFloat() + 1
    }

    private fun getAngle(): Float {
        return random.nextInt(31).toFloat()
    }


    private fun getRadius(): Float {
        return (random.nextInt(15) + 3).toFloat()
    }


    private fun reset() {

        presentSpeed = getSpeed()
        presentAngle = getAngle()
        presentRadius = getRadius()

        presentX = random.nextInt(scopeX).toFloat()
        presentY = random.nextInt(scopeY/2).toFloat()

        speedX = getSpeedX()
        speedY = getSpeedY()
     }

    private fun moveY() {
        presentY += speedY
    }

    private fun moveX() {
        presentX += speedX
    }

    // 雪花x轴的速度
    private fun getSpeedX(): Float {
        return (presentSpeed * Math.sin(Math.PI*presentAngle/180.0)).toFloat()
    }

    private fun getSpeedY(): Float {
        return (presentSpeed * Math.cos(Math.PI*presentAngle/180.0)).toFloat()
    }
}