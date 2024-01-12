package com.snowflowerthon.snowman.data

import com.snowflowerthon.snowman.data.dto.BaseResponseDto
import com.snowflowerthon.snowman.data.dto.request.LoginRequestDto
import com.snowflowerthon.snowman.data.dto.request.VoteRequsetDto
import com.snowflowerthon.snowman.data.dto.response.ArchiveDetailiResponseDto
import com.snowflowerthon.snowman.data.dto.response.LoginResponseDto
import com.snowflowerthon.snowman.data.dto.response.VoteHistory
import com.snowflowerthon.snowman.data.dto.response.WeatherResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @POST("/api/v1/auth/login") //로그인
    fun login(@Body request: LoginRequestDto)
            : Call<BaseResponseDto<LoginResponseDto?>>

    @POST("/api/v1/weathers/{weatherId}/poll") //투표하기
    fun voteClothes(@Header("Authorization") token: String,
                    @Path("weatherId") weatherId: Long,
                    @Body request: VoteRequsetDto)
            : Call<BaseResponseDto<String?>>


    @GET ("api/v1/users/vote-history/{weatherId}") //아카이빙 모아보기
    fun archive(@Header("Authorization") token: String,
                @Path("weatherId") weatherId: Long)
    :Call<BaseResponseDto<ArchiveDetailiResponseDto?>>


    @GET ("api/v1/users/vote-history/{voteHistoryId}") // 아카이빙 상세보기
    fun archiveDetail(@Header("Authorization") token: String,
                      @Path("voteHistoryId") voteHistoryId: Long)
    :Call<BaseResponseDto<VoteHistory?>>

    @GET("/api/v1/weathers/{weatherId}/poll") //투표 여부 조회
    fun isVote(@Header("Authorization") token: String,
               @Path("weatherId") weatherId: Long)
    : Call<BaseResponseDto<Boolean>>


    @GET("/api/v1/weathers") //날씨 정보 조회
    fun getWeather(@Header("Authorization") token: String,
                   @Query("latitude") latitude: Double,
                   @Query("longitude") longitude: Double)
    : Call<BaseResponseDto<WeatherResponseDto>>
}