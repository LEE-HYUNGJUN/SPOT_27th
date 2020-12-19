package com.example.sopt_homework

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KakakoViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    private val title : TextView = itemView.findViewById(R.id.tv_title)
    private val datetime : TextView = itemView.findViewById(R.id.tv_date)
    private val contents : TextView = itemView.findViewById(R.id.tv_content)

    fun onBind(data: KakaoData){
        title.text = data.title
        datetime.text = data.datetime
        contents.text = data.contents
    }
}