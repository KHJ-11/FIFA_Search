package com.example.fifa.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.fifa.R
import com.example.fifa.data.UserInfo
import com.example.fifa.databinding.FragmentHomeEditTextBinding
import com.example.fifa.util.Constants.KEY
import com.example.fifa.util.Constants.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeEditText : Fragment() {
    private lateinit var binding: FragmentHomeEditTextBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeEditTextBinding.inflate(layoutInflater)
        val view = binding.root

        userEditText()

        return view
    }

    private fun userEditText() {
        binding.homeButton.setOnClickListener {
            getUserInfo()
        }
    }

    private fun getUserInfo() {
        val callGetUserInfo = api.getUserInfo("${KEY}","${binding.homeEditText.text.toString()}")

        callGetUserInfo.enqueue(object : Callback<UserInfo> {
            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                val user = response.body()

                if (binding.homeEditText.text.toString() != user?.nickname) {
                    Navigation.findNavController(binding.root).navigate(R.id.action_homeEditText_to_failSearch)
                } else {
                    val bundle = bundleOf(
                        "nickname" to user?.nickname,
                        "level" to user?.level,
                        "accessid" to user?.accessId
                    )
                    Navigation.findNavController(binding.root).navigate(R.id.action_homeEditText_to_userInfoText, bundle)
                }

            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {

            }

        })
    }
}