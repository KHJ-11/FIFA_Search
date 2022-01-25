package com.example.fifa.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fifa.R
import com.example.fifa.data.DivisionType
import com.example.fifa.data.MatchType
import com.example.fifa.data.UserRanked
import com.example.fifa.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class RankedAdapter(private val rankedList: ArrayList<UserRanked>)
    : RecyclerView.Adapter<RankedAdapter.RankedHolder>() {

    inner class RankedHolder(rowRoot: View) : RecyclerView.ViewHolder(rowRoot) {
        val matchItem : TextView = rowRoot.findViewById(R.id.rankedMatch)
        val divisionItem : TextView = rowRoot.findViewById(R.id.rankedDivision)
        val dateItem : TextView = rowRoot.findViewById(R.id.rankedDate)

        fun setData(item: UserRanked) {
            matchItem.text = item.matchType.toString()
            divisionItem.text = item.division.toString()
            dateItem.text = item.achievementDate

            val callGetMatchType = Constants.api.getMatchType()
            val callGetDivisionType = Constants.api.getDivisionType()

            callGetMatchType.enqueue(object : Callback<List<MatchType>> {
                override fun onResponse(call: Call<List<MatchType>>, response: Response<List<MatchType>>) {
                    val match = response.body()
                    if (match != null) {
                        for (index in 0 until match.size) {
                            if (item.matchType == match.get(index).matchtype) {
                                matchItem.text = match.get(index).desc
                            }

                        }
                    }
                }

                override fun onFailure(call: Call<List<MatchType>>, t: Throwable) {

                }
            })

            callGetDivisionType.enqueue(object : Callback<List<DivisionType>> {
                override fun onResponse(call: Call<List<DivisionType>>, response: Response<List<DivisionType>>) {
                    val division = response.body()
                    if (division != null) {
                        for (index in 0 until division.size) {
                            if (item.division == division.get(index).divisionId) {
                                divisionItem.text = division.get(index).divisionName
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<DivisionType>>, t: Throwable) {

                }
            })

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankedHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ranked, parent, false)
        return RankedHolder(view)
    }

    override fun onBindViewHolder(holder: RankedHolder, position: Int) {
        holder.setData(rankedList[position])
    }

    override fun getItemCount(): Int {
        return rankedList.size
    }

}