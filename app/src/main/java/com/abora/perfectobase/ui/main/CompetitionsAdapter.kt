package com.abora.perfectobase.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abora.perfectobase.data.models.CompetitionsData
import com.abora.perfectobase.databinding.ItemCompetitionBinding

class CompetitionsAdapter(
    val actions: MyCategoryAction
) : RecyclerView.Adapter<CompetitionsAdapter.MyHolder>() {

    var list: MutableList<CompetitionsData> = arrayListOf()
    var lastPosition = -1
    lateinit var context: Context

    var selectedItemIdsList = arrayListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(
            ItemCompetitionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        holder.viewItem.data = list[position]


    }

    fun updateData(newList: MutableList<CompetitionsData>, pos: Int) {
        list = newList
        notifyItemChanged(pos)
    }

    fun setData(list: MutableList<CompetitionsData>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MyHolder(val viewItem: ItemCompetitionBinding) : RecyclerView.ViewHolder(viewItem.root) {

        init {
            viewItem.root.setOnClickListener {
                actions.onCategoryClick(data = list[adapterPosition], type = "root", position = adapterPosition)
            }


        }
    }


    interface MyCategoryAction {
        fun onCategoryClick(data: CompetitionsData, type: String, position: Int)
    }
}
