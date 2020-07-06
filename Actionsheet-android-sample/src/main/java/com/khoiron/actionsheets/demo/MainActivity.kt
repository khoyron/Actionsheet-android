package com.khoiron.actionsheets.demo

import com.khoiron.actionsheets.callback.ActionSheetCallBack
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.khoiron.actionsheets.ActionSheet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val data by lazy { ArrayList<String>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data.add("English")
        data.add("Indonesia")

        testbtn.setOnClickListener {

           ActionSheet(this, data)
                   .setTitle("What do you want to do with the file")
                   .setCancelTitle("Deleted")
                   .setColorTitleCancel(Color.parseColor("#FF4081"))
                   .setColorTitle(Color.parseColor("#FF4081"))
                   .setColorData(Color.parseColor("#FF4081"))
//                   .hideTitle()
//                   .setFontData(R.font.meryana_script)
//                   .setFontCancelTitle(R.font.meryana_script)
//                   .setFontTitle(R.font.meryana_script)
//                   .setSizeTextCancel(30f)
//                   .setSizeTextData(30f)
//                   .setSizeTextTitle(30f)
                   .create(object : ActionSheetCallBack {
                       override fun data(data: String, position: Int) {
                           if ("English".equals(data)){
                               // your action
                               setToast(data)
                           }else if("Indonesia".equals(data)){
                               // your action
                           }

                       }
                   })

        }
    }

    private fun setToast(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show()
    }
}
