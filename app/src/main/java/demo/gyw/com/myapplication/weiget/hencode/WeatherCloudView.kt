package demo.gyw.com.myapplication.weiget.hencode

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.support.v4.media.session.PlaybackStateCompat
import android.util.AttributeSet
import android.view.View
import demo.gyw.com.myapplication.R
import demo.gyw.com.myapplication.ext.dp2px
import demo.gyw.com.myapplication.ext.log
import demo.gyw.com.myapplication.ext.px2dp

/**
 * 2019/8/27 11:44
 * gyw 多云
 */
class WeatherCloudView @JvmOverloads constructor(
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

    private var cloudPath = Path()
    private var rectF = RectF()

    private var moveX = 0f
        get() = field
        set(value) {
            field = value
            invalidate()
        }


    var animitor = ObjectAnimator.ofFloat(this, "moveX", 0f, 1f).apply {
        this.repeatMode = ValueAnimator.REVERSE
        this.repeatCount = -1
        this.duration = 2000
    }

    init {

        var ta = context.obtainStyledAttributes(attrs, R.styleable.WeatherView)

        outRadius = ta.getDimension(R.styleable.WeatherView_outRadius, 80f.dp2px(context))

        ta.recycle()

        var outRadiusDp = outRadius.px2dp(context)
        var innerRadiusDp = outRadiusDp - outRadiusDp/10

        innerRadius = innerRadiusDp.dp2px(context)

        animitor.start()

    }



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        centerX = (measuredWidth / 2).toFloat()
        centerY = (measuredHeight / 2).toFloat()

    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.BLACK)

        canvas.drawCircle(centerX, centerY, outRadius, outPaint)


        innerPaint?.let {
            it.shader = LinearGradient(centerX - innerRadius, centerY + innerRadius , centerX + innerRadius/2, centerX - innerRadius,
                    Color.parseColor("#53a4e0"), Color.parseColor("#c3d9e0"), Shader.TileMode.CLAMP)
        }
        canvas.drawCircle(centerX, centerY, innerRadius, innerPaint)


        var rateX = 20f.dp2px(context) * moveX
        drawCloud(centerX + innerRadius/2 - rateX , centerY - innerRadius, 16f.dp2px(context), canvas)

        rateX = 10f.dp2px(context) * moveX
        drawCloud(centerX - rateX , centerY - innerRadius + 16f.dp2px(context), 16f.dp2px(context), canvas)

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



    // 可控制大小 // 使用path 发现动画 有问题
    private fun drawCloud2(centerX: Float, centerY: Float, baseRadius: Float, canvas: Canvas) {
        canvas.save()
        rectF.set(centerX, centerY, centerX + baseRadius,  centerY + baseRadius)
        cloudPath.addArc(rectF, 90f, 180f)
        rectF.set(centerX + baseRadius/2, centerY + baseRadius/2 - baseRadius*3/2, centerX + baseRadius/2 + baseRadius*3/2,  centerY + baseRadius/2)
        cloudPath.arcTo(rectF, 160f, 180f)
        rectF.set(centerX + baseRadius*3/2 , centerY - baseRadius/2, centerX + baseRadius + baseRadius*3/2,  centerY + baseRadius/2)
        cloudPath.arcTo(rectF, 270f, 90f)
        rectF.set(centerX + baseRadius*2, centerY, centerX + baseRadius*3,  centerY + baseRadius)
        cloudPath.arcTo(rectF, 270f, 180f)
        cloudPath.close()

        canvas.drawPath(cloudPath, cloudPaint)

        canvas.restore()
    }


}