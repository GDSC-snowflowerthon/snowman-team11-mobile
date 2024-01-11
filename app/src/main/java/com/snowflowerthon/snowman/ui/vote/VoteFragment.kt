package com.snowflowerthon.snowman.ui.vote


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.snowflowerthon.snowman.R
import com.snowflowerthon.snowman.data.RetrofitClient
import com.snowflowerthon.snowman.data.apiService
import com.snowflowerthon.snowman.databinding.FragmentVoteBinding
import com.snowflowerthon.snowman.ui.vote.category.TabPagerAdapter

interface PenguinInteractionListener {
    fun dressPenguinWithScarf()
}

class VoteFragment : Fragment(), PenguinInteractionListener {


    private var _binding: FragmentVoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: SharedViewModel

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


        val tabNames = listOf("아우터", "아이템")
        binding.myPageviewPager.adapter = TabPagerAdapter(this, tabNames.size)

        // 뷰페이저와 탭 레이아웃을 연결합니다.
        TabLayoutMediator(binding.myPagetabLayout, binding.myPageviewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()


        binding.btnCustomSave.setOnClickListener {
            val retrofitAPI = RetrofitClient.getInstance().create(apiService::class.java)
            retrofitAPI.voteClothes()
        }

        // SharedViewModel을 가져옵니다.
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // variableX를 감시하고, 값이 변경될 때마다 처리할 로직을 구현합니다.
        sharedViewModel.variableX.observe(viewLifecycleOwner, Observer { newValue ->
            // newValue로부터 필요한 작업을 수행합니다.
            // 예: newValue가 true이면 어떤 동작을 수행하고, false이면 다른 동작을 수행

            Log.d("VoteFragment","패딩을 눌러버림")
            binding.ivCloth.setImageResource(R.drawable.img_coat)

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // VoteFragment에게 펭귄에게 목도리를 씌우는 동작 수행
    override fun dressPenguinWithScarf() {
        // 원하는 동작을 여기에 추가하세요
        Log.d("VoteFragment","롱패")
        Toast.makeText(requireContext(), "펭귄에게 목도리를 씌웠습니다!", Toast.LENGTH_SHORT).show()
    }
}

