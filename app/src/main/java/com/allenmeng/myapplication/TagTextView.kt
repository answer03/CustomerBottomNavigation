package com.allenmeng.myapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.TextView


/**
 ************************************
 *                                  *
 * Created by allen_meng on 2020/1/9.*
 * 箭头标签文本                       *
 * **********************************
 */
class TagTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    private var mCornerRadius = 10f

    private var mPadding = 10f

    private var mColor: Int = Color.RED

    private var paint: Paint? = null


    init {
        val a =
            context.theme.obtainStyledAttributes(attrs, R.styleable.TagTextView, defStyleAttr, 0)

        mCornerRadius = a.getDimension(R.styleable.TagTextView_tagRadius, mCornerRadius)

        mPadding = a.getDimension(R.styleable.TagTextView_tagPadding, mPadding)

        mColor = a.getColor(R.styleable.TagTextView_tagBgColor, mColor)

        a.recycle()

        paint = Paint()

        paint?.isAntiAlias = true

        paint?.strokeWidth = 2f

        paint?.color = mColor

    }

    override fun onDraw(canvas: Canvas?) {

        val height = measuredHeight //获取View的高度

        val width = measuredWidth //获取View的宽度

        canvas!!.drawRoundRect(
            RectF(
                paddingLeft - mPadding,
                paddingTop - mPadding,
                width - paddingRight + mPadding,
                height - paddingBottom + mPadding
            ), mCornerRadius, mCornerRadius, paint!!
        )

//        canvas!!.drawRoundRect(RectF(paddingLeft.toFloat(), paddingTop.toFloat(),
//            width.toFloat(), height.toFloat()
//        ),mCornerRadius,mCornerRadius,paint!!)

        val path = Path()
        path.moveTo(0f, (height / 2).toFloat())
        path.lineTo(10f, (((height / 2) - mPadding)))
        path.lineTo(10f, (((height / 2) + mPadding)))

        path.close()

        //箭头绘制
//        path.moveTo((width / 2).toFloat(), height.toFloat()) // 三角形顶点
//        path.lineTo((width / 2 - 30).toFloat(), (height - paddingBottom).toFloat()) //三角形左边的点
//        path.lineTo((width / 2 + 30).toFloat(), (height - paddingBottom).toFloat()) //三角形右边的点

        canvas.drawPath(path, paint!!)

        super.onDraw(canvas)

    }

}