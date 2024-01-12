package com.snowflowerthon.snowman.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.snowflowerthon.snowman.R
import com.snowflowerthon.snowman.data.ApiService
import com.snowflowerthon.snowman.data.RetrofitClient
import com.snowflowerthon.snowman.data.dto.BaseResponseDto
import com.snowflowerthon.snowman.data.dto.response.WeatherResponseDto
import com.snowflowerthon.snowman.data.enums.Clothes
import com.snowflowerthon.snowman.data.enums.Weather
import com.snowflowerthon.snowman.databinding.FragmentHomeBinding
import com.snowflowerthon.snowman.ui.MySharedPreferences.setWeatherId
import retrofit2.Call
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHomeBinding.inflate(inflater, container, false)
        val handler = Handler(Looper.getMainLooper())

        var locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        // 위치 권한 허가
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissions,
                1
            )
        }

        var currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss")
        var now = LocalDateTime.now().format(dateTimeFormat)

//        binding.tv.text = "위도:${currentLocation?.latitude} 경도:${currentLocation?.longitude}\n$now"
//
//        val updateDuringRuntime = object : Runnable {
//            override fun run() {
//                // Update the time in the TextView
//                val now = LocalDateTime.now().format(dateTimeFormat)
//
////                postData(currentLocation!!.latitude,currentLocation!!.longitude)
//                // Schedule the next update in 1 second (1000 milliseconds)
//                handler.postDelayed(this, 1000)
//            }
//        }
        postData(37.556481, 127.0045783)

//        handler.postDelayed(updateDuringRuntime, 1000)




        // Inflate the layout for this fragment
        return binding.root
    }

    fun postData(latitude: Double, longitude: Double) {
        val retrofitAPI = RetrofitClient.getInstance().create(ApiService::class.java)
        retrofitAPI.getWeather(MySharedPreferences.getToken(requireContext()),latitude, longitude)

            .enqueue(object : retrofit2.Callback<BaseResponseDto<WeatherResponseDto>> {
                override fun onResponse(call: Call<BaseResponseDto<WeatherResponseDto>>, response: Response<BaseResponseDto<WeatherResponseDto>>) {
                    if (response.isSuccessful) {
                        // TODO: 서버 응답을 처리
//                                Log.d("Home",latitude.toString()+longitude.toString())
                        Log.d("VoteFragment", response.body()?.success.toString() + response.body()?.data.toString())

                        var data = response.body()?.data

                        data?.weatherId?.let { setWeatherId(requireContext(), it) }

                        binding.tvLocation.text = data?.location.toString()

                        binding.tvNextInfoText1.text = data?.firstBranch?.branchTime.toString()
                        binding.tvNextInfoText2.text = data?.secondBranch?.branchTime.toString()
                        binding.tvNextInfoText3.text = data?.thirdBranch?.branchTime.toString()

                        binding.tvCurrentTemperature.text = "${data?.temperature.toString()}°"

                        binding.tvTemperature1.text = "${data?.firstBranch?.highestTemperature}°/${data?.firstBranch?.lowestTemperature}°"
                        binding.tvTemperature2.text = "${data?.secondBranch?.highestTemperature}°/${data?.secondBranch?.lowestTemperature}°"
                        binding.tvTemperature3.text = "${data?.thirdBranch?.highestTemperature}°/${data?.thirdBranch?.lowestTemperature}°"

                        when (data?.weatherStatus) {
                            Weather.CLEAR.toString() -> { binding.ivWeather.setImageResource(R.drawable.img_sunny) }
                            Weather.RAIN.toString() -> { binding.ivWeather.setImageResource(R.drawable.ic_rainy) }
                            Weather.SNOW.toString() -> { binding.ivWeather.setImageResource(R.drawable.img_snowy) }
                        }

                        when (data?.mainBranch?.headWear){
                            Clothes.BALACLAVA.toString() -> { binding.ivCurrentHeadWear.setImageResource(R.drawable.img_balaclava) }
                            Clothes.EAR_MUFFS.toString() -> { binding.ivCurrentHeadWear.setImageResource(R.drawable.img_ear) }
                            Clothes.NONE.toString() -> { binding.ivCurrentHeadWear.setImageResource(R.drawable.img_empty) }
                        }

                        when (data?.mainBranch?.neckWear){
                            Clothes.SCARF.toString() -> { binding.ivCurrentNeckWear.setImageResource(R.drawable.img_muffler) }
                            Clothes.NONE.toString() -> { binding.ivCurrentNeckWear.setImageResource(R.drawable.img_empty) }
                        }

                        when (data?.mainBranch?.outerWear){
                            Clothes.SHORT_PADDING.toString() -> { binding.ivCurrentOuterWear.setImageResource(
                                R.drawable.img_short_padding) }
                            Clothes.LONG_PADDING.toString() -> { binding.ivCurrentOuterWear.setImageResource(
                                R.drawable.img_long_padding) }
                            Clothes.COAT.toString() -> { binding.ivCurrentOuterWear.setImageResource(R.drawable.img_coat) }
                            Clothes.NONE.toString() -> { binding.ivCurrentOuterWear.setImageResource(R.drawable.img_empty) }
                        }

                        when (data?.mainBranch?.topWear){
                            Clothes.LONG_SLEEVE.toString() -> { binding.ivCurrentTopWear.setImageResource(R.drawable.img_long_shirt) }
                            Clothes.NEAT.toString() -> { binding.ivCurrentTopWear.setImageResource(R.drawable.img_neat) }
                        }

                        //1분기
                        when (data?.firstBranch?.headWear){
                            Clothes.BALACLAVA.toString() -> { binding.ivHeadWear1.setImageResource(R.drawable.img_balaclava) }
                            Clothes.EAR_MUFFS.toString() -> { binding.ivHeadWear1.setImageResource(R.drawable.img_ear) }
                            Clothes.NONE.toString() -> { binding.ivHeadWear1.setImageResource(R.drawable.img_empty) }
                        }

                        when (data?.firstBranch?.neckWear){
                            Clothes.SCARF.toString() -> { binding.ivNeckWear1.setImageResource(R.drawable.img_muffler) }
                            Clothes.NONE.toString() -> { binding.ivNeckWear1.setImageResource(R.drawable.img_empty) }
                        }

                        when (data?.firstBranch?.outerWear){
                            Clothes.SHORT_PADDING.toString() -> { binding.ivOuterWear1.setImageResource(
                                R.drawable.img_short_padding) }
                            Clothes.LONG_PADDING.toString() -> { binding.ivOuterWear1.setImageResource(
                                R.drawable.img_long_padding) }
                            Clothes.COAT.toString() -> { binding.ivOuterWear1.setImageResource(R.drawable.img_coat) }
                            Clothes.NONE.toString() -> { binding.ivOuterWear1.setImageResource(R.drawable.img_empty) }
                        }

                        when (data?.firstBranch?.topWear){
                            Clothes.LONG_SLEEVE.toString() -> { binding.ivTopWear1.setImageResource(R.drawable.img_long_shirt) }
                            Clothes.NEAT.toString() -> { binding.ivTopWear1.setImageResource(R.drawable.img_neat) }
                        }

                        //두번째 분기
                        when (data?.secondBranch?.headWear){
                            Clothes.BALACLAVA.toString() -> { binding.ivHeadWear2.setImageResource(R.drawable.img_balaclava) }
                            Clothes.EAR_MUFFS.toString() -> { binding.ivHeadWear2.setImageResource(R.drawable.img_ear) }
                            Clothes.NONE.toString() -> { binding.ivHeadWear2.setImageResource(R.drawable.img_empty) }
                        }

                        when (data?.secondBranch?.neckWear){
                            Clothes.SCARF.toString() -> { binding.ivNeckWear2.setImageResource(R.drawable.img_muffler) }
                            Clothes.NONE.toString() -> { binding.ivNeckWear2.setImageResource(R.drawable.img_empty) }
                        }

                        when (data?.secondBranch?.outerWear){
                            Clothes.SHORT_PADDING.toString() -> { binding.ivOuterWear2.setImageResource(
                                R.drawable.img_short_padding) }
                            Clothes.LONG_PADDING.toString() -> { binding.ivOuterWear2.setImageResource(
                                R.drawable.img_long_padding) }
                            Clothes.COAT.toString() -> { binding.ivOuterWear2.setImageResource(R.drawable.img_coat) }
                            Clothes.NONE.toString() -> { binding.ivOuterWear2.setImageResource(R.drawable.img_empty) }
                        }

                        when (data?.secondBranch?.topWear){
                            Clothes.LONG_SLEEVE.toString() -> { binding.ivTopWear2.setImageResource(R.drawable.img_long_shirt) }
                            Clothes.NEAT.toString() -> { binding.ivTopWear2.setImageResource(R.drawable.img_neat) }
                        }

                        //세번째 분기

                        when (data?.thirdBranch?.headWear){
                            Clothes.BALACLAVA.toString() -> { binding.ivHeadWear3.setImageResource(R.drawable.img_balaclava) }
                            Clothes.EAR_MUFFS.toString() -> { binding.ivHeadWear3.setImageResource(R.drawable.img_ear) }
                            Clothes.NONE.toString() -> { binding.ivHeadWear3.setImageResource(R.drawable.img_empty) }
                        }

                        when (data?.thirdBranch?.neckWear){
                            Clothes.SCARF.toString() -> { binding.ivNeckWear3.setImageResource(R.drawable.img_muffler) }
                            Clothes.NONE.toString() -> { binding.ivNeckWear3.setImageResource(R.drawable.img_empty) }
                        }

                        when (data?.thirdBranch?.outerWear){
                            Clothes.SHORT_PADDING.toString() -> { binding.ivOuterWear3.setImageResource(
                                R.drawable.img_short_padding) }
                            Clothes.LONG_PADDING.toString() -> { binding.ivOuterWear3.setImageResource(
                                R.drawable.img_long_padding) }
                            Clothes.COAT.toString() -> { binding.ivOuterWear3.setImageResource(R.drawable.img_coat) }
                            Clothes.NONE.toString() -> { binding.ivOuterWear3.setImageResource(R.drawable.img_empty) }
                        }

                        when (data?.thirdBranch?.topWear){
                            Clothes.LONG_SLEEVE.toString() -> { binding.ivTopWear3.setImageResource(R.drawable.img_long_shirt) }
                            Clothes.NEAT.toString() -> { binding.ivTopWear3.setImageResource(R.drawable.img_neat) }
                        }


                    } else {
                        // TODO: 서버 에러 처리
                        Log.d("VoteFragment", response.body()?.success.toString() + response.body()?.data.toString())
                        Toast.makeText(context, "정보 불러오기를 실패했어요.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<BaseResponseDto<WeatherResponseDto>>, t: Throwable) {
                    // TODO: 통신 실패 처리
//                    Log.d("VoteFragment", response.body()?.success.toString() + response.body()?.data.toString())
                    Toast.makeText(context, "정보 불러오기를 실패했어요.", Toast.LENGTH_SHORT).show()
                }
            })
    }
}

