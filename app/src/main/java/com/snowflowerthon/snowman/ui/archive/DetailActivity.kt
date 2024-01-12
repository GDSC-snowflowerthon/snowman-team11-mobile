package com.snowflowerthon.snowman.ui.archive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ViewFlipper
import com.snowflowerthon.snowman.R
import com.snowflowerthon.snowman.data.ApiService
import com.snowflowerthon.snowman.data.RetrofitClient
import com.snowflowerthon.snowman.data.dto.BaseResponseDto
import com.snowflowerthon.snowman.data.dto.response.ArchiveDetailiResponseDto
import com.snowflowerthon.snowman.data.dto.response.VoteHistory
import com.snowflowerthon.snowman.data.enums.Clothes
import com.snowflowerthon.snowman.data.enums.Weather
import com.snowflowerthon.snowman.databinding.ActivityDetailBinding
import com.snowflowerthon.snowman.ui.MySharedPreferences
import retrofit2.Call

class DetailActivity : AppCompatActivity() {

    // 전역 변수로 바인딩 객체 선언
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView( binding.root)

        val id = intent.getLongExtra("archiveId",1)

        val viewFlipper = binding.viewFlipper

        // 클릭 이벤트 처리
        viewFlipper.setOnClickListener {
            // 카드 뒤집기
            viewFlipper.showNext()
        }

        val retrofitAPI = RetrofitClient.getInstance().create(ApiService::class.java)
        retrofitAPI.archiveDetail(MySharedPreferences.getToken(this), id).enqueue(object : retrofit2.Callback<BaseResponseDto<VoteHistory?>> {
            override fun onResponse(call: Call<BaseResponseDto<VoteHistory?>>, response: retrofit2.Response<BaseResponseDto<VoteHistory?>>) {
                if (response.isSuccessful) {
                    // TODO: 서버 응답을 처리
                    Log.d("VoteFragment", response.body()?.success.toString() + response.body()?.data.toString())
                    val data = response.body()?.data
                    binding.inFront.ivHeadWear

                    when (data?.weatherStatus) {
                        Weather.CLEAR.toString() -> {
                            binding.inFront.ivWeather.setImageResource(R.drawable.img_sunny)
                        }

                        Weather.RAIN.toString() -> {
                            binding.inFront.ivWeather.setImageResource(R.drawable.ic_rainy)
                        }

                        Weather.SNOW.toString() -> {
                            binding.inFront.ivWeather.setImageResource(R.drawable.img_snowy)
                        }
                    }

                    when (data?.headWear) {
                        Clothes.BALACLAVA.toString() -> {
                            binding.inFront.ivHeadWear.setImageResource(R.drawable.img_balaclava)
                        }

                        Clothes.EAR_MUFFS.toString() -> {
                            binding.inFront.ivHeadWear.setImageResource(R.drawable.img_ear)
                        }

                        Clothes.NONE.toString() -> {
                            binding.inFront.ivHeadWear.setImageResource(R.drawable.img_empty)
                        }
                    }

                    when (data?.neckWear) {
                        Clothes.SCARF.toString() -> {
                            binding.inFront.ivNeckWear.setImageResource(R.drawable.img_muffler)
                        }

                        Clothes.NONE.toString() -> {
                            binding.inFront.ivNeckWear.setImageResource(R.drawable.img_empty)
                        }
                    }

                    when (data?.outerWear) {
                        Clothes.SHORT_PADDING.toString() -> {
                            binding.inFront.ivOuterWear.setImageResource(R.drawable.img_short_padding)
                        }

                        Clothes.LONG_PADDING.toString() -> {
                            binding.inFront.ivOuterWear.setImageResource(R.drawable.img_long_padding)
                        }

                        Clothes.COAT.toString() -> {
                            binding.inFront.ivOuterWear.setImageResource(R.drawable.img_coat)
                        }

                        Clothes.NONE.toString() -> {
                            binding.inFront.ivOuterWear.setImageResource(R.drawable.img_empty)
                        }
                    }

                    when (data?.topWear) {
                        Clothes.LONG_SLEEVE.toString() -> {
                            binding.inFront.ivTopWear.setImageResource(R.drawable.img_long_shirt)
                        }

                        Clothes.NEAT.toString() -> {
                            binding.inFront.ivTopWear.setImageResource(R.drawable.img_neat)
                        }
                    }

                    binding.inBack.tvLocation.text = data?.location.toString()
                    binding.inBack.tvTime.text = data?.voteTime.toString()
                    binding.inBack.tvId.text = data?.nickname.toString()
                    binding.inBack.tvTemperature.text = data?.temperature.toString()+"°"


                } else {
                    // TODO: 서버 에러 처리
                    Log.d("VoteFragment", response.body()?.success.toString() + response.body()?.data.toString())
                }
            }

            override fun onFailure(call: Call<BaseResponseDto<VoteHistory?>>, t: Throwable) {
                // TODO: 통신 실패 처리
//                    Log.d("VoteFragment", response.body()?.success.toString() + response.body()?.data.toString())
            }
        })

        binding.button.setOnClickListener {
            finish()
        }
    }
}