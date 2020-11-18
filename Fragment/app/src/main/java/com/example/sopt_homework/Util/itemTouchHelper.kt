package com.example.sopt_homework.Util

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_homework.Adapter.ProfileAdapter

fun itemTouchHelper(adapter: ProfileAdapter) : ItemTouchHelper{
    val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.DOWN or ItemTouchHelper.UP or ItemTouchHelper.START or ItemTouchHelper.END,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val draggedPosition = viewHolder.adapterPosition
            val targetPosition = target.adapterPosition
            adapter.notifyItemMoved(draggedPosition, targetPosition)
            Log.d("tag", adapter.data.toString())
            return false
    }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val swipePosition = viewHolder.adapterPosition
            adapter.data.removeAt(swipePosition)
            adapter.notifyItemRemoved(swipePosition)
            Log.d("tag2", adapter.data.toString())
        }
    })

    return helper
}