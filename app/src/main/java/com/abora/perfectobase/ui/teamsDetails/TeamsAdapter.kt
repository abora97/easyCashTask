package com.abora.perfectobase.ui.teamsDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abora.perfectobase.data.models.CompetitionsData
import com.abora.perfectobase.data.models.Teams
import com.abora.perfectobase.databinding.ItemCompetitionBinding
import com.abora.perfectobase.databinding.ItemSeasonBinding
import com.abora.perfectobase.databinding.ItemTeamsBinding

class TeamsAdapter(
    val actions: MyTeamsAction
) : RecyclerView.Adapter<TeamsAdapter.MyHolder>() {

    var list: MutableList<Teams> = arrayListOf()
    var lastPosition = -1
    lateinit var context: Context

    var selectedItemIdsList = arrayListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(
            ItemTeamsBinding.inflate(
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

    fun updateData(newList: MutableList<Teams>, pos: Int) {
        list = newList
        notifyItemChanged(pos)
    }

    fun setData(list: MutableList<Teams>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class MyHolder(val viewItem: ItemTeamsBinding) : RecyclerView.ViewHolder(viewItem.root) {

        init {
            viewItem.root.setOnClickListener {
                actions.onTeamClick(data = list[adapterPosition], type = "root", position = adapterPosition)
            }

            viewItem.tvPhone.setOnClickListener {
                actions.onTeamClick(data = list[adapterPosition], type = "phone", position = adapterPosition)
            }
            viewItem.tvWebsite.setOnClickListener {
                actions.onTeamClick(data = list[adapterPosition], type = "Website", position = adapterPosition)
            }
            viewItem.tvEmail.setOnClickListener {
                actions.onTeamClick(data = list[adapterPosition], type = "email", position = adapterPosition)
            }

        }
    }


    interface MyTeamsAction {
        fun onTeamClick(data: Teams, type: String, position: Int)
    }
}
