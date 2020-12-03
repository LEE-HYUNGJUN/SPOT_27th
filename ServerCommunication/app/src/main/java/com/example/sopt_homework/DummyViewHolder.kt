package com.example.sopt_homework

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DummyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    private val first_name : TextView = itemView.findViewById(R.id.tv_firstname)
    private val last_name : TextView = itemView.findViewById(R.id.tv_lastname)
    private val email : TextView = itemView.findViewById(R.id.tv_email)
    private val avatar : ImageView = itemView.findViewById(R.id.iv_profile)

    fun onBind(data : DummyUser){
        first_name.text = data.first_name
        last_name.text = data.last_name
        email.text = data.email
        Glide.with(itemView.context).load(data.avatar).override(400,400).into(avatar)
    }
}