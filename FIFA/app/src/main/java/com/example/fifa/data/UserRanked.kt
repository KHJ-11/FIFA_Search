package com.example.fifa.data

import com.google.gson.annotations.SerializedName

data class UserRanked(
    @SerializedName("matchType")
    val matchType: Int = 0,
    @SerializedName("division")
    val division: Int = 0,
    @SerializedName("achievementDate")
    val achievementDate: String = ""
)
