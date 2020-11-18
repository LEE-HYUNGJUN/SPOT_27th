package com.example.sopt_homework.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_homework.ProfileData
import com.example.sopt_homework.ProfileViewHolder
import com.example.sopt_homework.R

class ProfileAdapter (private val context : Context) : RecyclerView.Adapter<ProfileViewHolder>(){

    var data = mutableListOf<ProfileData>()

    var layoutitem = R.layout.item_list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(context).inflate(layoutitem,parent,false)
        return ProfileViewHolder(view)
    }
    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(data[position])

        holder.itemView.setOnClickListener {
            mitemClickListener.onClick(it,position)
        }
    }

    fun changelayout(layoutitem :Int){
        this.layoutitem = layoutitem;
    }
    interface ItemClickListener{
        fun onClick(view : View, position: Int)
    }

    // 리스너 객체 참조를 저장하는 변수
    private lateinit var mitemClickListener: ItemClickListener

    // ItemClickListener 객체 참조를 어댑터에 전달하는 메서드
    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.mitemClickListener = itemClickListener
    }

}