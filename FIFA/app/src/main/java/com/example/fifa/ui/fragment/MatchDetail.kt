package com.example.fifa.ui.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fifa.R
import com.example.fifa.data.*
import com.example.fifa.databinding.FragmentMatchDetailBinding
import com.example.fifa.ui.adapter.PlayerAdapter
import com.example.fifa.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MatchDetail : Fragment() {
    private lateinit var binding: FragmentMatchDetailBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMatchDetailBinding.inflate(layoutInflater)
        val view = binding.root

        detailMatch()

        return view
    }

    private fun detailMatch() {
        binding.btPlayerListHome.text = arguments?.getString("nicknameHome")
        binding.btPlayerListAway.text = arguments?.getString("nicknameAway")
        binding.tvMatchResultHome.text = arguments?.getString("goalHome")
        binding.tvMatchResultAway.text = arguments?.getString("goalAway")
        binding.tvMatchResultTotal.text = arguments?.getString("result")
        binding.detailVerse.text = "VS"

        if (arguments?.getString("result") == "무") {
            binding.tvMatchResultTotal.setTextColor(Color.GRAY)
        } else if (arguments?.getString("result") == "승") {
            binding.tvMatchResultTotal.setTextColor(Color.GREEN)
        } else if (arguments?.getString("result") == "패") {
            binding.tvMatchResultTotal.setTextColor(Color.RED)
        }

        val callGetMatchValues = Constants.api.getMatchValues("${Constants.KEY}","${arguments?.getString("matchId")}")

        callGetMatchValues.enqueue(object : Callback<MatchValues> {
            override fun onResponse(call: Call<MatchValues>, response: Response<MatchValues>) {
                val player = response.body()
                val playerHome = player?.matchInfo?.get(0)?.player
                val playerAway = player?.matchInfo?.get(1)?.player

                binding.btPlayerListHome.setOnClickListener {
                    binding.btPlayerListHome.setBackgroundResource(R.color.green)
                    binding.btPlayerListAway.setBackgroundColor(Color.GRAY)

                    playerHome.let {
                        setAdapter(it as ArrayList<Player>)
                    }
                }
                binding.btPlayerListAway.setOnClickListener {
                    binding.btPlayerListAway.setBackgroundResource(R.color.green)
                    binding.btPlayerListHome.setBackgroundColor(Color.GRAY)

                    playerAway.let {
                        setAdapter(it as ArrayList<Player>)
                    }
                }
            }

            override fun onFailure(call: Call<MatchValues>, t: Throwable) {

            }

        })

    }

    private fun setAdapter(playerList: ArrayList<Player>) {
        val mPlayerAdapter = PlayerAdapter(playerList)
        binding.rvPlayerList.adapter = mPlayerAdapter
        binding.rvPlayerList.layoutManager = LinearLayoutManager(context)
        binding.rvPlayerList.addItemDecoration(DividerItemDecoration(binding.rvPlayerList.context, LinearLayoutManager(context).orientation))
        playerList.sortBy { it.spPosition }
    }

}