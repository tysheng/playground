package com.tysheng.playground.optionbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by tysheng
 * Date: 22/1/18 5:41 PM.
 * Email: tyshengsx@gmail.com
 */

public class GameButton extends android.support.v7.widget.AppCompatTextView {

    private Paint paint;


    private int[] colors = new int[2];
    private float[] positions = new float[]{0.5f, 0.5f};
    private LinearGradient linearGradient;
    RectF rectF = new RectF(0, 0, 300, 300);
    public GameButton(@NonNull Context context) {
        this(context, null, 0);
    }

    public GameButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = getPaint();
        paint.setColor(Color.BLACK);
        colors[0] = Color.RED;
        colors[1] = Color.BLUE;

        linearGradient = new LinearGradient(0, 0, 10, 0, colors[0],colors[1],
                Shader.TileMode.CLAMP);

        paint.setShader(linearGradient);
    }

    public void setPercent(int percent) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(rectF, 30, 30, paint);
    }
}
