package com.example.fifa.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fifa.R
import com.example.fifa.data.PlayMatch

class PlayAdapter(private val playList: ArrayList<PlayMatch>)
    : RecyclerView.Adapter<PlayAdapter.Playholder>() {

    inner class Playholder(rowRoot: View) : RecyclerView.ViewHolder(rowRoot) {
        val playItem: TextView = rowRoot.findViewById(R.id.playMatch)

        fun setData(item: PlayMatch) {
            playItem.text = item.playMatch.replace("[^A-Za-z0-9]".toRegex(), "")

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayAdapter.Playholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_play, parent, false)
        return Playholder(view)
    }

    override fun onBindViewHolder(holder: PlayAdapter.Playholder, position: Int) {
        holder.setData(playList[position])
    }

    override fun getItemCount(): Int {
        return playList.size
    }

}