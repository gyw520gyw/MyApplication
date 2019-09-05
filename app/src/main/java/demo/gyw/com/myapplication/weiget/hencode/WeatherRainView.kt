package demo.gyw.com.myapplication.weiget.hencode

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.support.v4.view.animation.PathInterpolatorCompat
import android.util.AttributeSet
import android.view.View
import demo.gyw.com.myapplication.R
import demo.gyw.com.myapplication.ext.dp2px
import demo.gyw.com.myapplication.ext.log
import demo.gyw.com.myapplication.ext.px2dp

/**
 * 2019/8/28 17:24
 * gyw 雨
 */
class WeatherRainView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var centerX: Float = 0f
    private var centerY: Float = 0f

    private var outRadius = 80f.dp2px(context)
    private var innerRadius = 70f.dp2px(context)


    private var outPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.color = Color.parseColor("#e6e8db")
        this.maskFilter = BlurMaskFilter(outRadius, BlurMaskFilter.Blur.SOLID)
    }


    private var innerPaint = Paint(Paint.ANTI_ALIAS_FLAG)


    private var cloudPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.color = Color.parseColor("#eceee8")
    }

    private var lightPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.color = Color.parseColor("#ffffff")
        this.alpha = 100
    }


//    private var cloudPath = Path()
    private var rectF = RectF()

    private var moveX = 0f
        get() = field
        set(value) {
            field = value
            invalidate()
        }


    private var cloudAnimator = ObjectAnimator.ofFloat(this, "moveX", 0f, 1f).apply {
        this.repeatMode = ValueAnimator.REVERSE
        this.repeatCount = -1
        this.duration = 2000

    }


    private var lightAnimator = ObjectAnimator.ofFloat(this, "alpha", 1f, 120f, 1f).apply {
//        this.interpolator = AccelerateInterpolator()
        //设计一个回弹 达到闪电效果
        var interpolatorPath = Path()
        interpolatorPath.lineTo(0.25f, 0.25f)
        interpolatorPath.lineTo(0.5f, 0.8f)
        interpolatorPath.lineTo(0.6f, 0.5f)
        interpolatorPath.lineTo(0.75f, 0.8f)
        interpolatorPath.lineTo(1f, 1f)
        this.interpolator = PathInterpolatorCompat.create(interpolatorPath)
        this.duration = 800
    }


    private var rainList = mutableListOf<Weather>()

    init {

        var ta = context.obtainStyledAttributes(attrs, R.styleable.WeatherView)

        outRadius = ta.getDimension(R.styleable.WeatherView_outRadius, 80f.dp2px(context))

        ta.recycle()

        var outRadiusDp = outRadius.px2dp(context)
        var innerRadiusDp = outRadiusDp - outRadiusDp/10

        innerRadius = innerRadiusDp.dp2px(context)

        cloudAnimator.addListener(object: AnimatorListenerAdapter() {

            override fun onAnimationRepeat(animation: Animator?) {
                super.onAnimationRepeat(animation)
//                log("RepeatRepeatRepeat  ")
                // 闪电 每隔两秒一次
                lightAnimator.start()
            }
        })

        cloudAnimator.start()

    }



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        centerX = (measuredWidth / 2).toFloat()
        centerY = (measuredHeight / 2).toFloat()


        if(rainList.isEmpty()) {
            for(i in 0..10) {
                var rain = Weather()
                        .setScope(centerX - innerRadius, centerX + innerRadius , centerY - innerRadius, centerY + innerRadius)
                        .init(Weather.Type.RAIN)
                rainList.add(rain)
            }
        }
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.BLACK)

        canvas.drawCircle(centerX, centerY, outRadius, outPaint)


        innerPaint?.let {
            it.shader = LinearGradient(centerX - innerRadius, centerY + innerRadius , centerX + innerRadius, centerX - innerRadius,
                    Color.parseColor("#4b9cc2"), Color.parseColor("#9adbd9"), Shader.TileMode.CLAMP)
        }
        canvas.drawCircle(centerX, centerY, innerRadius, innerPaint)

        var rateX = 20f.dp2px(context) * moveX
        drawCloud(centerX - rateX , centerY - innerRadius, 20f.dp2px(context), canvas)

        for (rain in rainList) {
            rain.draw(canvas)
        }
        handler.postDelayed({
            invalidate()
        }, 40)

        lightPaint.alpha = alpha.toInt()
//        log("  alpha  $alpha")
        //闪电效果
        canvas.drawCircle(centerX, centerY, innerRadius, lightPaint)

    }



    //画云 四个圆一个长方形  可控制大小
    private fun drawCloud(centerX: Float, centerY: Float, baseRadius: Float, canvas: Canvas) {

        rectF.set(centerX, centerY, centerX + baseRadius,  centerY + baseRadius)
        canvas.drawArc(rectF, 0f, 360f, false, cloudPaint)
        rectF.set(centerX + baseRadius/2, centerY + baseRadius/2 - baseRadius*3/2, centerX + baseRadius/2 + baseRadius*3/2,  centerY + baseRadius/2)
        canvas.drawArc(rectF, 0f, 360f, false, cloudPaint)
        rectF.set(centerX + baseRadius*3/2 , centerY - baseRadius/2, centerX + baseRadius + baseRadius*3/2,  centerY + baseRadius/2)
        canvas.drawArc(rectF, 0f, 360f, false, cloudPaint)
        rectF.set(centerX + baseRadius*2, centerY, centerX + baseRadius*3,  centerY + baseRadius)
        canvas.drawArc(rectF, 0f, 360f, false, cloudPaint)
        rectF.set(centerX + baseRadius/2, centerY + baseRadius/3, centerX + baseRadius*5/2, centerY + baseRadius)
        canvas.drawRect(rectF, cloudPaint)

    }



    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        cloudAnimator.cancel()
    }



}