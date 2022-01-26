package com.example.fifa.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fifa.R
import com.example.fifa.data.SpidName
import com.example.fifa.data.TradeType
import com.example.fifa.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class TradeAdapter(private val tradeList: ArrayList<TradeType>)
    : RecyclerView.Adapter<TradeAdapter.Tradeholder>() {

    val deFormat = DecimalFormat("###,###")

    inner class Tradeholder(rowRoot: View) : RecyclerView.ViewHolder(rowRoot) {
        val spidItem: TextView = rowRoot.findViewById(R.id.tradeSpid)
        val gradeItem: TextView = rowRoot.findViewById(R.id.tradeGrade)
        val valueItem: TextView = rowRoot.findViewById(R.id.tradeValue)
        val spidPicture: ImageView = rowRoot.findViewById(R.id.tradePicture)

        fun setData(item: TradeType) {
            spidItem.text = item.spid.toString()
            gradeItem.text = item.grade.toString()
            valueItem.text = deFormat.format(item.value.toString().toInt())

            if (item.grade == 1) {
                gradeItem.setBackgroundResource(R.drawable.back_black)
                gradeItem.setTextColor(Color.WHITE)
            } else if (item.grade <= 4) {
                gradeItem.setBackgroundResource(R.drawable.back_bronze)
                gradeItem.setTextColor(Color.BLACK)
            } else if (item.grade <= 7) {
                gradeItem.setBackgroundResource(R.drawable.back_silver)
                gradeItem.setTextColor(Color.BLACK)
            } else if (item.grade <= 10) {
                gradeItem.setBackgroundResource(R.drawable.back_gold)
                gradeItem.setTextColor(Color.BLACK)
            }

            val callGetSpidName = Constants.api.getSpidName()

            callGetSpidName.enqueue(object : Callback<List<SpidName>> {
                override fun onResponse(call: Call<List<SpidName>>, response: Response<List<SpidName>>) {
                    val name = response.body()
                    if (name != null) {
                        for (index in 0 until name.size) {
                            if (item.spid == name.get(index).id) {
                                spidItem.text = name.get(index).name

                                val spid = item.spid.toString()
                                val pid = spid.substring(spid.length - 6, spid.length)
                                val id = pid.replace("^0+".toRegex(),"")

                                Glide.with(itemView.context)
                                    .load("https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/players/p${id}.png")
                                    .error("https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/players/p101000001.png")
                                    .into(spidPicture)

                            }
                        }
                    }
                }
//                player Array 경기 사용 선수 정보 status StatusDTO 선수 경기 스탯
//                03513662369148b21e27982a 233164959 5de0a621bad020f5726b705f
                override fun onFailure(call: Call<List<SpidName>>, t: Throwable) {

                }
            })

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradeAdapter.Tradeholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trade, parent, false)
        return Tradeholder(view)
    }

    override fun onBindViewHolder(holder: TradeAdapter.Tradeholder, position: Int) {
        holder.setData(tradeList[position])
    }

    override fun getItemCount(): Int {
        return tradeList.size
    }
}