package com.tysheng.playground.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tysheng.playground.R;
import com.tysheng.playground.share.UiUtils;

/**
 * Created by tysheng
 * Date: 22/1/18 5:30 PM.
 * Email: tyshengsx@gmail.com
 */

public class GameOptionButton extends FrameLayout {

    private TextView tvOption;
    private TextView tvChooseCount;

    private int[] colors = new int[]{
            Color.GREEN, Color.WHITE
    };
    private float[] positions = new float[]{
            0.31f, 0.3f
    };
    private LinearGradient gradient;
    private Paint paint;
    private float round = 30;
    private GradientDrawable gradientDrawable;

    public GameOptionButton(@NonNull Context context) {
        this(context, null, 0);
    }

    public GameOptionButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameOptionButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_game_option, this);
        tvOption = findViewById(R.id.tv_option);
        tvChooseCount = findViewById(R.id.tv_choose_count);
        paint = new Paint();
        paint.setAntiAlias(true);
        setRound(UiUtils.dp2px(12));
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(round);
    }

    private void setBgColor(int color) {
        gradientDrawable.setColor(color);
        setBackground(gradientDrawable);
    }

    public void normal(String option) {
        paint.setShader(null);
//        setBgColor(ContextCompat.getColor(getContext(), R.color.option_normal));
        tvOption.setText(option);
        tvOption.setVisibility(VISIBLE);
        LayoutParams layoutParams = (LayoutParams) tvOption.getLayoutParams();
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.leftMargin = 0;
        tvOption.setLayoutParams(layoutParams);
        tvChooseCount.setVisibility(GONE);

        postInvalidate();
    }

    public void selected() {
//        setBgColor(ContextCompat.getColor(getContext(), R.color.option_selected));
    }

    public void correct() {
//        setBgColor(ContextCompat.getColor(getContext(), R.color.option_correct));
    }

    public void wrong() {
//        setBgColor(ContextCompat.getColor(getContext(), R.color.option_error));
    }

    public void announceCorrect(String count, float percent) {
//        Timber.d("count %s percent %f", count, percent);
        LayoutParams layoutParams = (LayoutParams) tvOption.getLayoutParams();
        layoutParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        layoutParams.leftMargin = UiUtils.dp2px(12);
        tvOption.setLayoutParams(layoutParams);
        tvChooseCount.setText(count);
        tvChooseCount.setVisibility(VISIBLE);
//        setColor(ContextCompat.getColor(getContext(), R.color.green_game));
        update(percent);
    }

    public void announceWrong(String count, float percent) {
//        Timber.d("count %s percent %f", count, percent);
        LayoutParams layoutParams = (LayoutParams) tvOption.getLayoutParams();
        layoutParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        layoutParams.leftMargin = UiUtils.dp2px(12);
        tvOption.setLayoutParams(layoutParams);
        tvChooseCount.setText(count);
        tvChooseCount.setVisibility(VISIBLE);
//        setColor(ContextCompat.getColor(getContext(), R.color.grey_tertiary));
        update(percent);
    }

    public void setColor(int color) {
        this.colors[0] = color;
    }

    public void setRound(float round) {
        this.round = round;
    }

    /**
     * @param percent [0,1]
     */
    private void update(float percent) {
        if (percent <= 0) {
            positions[0] = 0;
            positions[1] = 0;
        } else if (percent >= 1) {
            positions[0] = 1;
            positions[1] = 1;
        } else {
            positions[0] = percent + 0.01f;
            positions[1] = percent;
        }
        gradient = new LinearGradient(0, 0, getMeasuredWidth(), 0, colors, positions, Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (paint.getShader() != null) {
            RectF rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
            canvas.drawRoundRect(rectF, round, round, paint);
        }
    }
}
