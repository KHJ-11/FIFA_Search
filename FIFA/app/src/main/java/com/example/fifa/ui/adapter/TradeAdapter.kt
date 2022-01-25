package com.example.fifa.ui.adapter

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
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
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

            val callGetSpidName = Constants.api.getSpidName()

            callGetSpidName.enqueue(object : Callback<List<SpidName>> {
                override fun onResponse(call: Call<List<SpidName>>, response: Response<List<SpidName>>) {
                    val name = response.body()
                    if (name != null) {
                        for (index in 0 until name.size) {
                            if (item.spid == name.get(index).id) {
                                spidItem.text = name.get(index).name
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<SpidName>>, t: Throwable) {

                }
            })

//            Glide.
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