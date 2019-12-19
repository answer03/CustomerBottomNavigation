package com.allenmeng.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by allen_meng on 2019/12/3.
 * 圆点虚线
 */
class DotDashView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mPaint: Paint? = null

    private var mDotPaint: Paint? = null

    private var mDashGap: Int = 5

    private var mDotColor: Int = Color.GRAY

    private var mDotSize: Int = 5


    init {

        mPaint = Paint()

        mDotPaint = Paint()

        val a =
            context.theme.obtainStyledAttributes(attrs, R.styleable.DotDashView, defStyleAttr, 0)
                .apply {
                    mDotColor = getColor(R.styleable.DotDashView_dot_color, Color.GRAY)

                    mDashGap = getDimension(R.styleable.DotDashView_dash_gap, 5.0f).toInt()

                    mDotSize = getDimension(R.styleable.DotDashView_dot_size, 5.0f).toInt()
                }

        a.recycle()

        mPaint?.color = Color.TRANSPARENT

        mDotPaint?.color = mDotColor

        mDotPaint?.isAntiAlias = true

        mDotPaint?.style = Paint.Style.FILL

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), mPaint!!)
        for (index in mDotSize..width step (mDotSize+mDashGap*2)) {
            canvas?.drawCircle(
                index.toFloat(),
                height.toFloat() / 2,
                mDotSize.toFloat(),
                mDotPaint!!
            )
        }
    }



}

