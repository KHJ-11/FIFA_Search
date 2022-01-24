package com.example.fifa.data

import com.google.gson.annotations.SerializedName

data class MatchType(
    @SerializedName("matchtype")
    val matchtype: Int = 0,
    @SerializedName("desc")
    val desc: String = ""
)
