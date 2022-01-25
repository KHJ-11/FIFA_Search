package com.example.fifa.data

import com.google.gson.annotations.SerializedName

data class TradeType( // 거래기록
    @SerializedName("tradeDate") // 거래일자
    val tradeDate: String = "",
    @SerializedName("saleSn") // 거래 고유식별자
    val saleSn: String = "",
    @SerializedName("spid") // 선수 고유식별자
    val spid: Int = 0,
    @SerializedName("grade") // 선수 강화등급
    val grade: Int = 0,
    @SerializedName("value") // 선수 가치
    val value: Int = 0
)
