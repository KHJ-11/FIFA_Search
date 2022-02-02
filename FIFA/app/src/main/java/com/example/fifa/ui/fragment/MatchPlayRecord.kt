package com.example.fifa.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fifa.R
import com.example.fifa.data.PlayMatch
import com.example.fifa.databinding.FragmentMatchPlayRecordBinding
import com.example.fifa.ui.adapter.PlayAdapter
import com.example.fifa.util.Constants
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchPlayRecord : Fragment() {
    private lateinit var binding: FragmentMatchPlayRecordBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMatchPlayRecordBinding.inflate(layoutInflater)
        val view = binding.root

        userPlayMatch()

        return view
    }

    private fun userPlayMatch() {

        val type = resources.getStringArray(R.array.matchPlayType)

        val callGetPlayTest = Constants.api.getPlayTest(
            "${Constants.KEY}",
            "${arguments?.getString("accessid")}",
            50,
            1,
            10
        )

        callGetPlayTest.enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {

                val play = response.body()

                fun viewData(): ArrayList<PlayMatch> {
                    val list = arrayListOf<PlayMatch>()
                    return list.apply {

                        if (play != null) {
                            for (index in 0 until play.size()) {
                                add(PlayMatch("${play.get(index)}"))
                            }
                        }

                    }
                }

                val mPlayAdapter = PlayAdapter(viewData())
                binding.rvPlayMatch.adapter = mPlayAdapter
                binding.rvPlayMatch.layoutManager = LinearLayoutManager(context)
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {

            }

        })

    }

}