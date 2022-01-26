package com.example.fifa.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fifa.R
import com.example.fifa.data.PlayMatch
import com.example.fifa.data.TradeType
import com.example.fifa.databinding.FragmentMatchPlayBinding
import com.example.fifa.ui.adapter.PlayAdapter
import com.example.fifa.ui.adapter.TradeAdapter
import com.example.fifa.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchPlay : Fragment() {
    private lateinit var binding: FragmentMatchPlayBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMatchPlayBinding.inflate(inflater)
        val view = binding.root

        userPlayMatch()
        Log.e("awdawd2123213","awdawd?zzz")

        return view
    }

    private fun userPlayMatch() {
        val type = R.array.matchPlayType.toString()
        val callGetPlayMatch = Constants.api.getMatchPlay("${Constants.KEY}","${arguments?.getString("accessid")}",52,1,10)

        callGetPlayMatch.enqueue(object : Callback<List<PlayMatch>> {
            override fun onResponse(call: Call<List<PlayMatch>>, response: Response<List<PlayMatch>>) {
                val play = response.body()
                Log.e("awdawd","${play}")
            }

            override fun onFailure(call: Call<List<PlayMatch>>, t: Throwable) {

            }

        })
    }

    private fun setAdapter(playList: ArrayList<PlayMatch>) {
        val mPlayAdapter = PlayAdapter(playList)
        binding.rvPlayMatch.adapter = mPlayAdapter
        binding.rvPlayMatch.layoutManager = LinearLayoutManager(context)
    }
}