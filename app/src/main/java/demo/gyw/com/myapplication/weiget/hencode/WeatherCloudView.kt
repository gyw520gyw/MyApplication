package demo.gyw.com.myapplication.weiget.hencode

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import demo.gyw.com.myapplication.R
import demo.gyw.com.myapplication.ext.dp2px
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
        this.color = Color.parseColor("#FF0000")
        this.style = Paint.Style.STROKE
        this.strokeWidth = 4f
    }

    private var cloudPath = Path()
    private var rectF = RectF()


    init {

        var ta = context.obtainStyledAttributes(attrs, R.styleable.WeatherView)

        outRadius = ta.getDimension(R.styleable.WeatherView_outRadius, 80f.dp2px(context))

        ta.recycle()

        var outRadiusDp = outRadius.px2dp(context)
        var innerRadiusDp = outRadiusDp - outRadiusDp/10

        innerRadius = innerRadiusDp.dp2px(context)



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
                    Color.parseColor("#53a4e0"), Color.parseColor("#ffffff"), Shader.TileMode.CLAMP)
        }
        canvas.drawCircle(centerX, centerY, innerRadius, innerPaint)


//        drawCloud(centerX, centerY, canvas)

        drawCloud2(centerX, centerY, canvas)

//        cloudPath.arcTo(rectF, 270f, 180f)
//        cloudPath.close()

//        canvas.drawPath(cloudPath, cloudPaint)

    }

    private fun drawCloud(centerX: Float, centerY: Float, canvas: Canvas) {
        // 画云
        rectF.set(centerX, centerY - innerRadius, centerX + 20f.dp2px(context),  centerY - innerRadius + 20f.dp2px(context))
        canvas.drawArc(rectF, 0f, 360f, false, cloudPaint)
//        cloudPath.addArc(rectF, 90f, 180f)
        rectF.set(centerX + 10f.dp2px(context), centerY - innerRadius - 20f.dp2px(context), centerX + 40f.dp2px(context),  centerY - innerRadius + 10f.dp2px(context))
        canvas.drawArc(rectF, 0f, 360f, false, cloudPaint)
//        cloudPath.arcTo(rectF, 150f, 200f)
        rectF.set(centerX + 20f.dp2px(context), centerY - innerRadius - 10f.dp2px(context), centerX + 50f.dp2px(context),  centerY - innerRadius + 20f.dp2px(context))
        canvas.drawArc(rectF, 0f, 360f, false, cloudPaint)
//        cloudPath.arcTo(rectF, 270f, 90f)
        rectF.set(centerX + 40f.dp2px(context), centerY - innerRadius, centerX + 60f.dp2px(context),  centerY - innerRadius + 20f.dp2px(context))
        canvas.drawArc(rectF, 0f, 360f, false, cloudPaint)

    }


    private fun drawCloud2(centerX: Float, centerY: Float, canvas: Canvas) {
        // 画云
        rectF.set(centerX, centerY - innerRadius, centerX + 10f.dp2px(context),  centerY - innerRadius + 10f.dp2px(context))
//        canvas.drawArc(rectF, 0f, 360f, false, cloudPaint)
        cloudPath.addArc(rectF, 90f, 180f)
        rectF.set(centerX + 5f.dp2px(context), centerY - innerRadius - 15f.dp2px(context), centerX + 25f.dp2px(context),  centerY - innerRadius + 5f.dp2px(context))
//        canvas.drawArc(rectF, 0f, 360f, false, cloudPaint)
        cloudPath.arcTo(rectF, 150f, 200f)
        rectF.set(centerX + 10f.dp2px(context), centerY - innerRadius - 10f.dp2px(context), centerX + 30f.dp2px(context),  centerY - innerRadius + 10f.dp2px(context))
//        canvas.drawArc(rectF, 0f, 360f, false, cloudPaint)
        cloudPath.arcTo(rectF, 270f, 90f)
        rectF.set(centerX + 25f.dp2px(context), centerY - innerRadius, centerX + 35f.dp2px(context),  centerY - innerRadius + 10f.dp2px(context))
//        canvas.drawArc(rectF, 0f, 360f, false, cloudPaint)
        cloudPath.arcTo(rectF, 270f, 180f)
        cloudPath.close()

        canvas.drawPath(cloudPath, cloudPaint)
    }


}