package com.snowflowerthon.snowman.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.snowflowerthon.snowman.databinding.FragmentHomeBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val handler = Handler(Looper.getMainLooper())

        var locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val permissions = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
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

        binding.tvCurrentInfo.text = "위도:${currentLocation?.latitude} 경도:${currentLocation?.longitude}\n$now"

        val updateDuringRuntime = object : Runnable {
            override fun run() {
                // Update the time in the TextView
                val now = LocalDateTime.now().format(dateTimeFormat)
                binding.tvCurrentInfo.text = "위도:${currentLocation?.latitude} 경도:${currentLocation?.longitude}\n$now"

                // Schedule the next update in 1 second (1000 milliseconds)
                handler.postDelayed(this, 1000)
            }
        }

        handler.postDelayed(updateDuringRuntime, 1000)

        // Inflate the layout for this fragment
        return binding.root
    }
}

