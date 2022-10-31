package com.curso.addmovies.adapters.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.curso.addmovies.R
import com.curso.addmovies.models.AllCategory
import com.curso.addmovies.models.CategoryItem


class MainRecyclerAdapter(
    private val context: Context,
    private val allCategory: List<AllCategory>
) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.main_recycler_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.categoryTitle.text = allCategory[position].categoryTitle
        setCatItemRecycler(holder.itemRecycler, allCategory[position].catergoryItems)
    }

    override fun getItemCount(): Int {
        return allCategory.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryTitle: TextView
        var itemRecycler: RecyclerView

        init {
            categoryTitle = itemView.findViewById(R.id.parent_item_title)
            itemRecycler = itemView.findViewById(R.id.child_recyclerview)
        }

    }

    private fun setCatItemRecycler(recyclerView: RecyclerView, categoryItem: List<CategoryItem>) {

        val itemRecyclerAdapter = CategoryItemAdapter(context, categoryItem)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = itemRecyclerAdapter
    }
}