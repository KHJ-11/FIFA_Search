package com.example.fifa.data

import com.google.gson.annotations.SerializedName

data class SpidName(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)