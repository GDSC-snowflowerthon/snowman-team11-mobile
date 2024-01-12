package com.snowflowerthon.snowman.data.dto.request

import com.snowflowerthon.snowman.data.dto.Error
import com.snowflowerthon.snowman.data.enums.Clothes

data class LoginResponseDto(
    val `data`: TokenItem,
    val error: Error?,
    val success: Boolean
)

data class TokenItem(
    val accessToken : String,
    val refreshToken : String
)