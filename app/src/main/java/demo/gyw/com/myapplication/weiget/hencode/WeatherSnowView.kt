package demo.gyw.com.myapplication.weiget.hencode

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.View
import demo.gyw.com.myapplication.R
import demo.gyw.com.myapplication.ext.dp2px
import demo.gyw.com.myapplication.ext.px2dp

/**
 * 2019/8/26 17:28
 * gyw 天气效果 雪
 */
class WeatherSnowView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {



    private var centerX: Int = 0
    private var centerY: Int = 0

    private var outRadius = 80f.dp2px(context)
    private var innerRadius = 70f.dp2px(context)
    private var snowBodyRadius = 25f.dp2px(context)
    private var snowHeadRadius = 12f.dp2px(context)

    private var outCirclePaint = Paint(ANTI_ALIAS_FLAG).apply {
        this.color = Color.parseColor("#e6e8db")
        this.maskFilter = BlurMaskFilter(outRadius, BlurMaskFilter.Blur.SOLID)
    }

    private  var innerCirclePaint: Paint =  Paint(ANTI_ALIAS_FLAG)

    private var snowArmPaint: Paint = Paint(ANTI_ALIAS_FLAG).apply {
        this.color = Color.parseColor("#88000000")
        this.style = Paint.Style.STROKE
        this.strokeWidth = 6f.dp2px(context)
    }

    private var snowArmRectF = RectF()

    private var snowBodyPaint: Paint = Paint(ANTI_ALIAS_FLAG).apply {
        this.color = Color.parseColor("#e6e8db")
    }

    private var snowList = mutableListOf<Weather>()


    init {

        var ta = context.obtainStyledAttributes(attrs, R.styleable.WeatherView)

        outRadius = ta.getDimension(R.styleable.WeatherView_outRadius, 80f)

        ta.recycle()

        var outRadiusDp = outRadius.px2dp(context)
        var innerRadiusDp = outRadiusDp - outRadiusDp/10

        innerRadius = innerRadiusDp.dp2px(context)
        snowBodyRadius = (innerRadiusDp / 2 * 2 / 3 + 0.5 + 2).toInt().toFloat().dp2px(context)
        snowHeadRadius = (innerRadiusDp / 2 * 1 / 3 + 0.5).toInt().toFloat().dp2px(context)

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        centerX = measuredWidth / 2
        centerY = measuredHeight / 2


        if(snowList.isEmpty()) {
            for(i in 0..50) {
                var snow = Weather().setScope(measuredWidth.toFloat(), measuredHeight.toFloat()).init(Weather.Type.SNOW)
                snowList.add(snow)
            }
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //设置背景
        canvas.drawColor(Color.BLACK)

        //外圈的圆
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), outRadius, outCirclePaint)


        //内圆
        innerCirclePaint?.let {
            it.shader = LinearGradient(centerX - innerRadius, centerY + innerRadius, centerX + innerRadius, centerY - innerRadius,
                    Color.parseColor("#e0e2e5"), Color.parseColor("#758595"), Shader.TileMode.CLAMP)
        }

        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), innerRadius, innerCirclePaint)


        // 雪人的手臂
        snowArmRectF?.let {
            it.set(centerX - innerRadius + snowBodyRadius , centerY - innerRadius +  snowBodyRadius,
                    centerX + innerRadius - snowBodyRadius, centerY + innerRadius - snowBodyRadius)
        }
        canvas.drawArc(snowArmRectF, 45f, 100f, false, snowArmPaint)

        //雪人的身体
        canvas.drawCircle(centerX.toFloat() - 4f.dp2px(context), centerY + snowHeadRadius * 2 + snowBodyRadius - 4f.dp2px(context), snowBodyRadius, snowBodyPaint)

        // 雪人的头
        canvas.drawCircle(centerX.toFloat(), centerY + snowHeadRadius, snowHeadRadius, snowBodyPaint)


        for (snow in snowList) {
            snow.draw(canvas)
        }
        handler.postDelayed({
            invalidate()
        }, 40)
    }

}