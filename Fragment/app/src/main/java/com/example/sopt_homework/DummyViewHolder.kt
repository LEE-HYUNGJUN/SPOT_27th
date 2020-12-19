package com.example.sopt_homework

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DummyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    private val first_name : TextView = itemView.findViewById(R.id.tv_firstname)
    private val last_name : TextView = itemView.findViewById(R.id.tv_lastname)
    private val email : TextView = itemView.findViewById(R.id.tv_email)

    fun onBind(data : DummyUser){
        first_name.text = data.first_name
        last_name.text = data.last_name
        email.text = data.email
    }
}