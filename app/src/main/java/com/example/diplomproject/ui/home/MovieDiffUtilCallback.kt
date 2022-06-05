package com.example.diplomproject.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.example.diplomproject.data.database.MovieEntity

class MovieDiffUtilCallback (private val oldList: List<MovieEntity>, private val newList: List<MovieEntity>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}