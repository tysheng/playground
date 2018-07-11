package com.tysheng.playground

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import org.jetbrains.anko.collections.forEachWithIndex

/**
 * Created by tysheng
 * Date: 25/4/18 5:29 PM.
 * Email: tyshengsx@gmail.com
 */
class MembersLinearView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr) {

    var size = 26.dp2Px()
    var marginRight = 8.dp2Px()

    var iconList: MutableList<String>? = null
        set(value) {
            field = value
            renderList()
        }

    private fun renderList() {
        removeAllViews()
        if (iconList?.isNotEmpty() == true) {
            iconList!!.forEachWithIndex { index, url ->
                val imageView = RoundedImageView(context).apply {
                    isOval = true
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    borderColor = Color.WHITE
                    borderWidth = 2.dp2Px().toFloat()
                }
                val layoutParams = FrameLayout.LayoutParams(size, size).apply {
                    gravity = Gravity.RIGHT
                    rightMargin = (size - marginRight)* index
                }

                Glide.with(context)
                        .load(url)
                        .into(imageView)
                addView(imageView, layoutParams)
            }
        }
    }


    inner class IconImageView(context: Context?) : ImageView(context) {
        private var drawableRect: RectF? = null
        private val borderWidth = 2
        private val borderPaint: Paint
private var mDrawableRect: RectF? = null
        init {
            borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.WHITE
                style = Paint.Style.FILL
            }
        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
//            canvas?.drawCircle(mDrawableRect!!.centerX(), mDrawableRect!!.centerY(), drawableRect!!.width() / 2, mBitmapPaint)
            if (borderWidth > 0) {
                canvas?.drawCircle(drawableRect!!.centerX(), drawableRect!!.centerY(), drawableRect!!.width() / 2 + borderWidth, borderPaint)
            }
        }

        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            super.onSizeChanged(w, h, oldw, oldh)
            drawableRect = calculateBounds()
        }

        private fun calculateBounds(): RectF {
            val availableWidth = width - paddingLeft - paddingRight
            val availableHeight = height - paddingTop - paddingBottom

            val sideLength = Math.min(availableWidth, availableHeight)

            val left = paddingLeft + (availableWidth - sideLength) / 2f
            val top = paddingTop + (availableHeight - sideLength) / 2f

            return RectF(left, top, left + sideLength, top + sideLength)
        }
    }
}