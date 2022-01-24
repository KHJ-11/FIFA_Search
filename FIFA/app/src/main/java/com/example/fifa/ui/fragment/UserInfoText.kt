package com.example.fifa.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fifa.data.MatchType
import com.example.fifa.data.UserRanked
import com.example.fifa.databinding.FragmentUserInfoTextBinding
import com.example.fifa.ui.adapter.RankedAdapter
import com.example.fifa.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoText : Fragment() {
    private lateinit var binding: FragmentUserInfoTextBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUserInfoTextBinding.inflate(layoutInflater)
        val view = binding.root

        userInfoText()
        userRankedText()

        return view
    }

    private fun userInfoText() {
        binding.userTextName.text = arguments?.getString("nickname")
        binding.userTextLevel.text = "Lv ${arguments?.getInt("level")}"
    }

    private fun userRankedText() {
        val callGetUserRanked = Constants.api.getUserRanked("${Constants.KEY}","${arguments?.getString("accessid")}")

        callGetUserRanked.enqueue(object : Callback<List<UserRanked>> {
            override fun onResponse(call: Call<List<UserRanked>>, response: Response<List<UserRanked>>) {
                val ranked = response.body()

                ranked?.let {
                    setAdapter(it as ArrayList<UserRanked>)
                }
            }

            override fun onFailure(call: Call<List<UserRanked>>, t: Throwable) {

            }
        })
    }

    private fun setAdapter(rankedList: ArrayList<UserRanked>) {
        val mRankedAdapter = RankedAdapter(rankedList)
        binding.rvRanked.adapter = mRankedAdapter
        binding.rvRanked.layoutManager = LinearLayoutManager(context)
    }

    private fun matchType() {
        val callGetMatchType = Constants.api.getMatchType()

        callGetMatchType.enqueue(object : Callback<List<MatchType>> {
            override fun onResponse(call: Call<List<MatchType>>, response: Response<List<MatchType>>) {
                val match = response.body()

                if (match != null) {
                    for (index in 0 until match.size) {
                        val type = match.get(index)

                    }
                }
            }

            override fun onFailure(call: Call<List<MatchType>>, t: Throwable) {

            }
        })
    }

}