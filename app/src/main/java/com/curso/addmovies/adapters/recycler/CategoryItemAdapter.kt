package com.curso.addmovies.adapters.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.curso.addmovies.R
import com.curso.addmovies.models.CategoryItem


class CategoryItemAdapter(private val context:Context, private val categoryItem: List<CategoryItem>): RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.child_recycler_row, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.itemImage.setImageResource(categoryItem[position].imageUrl)
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }



    inner  class CategoryItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
                val itemImage:ImageView

                init {
                      itemImage = itemView.findViewById(R.id.img_child_item)
                }
    }

}