package com.cjmobileapps.quidditchplayersandroid.viewutil

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class RoundedImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : AppCompatImageView(context, attrs, defStyle) {
    private val radius = 8.0F
    private var path: Path = Path()
    private val padding = 1
    private val strokeWidth = 8F
    private val rect by lazy {  RectF(0F, 0F, width.toFloat(), height.toFloat()) }
    private lateinit var paint: Paint

    init {
        initBorderPaint()
    }

    private fun initBorderPaint() {
        paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK
        paint.strokeWidth = strokeWidth
    }

    override fun onDraw(canvas: Canvas) {
        path.addRoundRect(rect, radius, radius, Path.Direction.CW)
        canvas.clipPath(path)
        super.onDraw(canvas)
        canvas.drawRect(padding.toFloat(), padding.toFloat(), width - padding.toFloat(), height - padding.toFloat(), paint)
    }
}
