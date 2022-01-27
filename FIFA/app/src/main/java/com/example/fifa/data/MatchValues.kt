package com.example.fifa.data

data class MatchValues(
    val matchDate: String,
    val matchId: String,
    val matchInfo: List<MatchInfo>,
    val matchType: Int
)