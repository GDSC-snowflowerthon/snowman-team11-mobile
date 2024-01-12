package com.snowflowerthon.snowman.data.dto.response

import com.snowflowerthon.snowman.data.dto.Error
import com.snowflowerthon.snowman.data.enums.Clothes

data class LoginResponseDto(
    val accessToken : String,
    val refreshToken : String
)