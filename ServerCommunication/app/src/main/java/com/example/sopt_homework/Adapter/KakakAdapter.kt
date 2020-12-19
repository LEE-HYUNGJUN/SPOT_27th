package com.example.sopt_homework.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_homework.*

class KakakAdapter(private val context: Context) : RecyclerView.Adapter<KakakoViewHolder>(){

    var data = mutableListOf<KakaoData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KakakoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_kakao_list,parent,false)
        return KakakoViewHolder(view)
    }

    override fun onBindViewHolder(holder: KakakoViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int = data.size
}