package com.example.fifa.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fifa.R
import com.example.fifa.data.MatchValues
import com.example.fifa.data.Player
import com.example.fifa.data.TradeType
import com.example.fifa.databinding.FragmentMatchDetailBinding
import com.example.fifa.ui.adapter.PlayerListAdapter
import com.example.fifa.ui.adapter.TradeAdapter
import com.example.fifa.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

//        binding.btPlayerListHome.setOnClickListener {
//            val callGetPlayer = Constants.api.getPlayer("${Constants.KEY}","${arguments?.getString("matchId")}")
//
//            callGetPlayer.enqueue(object : Callback<List<Player>> {
//                override fun onResponse(call: Call<List<Player>>, response: Response<List<Player>>) {
//                    val player = response.body()
//
//                    player.let {
//                        setAdapter(it as ArrayList<Player>)
//                        Log.e("2kl","12323")
//                    }
//                }
//
//                override fun onFailure(call: Call<List<Player>>, t: Throwable) {
//
//                }
//
//            })
//        }

        val callGetMatchValues = Constants.api.getMatchValues("${Constants.KEY}","${arguments?.getString("matchId")}")

        callGetMatchValues.enqueue(object : Callback<MatchValues> {
            override fun onResponse(call: Call<MatchValues>, response: Response<MatchValues>) {
                binding.btPlayerListAway.setOnClickListener {
                    Log.e("awd","213123")
                    
                }
            }

            override fun onFailure(call: Call<MatchValues>, t: Throwable) {

            }

        })

    }

    private fun setAdapter(playeredList: ArrayList<Player>) {
        val mPlayeredAdapter = PlayerListAdapter(playeredList)
        binding.rvPlayerList.adapter = mPlayeredAdapter
        binding.rvPlayerList.layoutManager = LinearLayoutManager(context)
    }

}