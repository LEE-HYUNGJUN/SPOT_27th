package com.example.sopt_homework

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProfileViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    private  val title : TextView = itemView.findViewById(R.id.title_txt)
    private val subtitle : TextView = itemView.findViewById(R.id.subtitle_txt)

    fun onBind(data : ProfileData){
        title.text = data.title
        subtitle.text = data.subTitle

    }
}