package com.example.fifa.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fifa.R
import com.example.fifa.data.MatchType
import com.example.fifa.data.UserRanked
import com.example.fifa.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RankedAdapter(private val rankedList: ArrayList<UserRanked>)
    : RecyclerView.Adapter<RankedAdapter.RankedHolder>() {

    inner class RankedHolder(rowRoot: View) : RecyclerView.ViewHolder(rowRoot) {
        val matchItem : TextView = rowRoot.findViewById(R.id.rankedMatch)
        val divisionItem : TextView = rowRoot.findViewById(R.id.rankedDivision)
        val dateItem : TextView = rowRoot.findViewById(R.id.rankedDate)

        private fun getMatchType() {
            val callGetMatchType = Constants.api.getMatchType()

            callGetMatchType.enqueue(object : Callback<List<MatchType>> {
                override fun onResponse(call: Call<List<MatchType>>, response: Response<List<MatchType>>) {

                }

                override fun onFailure(call: Call<List<MatchType>>, t: Throwable) {

                }

            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankedHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ranked, parent, false)
        return RankedHolder(view)
    }

    override fun onBindViewHolder(holder: RankedHolder, position: Int) {
        val rankedData = rankedList[position]
        with(holder) {
            matchItem.text = rankedData.matchType.toString()
            divisionItem.text = rankedData.division.toString()
            dateItem.text = rankedData.achievementDate
        }
    }

    override fun getItemCount(): Int {
        return rankedList.size
    }

}