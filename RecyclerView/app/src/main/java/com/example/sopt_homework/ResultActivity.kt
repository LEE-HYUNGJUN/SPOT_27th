package com.example.sopt_homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var title : String
        var subtitle : String

        resulttitle_txt.setText(intent.getStringExtra("title"))
        resultsubtitle_txt.setText(intent.getStringExtra("subtitle"))
        date_title.setText(intent.getStringExtra("date_title"))
        date_subtitle.setText(intent.getStringExtra("date_subTitle"))
        contents.setText(intent.getStringExtra("contents"))
    }
}