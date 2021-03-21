package com.geeksforgeeks.rvadapterviewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.geeksforgeeks.rvadapterviewbinding.databinding.SingleItemBinding

class RvAdapter() : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    private var oldLanguageList= emptyList<Language>()
    // create an inner class with name ViewHolder
    //It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    // bind the items with each item of the list languageList which than will be
    // shown in recycler view
    // to keep it simple we are not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(oldLanguageList[position]){
                binding.tvLangName.text = this.name
                binding.tvExp.text = this.exp
            }
        }
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return oldLanguageList.size
    }

    //
    fun setData(newLanguageList : List<Language>){
        val diffUtil = MyDiffUtil(oldLanguageList , newLanguageList)
        // it calculates the different items of the oldLanguageList and newLanguageList
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        // assign oldLanguageList to newLanguageList
        oldLanguageList = newLanguageList
        diffResult.dispatchUpdatesTo(this)
    }
}