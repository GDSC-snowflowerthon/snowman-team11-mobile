package com.snowflowerthon.snowman.data

import com.snowflowerthon.snowman.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    // 객체를 하나만 생성하는 싱글톤 패턴을 적용합니다.
    companion object {
        // API 서버의 주소가 BASE_URL이 됩니다.
        private var INSTANCE: Retrofit? = null

        fun getInstance(): Retrofit {
            if (INSTANCE == null) {  // null인 경우에만 생성
                // Set Log Level of OkHttp
                val interceptor = HttpLoggingInterceptor()
                if (BuildConfig.DEBUG) {
                    interceptor.level = HttpLoggingInterceptor.Level.BODY
                } else {
                    interceptor.level = HttpLoggingInterceptor.Level.NONE
                }

                val client = OkHttpClient().newBuilder()
                    .addNetworkInterceptor(interceptor)
                    .build()

                INSTANCE = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)  // API 베이스 URL 설정
                    .client(client)  // OkHttpClient 설정 추가
                    .addConverterFactory(GsonConverterFactory.create()) // 1)
                    .build()
            }
            return INSTANCE!!
        }
    }
}
