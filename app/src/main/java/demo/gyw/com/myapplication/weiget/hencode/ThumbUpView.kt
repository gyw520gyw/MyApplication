package demo.gyw.com.myapplication.weiget.hencode

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import demo.gyw.com.myapplication.R
import demo.gyw.com.myapplication.ext.DefalutAnimatorListener
import demo.gyw.com.myapplication.ext.dp2px
import demo.gyw.com.myapplication.ext.log
import kotlinx.android.synthetic.main.view_thumb_up.view.*

/**
 * 2019/8/20 16:03
 * gyw 文字翻转没有处理好 现在是整个文字一起动
 */
class ThumbUpView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, var rate: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var animate: ViewPropertyAnimator

    private lateinit var animatorSet: AnimatorSet
    private lateinit var animatorSet2: AnimatorSet

    private lateinit var paint: Paint
    private lateinit var paint2: Paint
    private lateinit var paint3: Paint

    var maxCount: Int = 9999


    private var circleProgress: Float = 0f
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    private var circleAlpha: Int = 1
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    private var textAlpha: Int = 1
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    private var textAlpha2: Int = 255
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    private var textTranslationY: Float = 0f
        get() = field
        set(value) {
            field = value
            invalidate()
        }


    var upNum: Int = 99


    init {
        View.inflate(context, R.layout.view_thumb_up, this)

        initAnimator()
        initPaint()

        initListener()

//        thumbUpNum.text = (upNum + 1).toString()

        setWillNotDraw(false)
    }

    var isAnimated = true

    private fun initListener() {
        this.setOnClickListener {
            if (isAnimated) {
               startAction()
                isAnimated = false
            } else {
                cancelAction()
                isAnimated = true
            }
        }
    }

    private fun initPaint() {
        paint = Paint()
        paint.color = Color.parseColor("#cccccc")
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2f.dp2px(context)

        paint2 = Paint()
        paint2.textSize = 14f.dp2px(context)
        paint2.color = Color.parseColor("#999999")

        paint3 = Paint()
        paint3.textSize = 14f.dp2px(context)
        paint3.color = Color.parseColor("#999999")
    }

    private fun initAnimator() {

        var animator1 = ObjectAnimator.ofFloat(thumbUpIcon, "scaleX", 1f, 0.6f)
        var animator2 = ObjectAnimator.ofFloat(thumbUpIcon, "scaleY", 1f, 0.6f)

        var animator4 = ObjectAnimator.ofFloat(this, "circleProgress", 0.4f, 1f)
        var animator5 = ObjectAnimator.ofInt(this, "circleAlpha", 1, 100)

        var animator6 = ObjectAnimator.ofInt(this, "textAlpha", 1, 255)
        var animator66 = ObjectAnimator.ofInt(this, "textAlpha2", 255, 1)
        var animator8 = ObjectAnimator.ofFloat(this, "textTranslationY", 0f, 1f)



        animatorSet = AnimatorSet()
        animatorSet.playTogether(animator1, animator2, animator4, animator5, animator6, animator66, animator8)

        animatorSet.addListener(object: DefalutAnimatorListener() {
            override fun onAnimationEnd(animation: Animator?) {
                thumbUpIcon.setImageResource(R.drawable.ic_messages_like_selected)
                thumbUpIcon.animate().scaleX(1f).scaleY(1f).interpolator = OvershootInterpolator()
                thumbUpShiningIcon.animate().alpha(1f)
            }
        })

        var animator3 = ObjectAnimator.ofFloat(thumbUpShiningIcon, "alpha", 1f, 0f)

        var animator7 = ObjectAnimator.ofInt(this, "textAlpha", 255, 1)
        var animator77 = ObjectAnimator.ofInt(this, "textAlpha2", 1, 255)
        var animator9 = ObjectAnimator.ofFloat(this, "textTranslationY", 1f, 0f)
        animatorSet2 = AnimatorSet()
        animatorSet2.playTogether(animator1, animator2, animator3, animator7, animator77, animator9)

        animatorSet2.addListener(object: DefalutAnimatorListener() {
            override fun onAnimationEnd(animation: Animator?) {
                thumbUpIcon.setImageResource(R.drawable.ic_messages_like_unselected)
                thumbUpIcon.animate().scaleX(1f).scaleY(1f).interpolator = OvershootInterpolator()
            }
        })
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        var centerX = (thumbUpIcon.width/2).toFloat()
        log("centerX  $centerX")
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var measureWidth = thumbUpIcon.measuredWidth + paint2.measureText((upNum+1).toString()) + 4f.dp2px(context)
        var fm = paint2.fontMetrics
        var measureHeight = Math.max(thumbUpIcon.measuredHeight +  4f.dp2px(context) * 2, -(fm.top + fm.bottom) * 3)

        var width = resolveSize(measureWidth.toInt(), widthMeasureSpec)
        var height = resolveSize(measureHeight.toInt(), heightMeasureSpec)

        setMeasuredDimension(width, height)

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 点赞背景散开的圆
        var centerX = (thumbUpIcon.width/2).toFloat()
        var centerY = thumbUpIcon.height/2 + 8f.dp2px(context)

        var maxRadius = Math.min(thumbUpIcon.width/2, thumbUpIcon.height/2)

        var radius = maxRadius * circleProgress
//log(" circleAlpha : $circleAlpha")
        paint.alpha = circleAlpha

        if (radius.toInt() == maxRadius) {
            paint.alpha = 0
        }

        canvas.drawCircle(centerX, centerY, radius, paint)

        var fm = paint2.fontMetrics
        var yOffset = -(fm.ascent + fm.descent)

        // 文字的翻转效果
        paint2.alpha = textAlpha // 隐藏到显示
        paint3.alpha = textAlpha2  // 显示到隐藏

        var translationY = -(fm.top + fm.bottom) * textTranslationY

        log(" translationY : $translationY   textAlpha  $textAlpha")

        canvas.drawText(upNum.toString(), thumbUpIcon.width + 4f.dp2px(context), centerY + yOffset/2 - translationY, paint3)

        canvas.drawText((upNum+1).toString(), thumbUpIcon.width + 4f.dp2px(context), centerY + paint2.fontSpacing - translationY , paint2)
    }



    fun startAction() {
        animatorSet.start()
        invalidate()
    }


    fun cancelAction() {
        animatorSet2.start()
        invalidate()
    }

}