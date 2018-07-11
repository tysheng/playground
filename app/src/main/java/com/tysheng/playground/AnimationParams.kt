package com.tysheng.playground

import android.animation.PropertyValuesHolder
import android.animation.TimeInterpolator
import android.animation.ValueAnimator

class AnimationParams {

    var floats: FloatArray? = null
    var values: Array<PropertyValuesHolder>? = null
    var duration: Long = 0
    var repeatCount: Int = 0
    var repeatMode = ValueAnimator.RESTART
    var interpolator: TimeInterpolator? = null
}