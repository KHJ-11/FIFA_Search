package com.example.fifa.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fifa.R
import com.example.fifa.data.Player

class PlayerListAdapter(private val playerList: ArrayList<Player>)
    :RecyclerView.Adapter<PlayerListAdapter.PlayerHolder>() {

    inner class PlayerHolder(rowRoot: View) : RecyclerView.ViewHolder(rowRoot) {
        val playerName : TextView = rowRoot.findViewById(R.id.playerName)
        val playerGrade : TextView = rowRoot.findViewById(R.id.playerGrade)
        val playerPosition : TextView = rowRoot.findViewById(R.id.positionName)

        fun setData(item: Player) {
            playerName.text = item.spId.toString()
            playerGrade.text = item.spGrade.toString()
            playerPosition.text = item.spPosition.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListAdapter.PlayerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player_position, parent, false)
        return PlayerHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerListAdapter.PlayerHolder, position: Int) {
        holder.setData(playerList[position])
    }

    override fun getItemCount(): Int {
        return playerList.size
    }
}