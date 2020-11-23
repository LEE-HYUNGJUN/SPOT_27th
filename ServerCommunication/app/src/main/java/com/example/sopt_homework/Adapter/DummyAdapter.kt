package com.example.sopt_homework.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_homework.*
import kotlin.coroutines.coroutineContext

class DummyAdapter(private val context: Context) : RecyclerView.Adapter<DummyViewHolder>(){
    var data = mutableListOf<DummyUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_dummy_list,parent,false)
        return DummyViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}