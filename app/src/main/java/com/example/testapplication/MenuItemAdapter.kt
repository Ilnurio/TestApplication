package com.example.testapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testapplication.databinding.MenuItemBinding
import kotlin.collections.ArrayList

class MenuItemAdapter: RecyclerView.Adapter<ItemHolder>() {

    private val itemList: MutableList<MenuItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<MenuItem>){
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }
}

class ItemHolder(item:View): RecyclerView.ViewHolder(item) {
    private val binding = MenuItemBinding.bind(item)

    fun bind(item: MenuItem) = with(binding){
       //imageUrl doesn't work from RapidApi. In this case, let's use hardcode.
        // imView.load(item.imageUrl)
        imView.setImageResource(R.drawable.pizza1)
        tvItem.text = item.name
        tvReview.text = item.description
        bPrice.text = item.price.toString()
    }
}
