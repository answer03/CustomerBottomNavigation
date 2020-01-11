package com.allenmeng.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

/**
 ************************************
 *                                  *
 * Created by allen_meng on 2020/1/8.*
 *                                  *
 * **********************************
 */
class ProgressButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defstyleAttr: Int = 0
) : AppCompatButton(context, attrs, defstyleAttr) {

    private var mMaxProgress: Int = 100

    private var mCurrentProgress: Int = 0

    private var mMinProgress: Int = 0

    private var mDrawableButton: GradientDrawable? = null

    private var mDrawableProgressBackground: GradientDrawable? = null

    private var mDrawableProgress: GradientDrawable? = null

    private var mIsFinish: Boolean = false

    private var mCornerRadius = 0f

    private var mProgressMargin = 0f

    init {
        mDrawableButton = GradientDrawable()

        mDrawableProgressBackground = GradientDrawable()

        mDrawableProgress = GradientDrawable()
        //获取自定义属性
        val a =
            context.theme.obtainStyledAttributes(attrs, R.styleable.ProgressButton, defstyleAttr, 0)
        mProgressMargin = a.getDimension(R.styleable.ProgressButton_progressMargin, mProgressMargin)
        mCornerRadius = a.getDimension(R.styleable.ProgressButton_cornerRadius, mCornerRadius)

        val buttonColor = a.getColor(R.styleable.ProgressButton_buttonColor, Color.RED)

        mDrawableButton?.setColor(buttonColor)

        val buttonBackColor = a.getColor(R.styleable.ProgressButton_progressBackColor, Color.RED)

        mDrawableProgressBackground?.setColor(buttonBackColor)

        val progressColor = a.getColor(R.styleable.ProgressButton_progressColor, Color.CYAN)

        mDrawableProgress?.setColor(progressColor)

        mMaxProgress = a.getInt(R.styleable.ProgressButton_maxProgress, mMaxProgress)

        mMinProgress = a.getInt(R.styleable.ProgressButton_minProgress, mMinProgress)

        mCurrentProgress = a.getInt(R.styleable.ProgressButton_currentProgress, mCurrentProgress)

        //回收资源
        a.recycle()

        //Set corner radius
        mDrawableButton?.cornerRadius = mCornerRadius
        mDrawableProgressBackground?.cornerRadius =mCornerRadius
        mDrawableProgress?.cornerRadius =mCornerRadius - mProgressMargin
        background = mDrawableButton

    }

    override fun onDraw(canvas: Canvas?) {
        if (mCurrentProgress in (mMinProgress + 1)..mMaxProgress && !mIsFinish) {
            var progressWidth =
                measuredWidth.toFloat() * ((mCurrentProgress - mMinProgress).toFloat() / mMaxProgress!! - mMinProgress!!)
            if (progressWidth < mCornerRadius * 2) {
                progressWidth = mCornerRadius * 2
            }
            mDrawableProgress?.setBounds(
                mProgressMargin.toInt(),
                mProgressMargin.toInt(),
                (progressWidth - mProgressMargin).toInt(),
                (measuredHeight - mProgressMargin).toInt()
            )
            mDrawableProgress?.draw(canvas!!)
            if (mCurrentProgress == mMaxProgress) {
                mIsFinish = true
                background = mDrawableButton
            }
        }
        super.onDraw(canvas)
    }

    /**
     * 设置进度
     */
    fun setProgress(progress: Int) {
        if (!mIsFinish) {
            mCurrentProgress = progress
            background = mDrawableProgressBackground
            invalidate()
        }
    }

    fun setMaxProgress(maxProgress: Int) {
        mMaxProgress = maxProgress
    }

    fun setMinProgress(minProgress: Int) {
        mMinProgress = minProgress
    }

    fun reset() {
        mCurrentProgress = mMinProgress
        mIsFinish = false
    }


}