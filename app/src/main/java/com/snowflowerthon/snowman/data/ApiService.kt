package com.snowflowerthon.snowman.data

import com.snowflowerthon.snowman.data.dto.BaseResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST


interface apiService {
    @POST("/api/v1/weathers/{weatherId}/poll")
    fun voteClothes(): Call<BaseResponseDto<String?>>

}