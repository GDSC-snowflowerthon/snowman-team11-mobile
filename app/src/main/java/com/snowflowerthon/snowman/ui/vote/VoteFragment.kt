package com.snowflowerthon.snowman.ui.vote


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.snowflowerthon.snowman.R
import com.snowflowerthon.snowman.data.ApiService
import com.snowflowerthon.snowman.data.RetrofitClient
import com.snowflowerthon.snowman.data.dto.BaseResponseDto
import com.snowflowerthon.snowman.data.dto.request.VoteRequsetDto
import com.snowflowerthon.snowman.data.enums.Clothes
import com.snowflowerthon.snowman.databinding.FragmentVoteBinding
import com.snowflowerthon.snowman.ui.vote.category.TabPagerAdapter
import retrofit2.Call


class VoteFragment : Fragment() {


    var topWear = Clothes.NEAT
    var neckWear = Clothes.NONE
    var headWear = Clothes.NONE
    var outerWear = Clothes.COAT


    private var _binding: FragmentVoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: VoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        // 탭 레이아웃의 탭 이름을 정의합니다. 여기서는 "Tab 1"과 "Tab 2"로 설정하였습니다.


        val tabNames = listOf("아우터", "상의","목도리","아이템")
        binding.myPageviewPager.adapter = TabPagerAdapter(this, tabNames.size)

        // 뷰페이저와 탭 레이아웃을 연결합니다.
        TabLayoutMediator(binding.myPagetabLayout, binding.myPageviewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()

        val codi = VoteRequsetDto(headWear,neckWear,outerWear,topWear)
        val token ="Bearer eyJKV1QiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1aWQiOjIsIlJPTEVfVVNFUiI6IlVTRVIiLCJpYXQiOjE3MDUwMDA4NTksImV4cCI6MTcwNTcyMDg1OX0.bctp9AioWRyjIK_wTTJR-3ahM9LpqfO1AqqsphtLBScHF0w8jG-n1uIkexsVHHjh5jA0fx4A-7RaPIkOQfkA5w"

        binding.btnCustomReset.setOnClickListener {
            binding.ivOuterWear.setImageResource(R.drawable.img_empty)
            binding.ivNeckWear.setImageResource(R.drawable.img_empty)
            binding.ivHeadWear.setImageResource(R.drawable.img_empty)
            binding.ivNeckWear.setImageResource(R.drawable.img_empty)

        }

        binding.btnCustomSave.setOnClickListener {
            val retrofitAPI = RetrofitClient.getInstance().create(ApiService::class.java)
            retrofitAPI.voteClothes(token,1,  codi).enqueue(object : retrofit2.Callback<BaseResponseDto<String?>> {
                override fun onResponse(call: Call<BaseResponseDto<String?>>, response: retrofit2.Response<BaseResponseDto<String?>>) {
                    if (response.isSuccessful) {
                        val baseResponse = response.body()
                        // TODO: 서버 응답을 처리
                        Log.d("VoteFragment", response.body()?.success.toString() + response.body()?.data.toString())
                    } else {
                        // TODO: 서버 에러 처리
                        Log.d("VoteFragment", response.body()?.success.toString() + response.body()?.data.toString())
                    }
                }

                override fun onFailure(call: Call<BaseResponseDto<String?>>, t: Throwable) {
                    // TODO: 통신 실패 처리
//                    Log.d("VoteFragment", response.body()?.success.toString() + response.body()?.data.toString())

                }
            })
        }

        // SharedViewModel을 가져옵니다.
        sharedViewModel = ViewModelProvider(requireActivity()).get(VoteViewModel::class.java)

        // variableX를 감시하고, 값이 변경될 때마다 처리할 로직을 구현합니다.
        // selectedOuterwear를 감시하고, 값이 변경될 때마다 처리할 로직을 구현합니다.
        sharedViewModel.selectedOuterwear.observe(viewLifecycleOwner, Observer { selectedType ->
            when (selectedType) {
                Clothes.COAT -> {
                    outerWear=Clothes.COAT
                    binding.ivOuterWear.setImageResource(R.drawable.img_coat)

                }
                Clothes.LONG_PADDING -> {
                    outerWear=Clothes.LONG_PADDING
                    binding.ivOuterWear.setImageResource(R.drawable.img_long_padding)

                }
                Clothes.SHORT_PADDING -> {
                    outerWear=Clothes.SHORT_PADDING
                    binding.ivOuterWear.setImageResource(R.drawable.img_short_padding)

                }
                Clothes.NONE -> {
                    outerWear=Clothes.NONE
                    binding.ivOuterWear.setImageResource(R.drawable.img_empty)

                }

                else -> {Clothes.NONE}
            }
        })

        sharedViewModel.selectedInnerwear.observe(viewLifecycleOwner, Observer { selectedType ->
            when (selectedType) {
                Clothes.NEAT -> {
                    topWear=Clothes.NEAT
                    binding.ivTopWear.setImageResource(R.drawable.img_neat)

                }
                Clothes.LONG_SLEEVE -> {
                    topWear=Clothes.LONG_SLEEVE
                    binding.ivTopWear.setImageResource(R.drawable.img_long_shirt)

                }

                else -> {Clothes.LONG_SLEEVE}
            }
        })

        sharedViewModel.selectedInnerwear.observe(viewLifecycleOwner, Observer { selectedType ->
            when (selectedType) {
                Clothes.NEAT -> {
                    topWear=Clothes.NEAT
                    binding.ivTopWear.setImageResource(R.drawable.img_neat)

                }
                Clothes.LONG_SLEEVE -> {
                    topWear=Clothes.LONG_SLEEVE
                    binding.ivTopWear.setImageResource(R.drawable.img_long_shirt)

                }

                else -> {Clothes.LONG_SLEEVE}
            }
        })


        sharedViewModel.selectedMuffler.observe(viewLifecycleOwner, Observer { selectedType ->
            when (selectedType) {
                Clothes.SCARF -> {
                    neckWear=Clothes.SCARF
                    binding.ivNeckWear.setImageResource(R.drawable.img_muffler)

                }
                Clothes.NONE -> {
                    neckWear=Clothes.NONE
                    binding.ivNeckWear.setImageResource(R.drawable.img_empty)

                }

                else -> {Clothes.NONE}
            }
        })

        sharedViewModel.selectedItem.observe(viewLifecycleOwner, Observer { selectedType ->
            when (selectedType) {
                Clothes.BALACLAVA -> {
                    headWear=Clothes.BALACLAVA
                    binding.ivHeadWear.setImageResource(R.drawable.img_balaclava)

                }
                Clothes.EAR_MUFFS -> {
                    headWear = Clothes.EAR_MUFFS
                    binding.ivHeadWear.setImageResource(R.drawable.img_ear)
                }
                Clothes.NONE -> {
                    neckWear=Clothes.NONE
                    binding.ivHeadWear.setImageResource(R.drawable.img_empty)

                }

                else -> {Clothes.NONE}
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

