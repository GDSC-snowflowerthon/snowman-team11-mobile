package com.snowflowerthon.snowman.data.dto


data class BaseResponseDto<T>(
    val data: T?,
    val error: Error?,
    val success: Boolean
)