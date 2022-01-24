package com.example.fifa.api

import com.example.fifa.data.MatchType
import com.example.fifa.data.UserInfo
import com.example.fifa.data.UserRanked
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("fifaonline4/v1.0/users?")
    fun getUserInfo(
        @Header("Authorization") Authorization: String,
        @Query("nickname") nickname: String
    ): Call<UserInfo>

    @GET("fifaonline4/v1.0/users/{accessid}/maxdivision")
    fun getUserRanked(
        @Header("Authorization") Authorization: String,
        @Path("accessid") accessid: String
    ): Call<List<UserRanked>>

    @GET("https://static.api.nexon.co.kr/fifaonline4/latest/matchtype.json")
    fun getMatchType(
    ): Call<List<MatchType>>

}