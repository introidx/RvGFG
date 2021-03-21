package com.geeksforgeeks.rvadapterviewbinding

import androidx.recyclerview.widget.DiffUtil

// pass two list one oldList and second newList
class MyDiffUtil(
    private val oldList : List<Language>,
    private val newList : List<Language>
) :DiffUtil.Callback() {
    // implement methods
    override fun getOldListSize(): Int {
        // return oldList size
        return oldList.size
    }

    override fun getNewListSize(): Int {
        // return newList size
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // compare items based on their unique id
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        // in here compare each item if they are same or different
        // return false if any data is same else return true
        return when{
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            oldList[oldItemPosition].exp != newList[newItemPosition].exp -> false
            else -> true
        }
    }
}