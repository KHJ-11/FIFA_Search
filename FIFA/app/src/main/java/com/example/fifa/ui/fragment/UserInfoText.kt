package com.example.fifa.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fifa.R
import com.example.fifa.data.Test
import com.example.fifa.data.TestItem
import com.example.fifa.data.UserRanked
import com.example.fifa.databinding.FragmentHomeEditTextBinding
import com.example.fifa.databinding.FragmentUserInfoTextBinding
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
        val callGetTest = Constants.api.getTest("${Constants.KEY}",", ${arguments?.getString("accessid")}")

        callGetUserRanked.enqueue(object : Callback<ArrayList<UserRanked>> {
            override fun onResponse(call: Call<ArrayList<UserRanked>>, response: Response<ArrayList<UserRanked>>) {
                Log.e("awd","${response.body().toString()}")
                binding.uesrDateText.text = response.body()?.get(0)?.achievementDate
                binding.userDivisionText.text = response.body()?.get(0)?.division.toString()
                binding.uesrMatchText.text = response.body()?.get(0)?.matchType.toString()


            }

            override fun onFailure(call: Call<ArrayList<UserRanked>>, t: Throwable) {
            }

        })

    }
}