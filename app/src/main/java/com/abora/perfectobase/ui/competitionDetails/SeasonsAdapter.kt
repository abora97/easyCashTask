package com.abora.perfectobase.ui.competitionDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abora.perfectobase.data.models.CompetitionsData
import com.abora.perfectobase.data.models.Seasons
import com.abora.perfectobase.databinding.ItemCompetitionBinding
import com.abora.perfectobase.databinding.ItemSeasonBinding

class SeasonsAdapter(
    val actions: MySeasonAction
) : RecyclerView.Adapter<SeasonsAdapter.MyHolder>() {

    var list: MutableList<Seasons> = arrayListOf()
    var lastPosition = -1
    lateinit var context: Context

    var selectedItemIdsList = arrayListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(
            ItemSeasonBinding.inflate(
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

    fun updateData(newList: MutableList<Seasons>, pos: Int) {
        list = newList
        notifyItemChanged(pos)
    }

    fun setData(list: MutableList<Seasons>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MyHolder(val viewItem: ItemSeasonBinding) : RecyclerView.ViewHolder(viewItem.root) {

        init {
            viewItem.root.setOnClickListener {
                actions.onSeasonClick(data = list[adapterPosition], type = "root", position = adapterPosition)
            }


        }
    }


    interface MySeasonAction {
        fun onSeasonClick(data: Seasons, type: String, position: Int)
    }
}
