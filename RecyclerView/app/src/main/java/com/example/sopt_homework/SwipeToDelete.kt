package com.example.sopt_homework

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SwipeToDelete(context: Context,SdragDir:Int,SwipeDir:Int,dragDir: Int,SSwipeDir: Int):ItemTouchHelper.SimpleCallback(SdragDir,SwipeDir){

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }
}
