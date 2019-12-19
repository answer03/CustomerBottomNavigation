package com.allenmeng.myapplication

import android.R.drawable
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding


/**
 * Created by allen_meng on 2019/11/27.
 */
class BottomNavView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), View.OnClickListener {

//    //底部按钮
//    private var radioOne: RadioButton? = null
//    private var radioTwo: RadioButton? = null
//    private var radioThree: RadioButton? = null
//    private var radioFour: RadioButton? = null

    private var radioButtonList: MutableList<RadioButton> = mutableListOf<RadioButton>()


    //默认的资源文件
    private val defRes = intArrayOf(
        R.drawable.user_sel,
        R.drawable.user_sel,
        R.drawable.user_sel,
        R.drawable.user_sel
    )

//    private val desRes = arrayOf("首页", "课程", "上课", "我的")

    //全局画笔
    private var mPaint: Paint? = null

    //Bar底部颜色
    private val mBarColor = Color.WHITE

    //点击回调
    private var mListener: onRadioClickListener? = null

    //避免重复点击 默认打开
    private var mRepeated: Boolean = true

    //第一个按钮为默认按钮 记录当前选中按钮
    private var mDefaultPage: Int = 0

    private var itemCount: Int = 4

    private var iconSize: Float = 30f

    private var textSize: Float = 16f

    private var iconList: Int = 0

    private var bottomTextList: Int = 0

    private var drawableArray: IntArray? = null

    private var drawableList: MutableList<Int> = mutableListOf()

    //颜色选择器
    private var selectorTextColor: ColorStateList? = null

    init {

        //获取自定义属性
        val a =
            context.theme.obtainStyledAttributes(attrs, R.styleable.BottomNavView, defStyleAttr, 0)

        itemCount = a.getInteger(R.styleable.BottomNavView_item_count, 4)

        textSize = a.getDimension(R.styleable.BottomNavView_text_size, 16.0f)

        iconSize = a.getDimension(R.styleable.BottomNavView_icon_size, 30.0f)

        selectorTextColor = a.getColorStateList(R.styleable.BottomNavView_text_sel_color)

        iconList = a.getResourceId(R.styleable.BottomNavView_icon_list, 0)

        bottomTextList = a.getResourceId(R.styleable.BottomNavView_bottom_text_list, 0)

        mRepeated = a.getBoolean(R.styleable.BottomNavView_enable_repeat_click, true)

        val topPadding = a.getDimension(R.styleable.BottomNavView_padding_top, 8.0f)

        val bottomPadding = a.getDimension(R.styleable.BottomNavView_padding_bottom, 8.0f)

        val drawablePadding = a.getDimension(R.styleable.BottomNavView_drawable_padding, 0f)

        a.recycle()

        val stringArray = context.resources.getStringArray(iconList)

        val txtArray = context.resources.getStringArray(bottomTextList)

        for (index in 0 until stringArray.size) {

            drawableList.add(ResourceUtils.getImageResourceId(stringArray[index]))

        }
        mPaint = Paint()
        setBackgroundColor(Color.WHITE)
        val gravity = RelativeLayout.CENTER_HORIZONTAL
        val radioGroup = RadioGroup(context)
        radioGroup.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        radioGroup.setPadding(0, topPadding.toInt(), 0, bottomPadding.toInt())
        radioGroup.gravity = Gravity.CENTER_VERTICAL
        radioGroup.orientation = LinearLayout.HORIZONTAL
        radioButtonList.clear()
        for (index in 1..itemCount) {
            val radioButton = RadioButton(context)
            radioButton.layoutParams =
                LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f)
            //设置对齐方式
            radioButton.gravity = Gravity.CENTER_HORIZONTAL
            //设置RadioButton默认样式为空
            radioButton.buttonDrawable = null
            //设置点击去除阴影
            radioButton.setBackgroundColor(Color.TRANSPARENT)
            //设置文字
            radioButton.text = txtArray[index - 1]
            //设置文字大小
            radioButton.textSize = textSize

            radioButton.compoundDrawablePadding = (drawablePadding.toInt() - 16)

            selectorTextColor?.let {
                radioButton.setTextColor(it)
            }

            radioButtonList.add(radioButton)

            radioGroup.addView(radioButton)

            radioButton.setOnClickListener(this)
        }

        //设置资源文件
//        setResourcePictures(defRes)
        setResourcePictures(drawableList)

        //添加视图
        addView(radioGroup)

        //设置默认页面
        setDefaultPage()


    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint?.color = mBarColor
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), mPaint!!)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    //设置资源图片
    fun setResourcePictures(drawableTop: MutableList<Int>) {

        for ((index, e) in drawableTop.withIndex()) {
            val drawable = getResources().getDrawable(e, context.theme)
            //参数从左到右依次是距左右边距离，距上下边距离，图片长度,图片宽度
            drawable.setBounds(
                0,
                0,
                iconSize.toInt(),
                iconSize.toInt()
            )

            radioButtonList.get(index).setCompoundDrawables(
                null,
                drawable,
                null,
                null
            )

        }
    }


    //内部使用,默认使用的页面
    private fun setDefaultPage() {
        setDefaultPage(mDefaultPage)
    }


    //设置默认打开的按钮
    fun setDefaultPage(page: Int) {
        //重置当前page
        mDefaultPage = page
        radioButtonList?.get(page)?.isSelected = true
    }

    override fun onClick(v: View) {

        for ((index, element) in radioButtonList.withIndex()) {
            if (v === element) {
                clickInterception(index)
                break
            }
        }

    }


    //设置点击回调
    fun setOnRadioClickListener(onRadioClickListener: onRadioClickListener) {
        mListener = onRadioClickListener
    }

    fun setItemSelected(postion: Int) {
        //重新记录位置
        mDefaultPage = postion

        for ((index, element) in radioButtonList?.withIndex()) {
            if (index == postion) {
                element?.isSelected = true
                element?.isChecked = true
            } else {
                element?.isChecked = false
                element?.isSelected = false
            }
        }
    }

    //按钮点击回调
    interface onRadioClickListener {
        fun onClick(postion: Int, unSelectPos: Int)

    }

    //点击拦截，过滤重复点击
    private fun clickInterception(page: Int) {
        //重复过滤开启并且点击记录重复视为重复点击
        if (mRepeated && page == mDefaultPage) {
            return
        }

        //开始回调
        mListener?.onClick(page, mDefaultPage)
//        setItemSelected(page)
//        //记录新的页码.
//        mDefaultPage = page

    }

    fun dpToPx(context: Context?, dip: Float): Float {
        if (context == null) {
            return 0f
        }
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dip,
            context.resources.displayMetrics
        )
    }

//    fun getImageResourceId(name: String?): Int {
//        val drawables = drawable()
//        //默认的id
//        var resId = -1
//        try { //根据字符串字段名，取字段//根据资源的ID的变量名获得Field的对象,使用反射机制来实现的
//            val field = drawable::class.java.getField(name!!)
//            //取值
//            resId = field[drawables] as Int
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return resId
//    }


}