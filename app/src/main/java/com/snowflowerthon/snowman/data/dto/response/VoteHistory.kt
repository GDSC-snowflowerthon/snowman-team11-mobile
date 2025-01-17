package com.snowflowerthon.snowman.data.dto.response

data class VoteHistory(
    val archiveId: Long,
    val headWear: String,
    val location: String,
    val neckWear: String,
    val outerWear: String,
    val temperature: Int,
    val topWear: String,
    val voteTime: String,
    val weatherStatus: String,
    val nickname: String
)