package com.snowflowerthon.snowman.vote.category

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BottomSheetPagerAdapter(fragment: Fragment, private val numTabs: Int) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = numTabs

    override fun createFragment(position: Int): Fragment {
        // 여기에서 각 탭에 해당하는 Fragment를 반환합니다.
        return when (position) {
            0 -> ClothesFragment()
            1 -> ItemFragment()
            else -> throw IllegalArgumentException("Invalid tab position: $position")
        }
    }
}