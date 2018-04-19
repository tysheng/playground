package com.tysheng.playground.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.TextView;

import com.tysheng.playground.AnimationParams;

import timber.log.Timber;

/**
 * Created by tysheng
 * Date: 25/1/18 11:41 AM.
 * Email: tyshengsx@gmail.com
 */

public class AnimationUtils {


    public static void alpha(TextView view, AnimationParams params, int i) {
        view.setText(String.valueOf(i));
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(params.getFloats());
        valueAnimator.setDuration(params.getDuration());
        valueAnimator.addUpdateListener(animation -> {
            Timber.d(animation.getAnimatedValue().toString());
            Float value = (Float) animation.getAnimatedValue();
            view.setAlpha(value);
            float scale = (1 - value) * 6 + 1;
            view.setScaleX(scale);
            view.setScaleY(scale);
        });
        valueAnimator.setRepeatCount(params.getRepeatCount());
        valueAnimator.setRepeatMode(params.getRepeatMode());
        if (params.getRepeatMode() == ValueAnimator.INFINITE) {
            valueAnimator.setRepeatCount(-1);
        }
        valueAnimator.setInterpolator(params.getInterpolator());
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (i > 1) {
                    int i1 = i - 1;

                    alpha(view, params, i1);
                }
            }
        });
        valueAnimator.start();

    }

    public static void rocket(View rocket, View fire, AnimationParams params) {
        fire.setPivotY(0);
        fire.setPivotX(fire.getWidth() / 2);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(params.getFloats());
        valueAnimator.setDuration(params.getDuration());
        valueAnimator.setRepeatCount(params.getRepeatCount());
        valueAnimator.setRepeatMode(params.getRepeatMode());
        if (params.getRepeatMode() == ValueAnimator.INFINITE) {
            valueAnimator.setRepeatCount(-1);
        }
        valueAnimator.setInterpolator(params.getInterpolator());
        valueAnimator.addUpdateListener(animation -> {
            Timber.d(animation.getAnimatedValue().toString());
            Float value = (Float) animation.getAnimatedValue();

            rocket.setTranslationY(-(value - 0.5f) * 30);

            fire.setScaleY(value * 0.5f + 1);
            fire.setScaleX(1 + value);
        });
        valueAnimator.start();
    }
}
