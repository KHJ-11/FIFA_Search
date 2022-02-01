package com.example.fifa.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fifa.R
import com.example.fifa.data.Player
import com.example.fifa.data.PositionItem
import com.example.fifa.data.SpidName
import com.example.fifa.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerAdapter(private val playerList: ArrayList<Player>)
    :RecyclerView.Adapter<PlayerAdapter.PlayerHolder>() {

    inner class PlayerHolder(rowRoot: View) : RecyclerView.ViewHolder(rowRoot) {
        val playerName : TextView = rowRoot.findViewById(R.id.playerName)
        val playerGrade : TextView = rowRoot.findViewById(R.id.playerGrade)
        val playerPosition : TextView = rowRoot.findViewById(R.id.positionName)
        val positionColor : TextView = rowRoot.findViewById(R.id.positionColor)

        fun setData(item: Player) {
            playerName.text = item.spId.toString()
            playerGrade.text = item.spGrade.toString()
            playerPosition.text = item.spPosition.toString()

            if (item.spPosition == 0) {
                positionColor.setBackgroundColor(Color.YELLOW)
            } else if (item.spPosition <= 8) {
                positionColor.setBackgroundColor(Color.BLUE)
            } else if (item.spPosition <= 19) {
                positionColor.setBackgroundColor(Color.GREEN)
            } else if (item.spPosition <= 27) {
                positionColor.setBackgroundColor(Color.RED)
            } else if (item.spPosition == 28) {
                positionColor.setBackgroundColor(Color.GRAY)
            }

            if (item.spGrade == 1) {
                playerGrade.setBackgroundResource(R.drawable.back_black)
                playerGrade.setTextColor(Color.WHITE)
            } else if (item.spGrade <= 4) {
                playerGrade.setBackgroundResource(R.drawable.back_bronze)
                playerGrade.setTextColor(Color.BLACK)
            } else if (item.spGrade <= 7) {
                playerGrade.setBackgroundResource(R.drawable.back_silver)
                playerGrade.setTextColor(Color.BLACK)
            } else if (item.spGrade <= 10) {
                playerGrade.setBackgroundResource(R.drawable.back_gold)
                playerGrade.setTextColor(Color.BLACK)
            }

            val callGetPosition = Constants.api.getPositionName()
            val callGetSpidName = Constants.api.getSpidName()

            callGetPosition.enqueue(object : Callback<List<PositionItem>> {
                override fun onResponse(call: Call<List<PositionItem>>, response: Response<List<PositionItem>>) {
                    val position = response.body()

                    if (position != null) {
                        for (index in 0 until position.size) {
                            if (item.spPosition == position.get(index).spposition) {
                                playerPosition.text = position.get(index).desc
                            }
                        }

                    }
                }

                override fun onFailure(call: Call<List<PositionItem>>, t: Throwable) {

                }

            })

            callGetSpidName.enqueue(object : Callback<List<SpidName>> {
                override fun onResponse(call: Call<List<SpidName>>, response: Response<List<SpidName>>) {
                    val name = response.body()

                    if (name != null) {
                        for (index in 0 until name.size) {
                            if (item.spId == name.get(index).id) {
                                playerName.text = name.get(index).name
                            }
                        }

                    }
                }

                override fun onFailure(call: Call<List<SpidName>>, t: Throwable) {

                }

            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player_position, parent, false)
        return PlayerHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.setData(playerList[position])
    }

    override fun getItemCount(): Int {
        return playerList.size
    }
}