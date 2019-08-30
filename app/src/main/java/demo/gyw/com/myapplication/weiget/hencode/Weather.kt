package demo.gyw.com.myapplication.weiget.hencode

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import demo.gyw.com.myapplication.ext.dp2px
import demo.gyw.com.myapplication.ext.log
import java.util.*

/**
 * 2019/8/28 17:41
 * gyw 实体类 模拟雪花 雨点
 */
class Weather {

    enum class Type{
        SNOW, RAIN;
    }

    private var minX = 0f
    private var minY = 0f
    private var maxX = 0f
    private var maxY = 0f

    private var random = Random()

    private var presentX = 0f
    private var presentY = 0f
    private var presentAngle = getAngle()
    private var presentSpeed = getSpeed()
    private var presentRadius = getRadius()

    private var speedX = 0f
    private var speedY = 0f


    private var type: Type = Type.RAIN

    private var snowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#e6e8db")
        alpha = 180
    }

    private var rainPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#e6e8db")
        alpha = 180
        strokeWidth = random.nextInt(2) + 3f
    }



    fun setScope(minWidth: Float, width: Float, minHeight: Float, height: Float): Weather {
        this.minX = minWidth
        this.minY = minHeight
        this.maxX = width
        this.maxY = height
//log(" minX  $minX   minY $minY   maxX  $maxX   maxY  $maxY ")
        return this
    }

    fun setScope(width: Float, height: Float): Weather {
        maxX = width
        maxY = height

        return this
    }


    fun init(type: Type): Weather {

        this.type = type

        reset()

        return this
    }


    fun draw(canvas: Canvas) {
        moveX()
        moveY()



        if(type == Type.SNOW) {
            if(presentX == minX || presentY == minY || presentX > maxX || presentY > maxY*3/4) {
                reset()
            }
            canvas.drawCircle(presentX, presentY, presentRadius, snowPaint)
        } else {

            if(presentX == minX || presentY == minY || presentX > maxX || presentY > maxY) {
                reset()
            }

            canvas.drawLine(presentX, presentY,
                    presentX + (10 * Math.sin(Math.PI*presentAngle/180.0)).toFloat(),
                    presentY - (10 * Math.cos(Math.PI*presentAngle/180.0)).toFloat(), rainPaint)
        }


    }

    private fun getSpeed(): Float {

        var speed = 0f

        if(type == Type.SNOW) {
            speed = random.nextFloat() + 2
        } else if(type == Type.RAIN) {
            speed = 4f
        }
        return speed
    }

    private fun getAngle(): Float {

        var angle = 0f

        if(type == Type.SNOW) {
            angle = random.nextInt(29) + 1.toFloat()
        } else if(type == Type.RAIN) {
            angle =  random.nextInt(10) + 20.toFloat()
        }

        return angle
    }


    private fun getRadius(): Float {
        return (random.nextInt(6) + 3).toFloat()
    }


    private fun reset() {

        presentSpeed = getSpeed()
        presentAngle = getAngle()
        presentRadius = getRadius()


        if(type == Type.SNOW) {
            presentX = random.nextInt(maxX.toInt()).toFloat()
            presentY = random.nextInt((maxY/2).toInt()).toFloat()
        } else if(type == Type.RAIN) {
            presentX = random.nextInt((maxX - minX).toInt()) + minX
            presentY = random.nextInt((maxY - minY).toInt()) + minY
        }

        speedX = getSpeedX()
        speedY = getSpeedY()
     }

    private fun moveY() {
        presentY += speedY
    }

    private fun moveX() {
         if(type == Type.SNOW) {
             presentX += speedX
        } else if(type == Type.RAIN)  {
             presentX -= speedX
        }

    }

    // 雪花x轴的速度
    private fun getSpeedX(): Float {
        return (presentSpeed * Math.sin(Math.PI*presentAngle/180.0)).toFloat()
    }

    private fun getSpeedY(): Float {
        return (presentSpeed * Math.cos(Math.PI*presentAngle/180.0)).toFloat()
    }
}