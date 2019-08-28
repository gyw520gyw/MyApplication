package demo.gyw.com.myapplication.weiget.hencode

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import demo.gyw.com.myapplication.R
import demo.gyw.com.myapplication.ext.log
import java.util.logging.Handler

/**
 * 2019/8/20 17:28
 * gyw 翻页效果
 */
class FlipboardView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    var animator = ObjectAnimator.ofFloat(this, "degreeY", 0f, -45f)
    var animator1 = ObjectAnimator.ofFloat(this, "degreeZ", 0f, 270f)
    var animator2 = ObjectAnimator.ofFloat(this, "fixDegreeY", 0f, 40f)

    private var iconBitmap : Bitmap
    private val bitmapWidth: Int
    private val bitmapHeight: Int

    private var camera: Camera = Camera()
    private var paint: Paint = Paint()


    var degreeZ: Float = 0.0f
        get() = field
        set(value) {
            field = value
            invalidate()
        }
    var degreeY: Float = 0.0f
        get() = field
        set(value) {
            field = value
            invalidate()
        }
    var fixDegreeY: Float = 0.0f
        get() = field
        set(value) {
            field = value
            invalidate()
        }

    init {

        var ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.FlipboardView)
        var iconId = ta.getResourceId(R.styleable.FlipboardView_iconId, R.drawable.icon_flipboard1)
        ta.recycle()

        iconBitmap = BitmapFactory.decodeResource(resources, iconId)

        bitmapWidth = iconBitmap.width
        bitmapHeight = iconBitmap.height

        val newZ = resources.displayMetrics.density * 6
        camera.setLocation(0f, 0f, -newZ)

        initListener()
    }


    private fun initListener() {
        setOnClickListener {
            startFlip()
        }
    }

    private fun startFlip() {
//        animator.duration = 1000
//        animator.addListener(object: DefalutAnimatorListener() {
//            override fun onAnimationEnd(animation: Animator?) {
//                state = 1
//                animator1.duration = 2000
//                animator1.start()
//            }
//        })
//
//
//        animator1.addListener(object: DefalutAnimatorListener() {
//            override fun onAnimationEnd(animation: Animator?) {
//                state = 2
//                animator2.duration = 1000
//                animator2.start()
//            }
//        })
//
//        animator2.addListener(object: DefalutAnimatorListener() {
//            override fun onAnimationEnd(animation: Animator?) {
//                state = 2
//                animator3.duration = 1000
//                animator3.start()
//            }
//        })
//
//        animator3.addListener(object: DefalutAnimatorListener() {
//            override fun onAnimationEnd(animation: Animator?) {
//            }
//        })
//
//        animator.start()

        animator.duration = 1000
        animator1.duration = 2000
        animator1.startDelay = 500
        animator2.duration = 800
        animator2.startDelay = 500

        var set = AnimatorSet()

        set.playSequentially(animator, animator1, animator2)
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                // 下次动画前间隔500毫秒
                android.os.Handler().postDelayed({
                    reset()
                    invalidate()
                }, 500)
            }
        })
        set.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var centerX = width / 2
        var centerY = height / 2

        var x = centerX - bitmapWidth / 2
        var y = centerY - bitmapHeight / 2

        log("width:  $width  height : $height")

        canvas.save()
        camera.save()
        canvas.translate(centerX.toFloat(), centerY.toFloat())
        canvas.rotate(-degreeZ)
        camera.rotateY(degreeY)
        camera.applyToCanvas(canvas)
        //计算裁切参数时清注意，此时的canvas的坐标系已经移动
        canvas.clipRect(0, -centerY, centerX, centerY)
        canvas.rotate(degreeZ)
        canvas.translate(-centerX.toFloat(), -centerY.toFloat())
        camera.restore()
        canvas.drawBitmap(iconBitmap, x.toFloat(), y.toFloat(), paint)
        canvas.restore()


        canvas.save()
        camera.save()
        canvas.translate(centerX.toFloat(), centerY.toFloat())
        canvas.rotate(-degreeZ)
        canvas.clipRect(-centerX, -centerY, 0, centerY)
        camera.rotateY(fixDegreeY)
        camera.applyToCanvas(canvas)
        canvas.rotate(degreeZ)
        canvas.translate(-centerX.toFloat(), -centerY.toFloat())
        camera.restore()
        canvas.drawBitmap(iconBitmap, x.toFloat(), y.toFloat(), paint)
        canvas.restore()

//        if(state == 0) {
//
//            canvas.save()
//            canvas.clipRect(x, y, centerX, y + height)
//            canvas.drawBitmap(iconBitmap, x.toFloat(), y.toFloat(), paint)
//            canvas.restore()
//
//            canvas.save()
//            canvas.clipRect(centerX, 0, x + width, y + height)
//
//            camera.save()
//            camera.rotateY(degreeY)
//
//            canvas.translate(centerX.toFloat(), centerY.toFloat())
//            camera.applyToCanvas(canvas)
//            canvas.translate(-centerX.toFloat(), -centerY.toFloat())
//            camera.restore()
//
//
//            canvas.drawBitmap(iconBitmap, x.toFloat(), y.toFloat(), paint)
//            canvas.restore()
//        }

    }

    fun reset() {
        fixDegreeY = 0f
        degreeY = 0f
        degreeZ = 0f
    }
}