package com.allenmeng.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

//    private  var i :Int = 0
//    private var handler: Handler = object : Handler() {
//        override fun handleMessage(msg: Message) {
//            when (msg.what) {
//                1 -> {
//                    i += 5
//                    pb_btn.setProgress(i)
//                    if (i !== 100) {
//                        sendEmptyMessageDelayed(1.also { Message().what = it }, 500)
//                        pb_btn.setText(i.toString() + "%")
//                    } else if (i === 100) {
//                        pb_btn.setText("下载完成")
//                    }
//                }
//                else -> {
//                }
//            }
//        }
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        handler.sendEmptyMessageDelayed(1,500)
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
    }
}