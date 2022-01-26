package com.example.fifa.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fifa.R
import com.example.fifa.data.TradeType
import com.example.fifa.databinding.FragmentSellRecordBinding
import com.example.fifa.ui.adapter.TradeAdapter
import com.example.fifa.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SellRecord : Fragment() {
    private lateinit var binding: FragmentSellRecordBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSellRecordBinding.inflate(layoutInflater)
        val view = binding.root

        userSellTrade()

        return view
    }

    private fun userSellTrade() {
        val callGetTradeType = Constants.api.getTradeType("${Constants.KEY}","${arguments?.getString("accessid")}","sell",1,5)

        callGetTradeType.enqueue(object : Callback<List<TradeType>> {
            override fun onResponse(call: Call<List<TradeType>>, response: Response<List<TradeType>>) {
                val sell = response.body()
                sell?.let {
                    setAdapter(it as ArrayList<TradeType>)
                }

            }

            override fun onFailure(call: Call<List<TradeType>>, t: Throwable) {

            }

        })
    }

    private fun setAdapter(tradeList: ArrayList<TradeType>) {
        val mTradeAdapter = TradeAdapter(tradeList)
        binding.rvBuyRecord.adapter = mTradeAdapter
        binding.rvBuyRecord.layoutManager = LinearLayoutManager(context)
    }

}