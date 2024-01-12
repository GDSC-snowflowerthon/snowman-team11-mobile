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
import com.snowflowerthon.snowman.data.ApiService
import com.snowflowerthon.snowman.data.RetrofitClient
import com.snowflowerthon.snowman.data.dto.BaseResponseDto
import com.snowflowerthon.snowman.data.dto.response.WeatherResponseDto
import com.snowflowerthon.snowman.databinding.FragmentHomeBinding
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

        val updateDuringRuntime = object : Runnable {
            override fun run() {
                // Update the time in the TextView
                val now = LocalDateTime.now().format(dateTimeFormat)
//                binding.tvCurrentInfo.text = "위도:${currentLocation?.latitude} 경도:${currentLocation?.longitude}\n$now"

//                latitude = currentLocation?.latitude!!
//                longitude = currentLocation?.longitude!!
                val token ="Bearer eyJKV1QiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1aWQiOjEsIlJPTEVfVVNFUiI6IlVTRVIiLCJpYXQiOjE3MDUwNjY0NDAsImV4cCI6MTcwNTc4NjQ0MH0.IXB0cUQzgivyInhz4C_w58iIpDDpL8uafUsSSurxoZ4-49pUXuQq0eKAbJgSXs86iRMSvN_4cShcWiaxWMZpzw"
                val retrofitAPI = RetrofitClient.getInstance().create(ApiService::class.java)
                retrofitAPI.getWeather(token, currentLocation?.latitude!!, currentLocation?.longitude!!)
                    .enqueue(object : retrofit2.Callback<BaseResponseDto<WeatherResponseDto>> {
                        override fun onResponse(call: Call<BaseResponseDto<WeatherResponseDto>>, response: Response<BaseResponseDto<WeatherResponseDto>>) {
                            if (response.isSuccessful) {
                                // TODO: 서버 응답을 처리
//                                Log.d("Home",latitude.toString()+longitude.toString())
                                Log.d("VoteFragment", response.body()?.success.toString() + response.body()?.data.toString())

                                var data = response.body()?.data


                                binding.tvLocation.text = data?.location.toString()

                                binding.tvNextInfoText1.text = data?.firstBranch?.branchTime.toString()
                                binding.tvNextInfoText2.text = data?.secondBranch?.branchTime.toString()
                                binding.tvNextInfoText3.text = data?.thirdBranch?.branchTime.toString()


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
                // Schedule the next update in 1 second (1000 milliseconds)
                handler.postDelayed(this, 1000)
            }
        }

        handler.postDelayed(updateDuringRuntime, 1000)




        // Inflate the layout for this fragment
        return binding.root
    }
//
//    fun bindData(response: Response<BaseResponseDto<WeatherResponseDto>>) {
//        var data = response.body()?.data
//
//
//        binding.tvLocation.text = data?.location.toString()
//
//        binding.tvNextInfoText1.text = data?.firstBranch?.branchTime.toString()
//        binding.tvNextInfoText2.text = data?.secondBranch?.branchTime.toString()
//        binding.tvNextInfoText3.text = data?.thirdBranch?.branchTime.toString()
//
//    }
}

