package com.allenmeng.myapplication

import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        btview.setDefaultPage(0)
//        btview.setOnRadioClickListener(object : BottomNavView.onRadioClickListener {
//            override fun onClick(postion: Int,unSelectPos:Int) {
////                Log.e("111","hahhah$postion aaaaa$unSelectPos")
//                if(postion == 2){
//                    btview.setItemSelected(unSelectPos)
//                }else{
//                    btview.setItemSelected(postion)
//                }
//
//            }
//
//        })
        test.paint.isAntiAlias = true
        test.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }
}