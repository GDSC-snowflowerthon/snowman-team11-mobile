package com.snowflowerthon.snowman.data

import com.snowflowerthon.snowman.data.dto.BaseResponseDto
import com.snowflowerthon.snowman.data.dto.request.VoteRequsetDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @POST("/api/v1/weathers/{weatherId}/poll")
    fun voteClothes(@Query("weatherId") weatherId: Long,
                    @Body request: VoteRequsetDto)
            : Call<BaseResponseDto<String?>>


}