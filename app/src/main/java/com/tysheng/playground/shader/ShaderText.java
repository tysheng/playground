package com.tysheng.playground.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by tysheng
 * Date: 18/9/17 5:41 PM.
 * Email: tyshengsx@gmail.com
 */

public class ShaderText extends android.support.v7.widget.AppCompatTextView {

    int[] colors = new int[]{
            0xff5CC414, Color.WHITE
    };
    float[] positions = new float[]{
            1f, 1f
    };
    LinearGradient gradient;
    int start;
    RectF rectF;
    Paint paint;
    private boolean cleared = false;

    public ShaderText(Context context) {
        this(context, null, 0);
    }

    public ShaderText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShaderText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
//        gradient.setLocalMatrix(matrix);
//        getPaint().setShader(gradient);
    }

    public void clear() {
        cleared = true;
        paint.setShader(null);
        postInvalidate();
    }


    public void update(int offset) {
//        start += offset;
//        matrix.setTranslate(start, 0);
        gradient = new LinearGradient(0, 0, getMeasuredWidth(), 0, colors, positions, Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (paint.getShader() != null) {
            rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
            canvas.drawRoundRect(rectF, 30, 30, paint);
        }

    }
}
