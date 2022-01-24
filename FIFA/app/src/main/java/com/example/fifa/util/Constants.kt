package com.example.fifa.util

import com.example.fifa.BuildConfig
import com.example.fifa.api.ApiService
import com.example.fifa.retrofit.RetrofitClient

object Constants {

    const val BASE_URL = "https://api.nexon.co.kr/"

    const val KEY = "${BuildConfig.NEXON_API_KEY}"

    val retrofit = RetrofitClient.getInstance()
    val api = retrofit.create(ApiService::class.java)
}