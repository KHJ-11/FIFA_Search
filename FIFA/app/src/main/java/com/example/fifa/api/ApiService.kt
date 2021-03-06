package com.example.fifa.api

import com.example.fifa.data.*
import com.google.gson.JsonArray
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

    @GET("https://static.api.nexon.co.kr/fifaonline4/latest/division.json")
    fun getDivisionType(
    ): Call<List<DivisionType>>

    @GET("fifaonline4/v1.0/users/{accessid}/markets?")
    fun getTradeType(
        @Header("Authorization") Authorization: String,
        @Path("accessid") accessid: String,
        @Query("tradetype") tradetype: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<List<TradeType>>

    @GET("https://static.api.nexon.co.kr/fifaonline4/latest/spid.json")
    fun getSpidName(
    ): Call<List<SpidName>>

    @GET("fifaonline4/v1.0/users/{accessid}/matches?")
    fun getPlayTest(
        @Header("Authorization") Authorization: String,
        @Path("accessid") accessid: String,
        @Query("matchtype") matchtype: Int,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ) : Call<JsonArray>

    @GET("fifaonline4/v1.0/matches/{matchid}")
    fun getMatchValues(
        @Header("Authorization") Authorization: String,
        @Path("matchid") matchId: String
    ) : Call<MatchValues>

    @GET("https://static.api.nexon.co.kr/fifaonline4/latest/seasonid.json")
    fun getSeasonId(
    ): Call<List<SeasonIdItem>>

    @GET("https://static.api.nexon.co.kr/fifaonline4/latest/spposition.json")
    fun  getPositionName(
    ): Call<List<PositionItem>>
}