package com.example.fifa.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fifa.R
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
        userButton()

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

    private fun userButton() {
        binding.userButtonBuy.setOnClickListener {
            val bundle = bundleOf(
            "accessid" to arguments?.getString("accessid")
            )
            Navigation.findNavController(binding.root).navigate(R.id.action_userInfoText_to_buyRecord, bundle)
        }

        binding.userButtonSell.setOnClickListener {
            val bundle = bundleOf(
                "accessid" to arguments?.getString("accessid")
            )
            Navigation.findNavController(binding.root).navigate(R.id.action_userInfoText_to_sellRecord, bundle)
        }

        binding.userButtonPlay.setOnClickListener {
            val bundle = bundleOf(
                "accessid" to arguments?.getString("accessid")
            )
            Navigation.findNavController(binding.root).navigate(R.id.action_userInfoText_to_matchPlay, bundle)
        }

        binding.userButtonPlayM.setOnClickListener {
            val bundle = bundleOf(
                "accessid" to arguments?.getString("accessid")
            )
            Navigation.findNavController(binding.root).navigate(R.id.action_userInfoText_to_matchPlayRecord, bundle)
        }

    }

}