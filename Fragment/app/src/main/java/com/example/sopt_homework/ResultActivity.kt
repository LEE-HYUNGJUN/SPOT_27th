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

        var intent = intent.getParcelableExtra<ProfileData>("data")
        resulttitle_txt.text = intent.title
        resultsubtitle_txt.text = intent.subTitle
        date_title.text = intent.date_title
        date_subtitle.text= intent.date_subTitle
        contents.text = intent.contents
    }
}