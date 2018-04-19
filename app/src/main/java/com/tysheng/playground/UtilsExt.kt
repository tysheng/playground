package com.tysheng.playground

import com.tysheng.playground.share.UiUtils


/**
 * Created by tysheng
 * Date: 27/3/18 3:26 PM.
 * Email: tyshengsx@gmail.com
 */
fun Float.dp2Px() = UiUtils.dp2px(this)
fun Int.dp2Px() = UiUtils.dp2px(this.toFloat())
fun Double.dp2Px() = UiUtils.dp2px(this.toFloat())