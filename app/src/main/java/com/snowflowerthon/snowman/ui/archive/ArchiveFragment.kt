package com.snowflowerthon.snowman.ui.archive

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.snowflowerthon.snowman.PopupActivity
import com.snowflowerthon.snowman.R
import com.snowflowerthon.snowman.data.ApiService
import com.snowflowerthon.snowman.data.RetrofitClient
import com.snowflowerthon.snowman.data.dto.BaseResponseDto
import com.snowflowerthon.snowman.data.dto.response.ArchiveDetailiResponseDto
import com.snowflowerthon.snowman.data.dto.response.VoteHistory
import com.snowflowerthon.snowman.databinding.FragmentArchiveBinding
import com.snowflowerthon.snowman.databinding.FragmentVoteBinding
import com.snowflowerthon.snowman.ui.MySharedPreferences
import retrofit2.Call


class ArchiveFragment : Fragment() {

    private var _binding: FragmentArchiveBinding? = null
    private val binding get() = _binding!!


    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArchiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rvArchive

        lodeData()
    }

    private fun setAdapter(reviewList: List<VoteHistory>) {
        val gridAdapter = GridAdapter(reviewList)
        val gridLayoutManager = GridLayoutManager(activity, 3)

        binding.rvArchive.adapter = gridAdapter
        binding.rvArchive.layoutManager = gridLayoutManager
        binding.rvArchive.setHasFixedSize(true)
    }

    private fun lodeData(){
            //임시 토큰
            val retrofitAPI = RetrofitClient.getInstance().create(ApiService::class.java)
            retrofitAPI.archive(MySharedPreferences.getToken(requireContext()),MySharedPreferences.getWeatherId(requireContext())).enqueue(object : retrofit2.Callback<BaseResponseDto<ArchiveDetailiResponseDto?>> {
                override fun onResponse(call: Call<BaseResponseDto<ArchiveDetailiResponseDto?>>, response: retrofit2.Response<BaseResponseDto<ArchiveDetailiResponseDto?>>) {
                    if (response.isSuccessful) {
                        // TODO: 서버 응답을 처리
                        Log.d("VoteFragment", response.body()?.success.toString() + response.body()?.data.toString())
                        response.body()?.data?.voteHistoryList?.let { setAdapter(it) }
                    } else {
                        // TODO: 서버 에러 처리
                        Log.d("VoteFragment", response.body()?.success.toString() + response.body()?.data.toString())
                    }
                }

                override fun onFailure(call: Call<BaseResponseDto<ArchiveDetailiResponseDto?>>, t: Throwable) {
                    // TODO: 통신 실패 처리
//                    Log.d("VoteFragment", response.body()?.success.toString() + response.body()?.data.toString())
                }
            })
    }



}