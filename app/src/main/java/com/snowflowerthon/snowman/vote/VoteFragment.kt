package com.snowflowerthon.snowman.vote


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.snowflowerthon.snowman.databinding.FragmentVoteBinding
import com.snowflowerthon.snowman.vote.category.ClothesFragment
import com.snowflowerthon.snowman.vote.category.TabPagerAdapter

interface PenguinInteractionListener {
    fun dressPenguinWithScarf()
}

class VoteFragment : Fragment(), PenguinInteractionListener {


    private var _binding: FragmentVoteBinding? = null
    private val binding get() = _binding!!

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
        val aFragment = ClothesFragment()
        aFragment.setInteractionListener(this) // VoteFragment의 인스턴스를 전달


        val tabNames = listOf("아우터", "아이템")
        binding.myPageviewPager.adapter = TabPagerAdapter(this, tabNames.size)

        // 뷰페이저와 탭 레이아웃을 연결합니다.
        TabLayoutMediator(binding.myPagetabLayout, binding.myPageviewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()

//        // AFragment를 뷰페이저에 추가
//        val aFragment = ClothesFragment()
//        aFragment.setInteractionListener(this) // VoteFragment의 인스턴스를 전달
//
//        val viewPager2 = binding.myPageviewPager
//        val adapter = TabPagerAdapter(this, 2) // 예시로 탭이 2개라 가정
//        adapter.addFragment(aFragment)
//        // 다른 프래그먼트 추가
//
//        viewPager2.adapter = adapter
//
//        // 뷰페이저와 탭 레이아웃 연결
//        TabLayoutMediator(binding.tabLayout, viewPager2) { tab, position ->
//            tab.text = "Tab ${position + 1}"
//        }.attach()
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

