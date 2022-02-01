package com.example.fifa.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.fifa.R
import com.example.fifa.data.MatchValues
import com.example.fifa.data.PlayMatch
import com.example.fifa.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayAdapter(private val playList: ArrayList<PlayMatch>)
    : RecyclerView.Adapter<PlayAdapter.Playholder>() {

    inner class Playholder(rowRoot: View) : RecyclerView.ViewHolder(rowRoot) {
        val nameHomeItem: TextView = rowRoot.findViewById(R.id.playNicknameHome)
        val nameAwayItem: TextView = rowRoot.findViewById(R.id.playNicknameAway)
        val matchVerseItem: TextView = rowRoot.findViewById(R.id.playMatchVerse)
        val goalHome: TextView = rowRoot.findViewById(R.id.goalHome)
        val goalAway: TextView = rowRoot.findViewById(R.id.goalAway)
        val result: TextView = rowRoot.findViewById(R.id.result)

        fun setData(item: PlayMatch) {
            val matchIds = item.playMatch.replace("[^A-Za-z0-9]".toRegex(), "")

            val callGetMatchValues = Constants.api.getMatchValues("${Constants.KEY}","${matchIds}")

            callGetMatchValues.enqueue(object : Callback<MatchValues> {
                override fun onResponse(call: Call<MatchValues>, response: Response<MatchValues>) {
                    val play = response.body()
                    play.let {
                        setValues(response.body()!!)

                    }

                    itemView.setOnClickListener {
                        val bundle = bundleOf(
                            "nicknameHome" to play?.matchInfo?.get(0)?.nickname,
                            "nicknameAway" to play?.matchInfo?.get(1)?.nickname,
                            "goalHome" to play?.matchInfo?.get(0)?.shoot?.goalTotalDisplay.toString(),
                            "goalAway" to play?.matchInfo?.get(1)?.shoot?.goalTotalDisplay.toString(),
                            "result" to play?.matchInfo?.get(0)?.matchDetail?.matchResult,
                            "matchId" to matchIds
                        )
                        Navigation.findNavController(itemView).navigate(R.id.action_matchPlay_to_matchDetail, bundle)
                    }
                }

                override fun onFailure(call: Call<MatchValues>, t: Throwable) {

                }

            })

        }

        fun setValues(value: MatchValues) {
            nameHomeItem.text = value.matchInfo.get(0).nickname
            nameAwayItem.text = value.matchInfo.get(1).nickname
            matchVerseItem.text = "VS"
            goalHome.text = value.matchInfo.get(0).shoot.goalTotalDisplay.toString()
            goalAway.text = value.matchInfo.get(1).shoot.goalTotalDisplay.toString()
            result.text = value.matchInfo.get(0).matchDetail.matchResult

            if (value.matchInfo.get(0).matchDetail.matchResult == "무") {
                result.setTextColor(Color.GRAY)
            } else if (value.matchInfo.get(0).matchDetail.matchResult == "승") {
                result.setTextColor(Color.GREEN)
            } else if (value.matchInfo.get(0).matchDetail.matchResult == "패") {
                result.setTextColor(Color.RED)
            }

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