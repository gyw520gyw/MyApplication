package demo.gyw.com.myapplication.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/2/2.
 */
public class TestShader extends View {

    private Paint paint;

    public TestShader(Context context) {
        this(context, null);
    }

    public TestShader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestShader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 线性
        Shader shader = new LinearGradient(100, 0, 100, 200, new int[]{Color.WHITE, Color.WHITE, Color.WHITE, Color.GRAY}, null, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(100, 100, 100, paint);
        // 圆性
        Shader shader1 = new SweepGradient(250, 250, new int[]{Color.YELLOW, 0xFF99CC66, Color.YELLOW}, null);
        paint.setShader(shader1);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(150, 150, 350, 350);
        canvas.drawArc(rectF, -90, 360, false, paint);

//        canvas.drawCircle(250, 250, 100, paint);
//        canvas.drawRect(0, 0, 200, 200, paint);
    }
}
