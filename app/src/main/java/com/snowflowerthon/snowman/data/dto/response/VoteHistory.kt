package com.snowflowerthon.snowman.data.dto.response

data class VoteHistory(
    val archive_id: Int,
    val headWear: String,
    val location: String,
    val neckWear: String,
    val outerWear: String,
    val temperature: Int,
    val topWear: String,
    val voteTime: List<Int>,
    val weatherStatus: String
)