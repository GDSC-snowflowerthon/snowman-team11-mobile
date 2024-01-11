package com.snowflowerthon.snowman.data.dto.request

import com.snowflowerthon.snowman.data.enums.Clothes

data class VoteRequsetDto(
    val headWear: Clothes,
    val neckWear: Clothes,
    val outerWear: Clothes,
    val topWear: Clothes
)