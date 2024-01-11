package com.snowflowerthon.snowman.ui.vote.category

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.snowflowerthon.snowman.ui.vote.category.ClothesFragment
import com.snowflowerthon.snowman.ui.vote.category.ItemFragment

class TabPagerAdapter(fragment: Fragment, private val numTabs: Int) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = numTabs

    override fun createFragment(position: Int): Fragment {
        // 여기에서 각 탭에 해당하는 Fragment를 반환합니다.
        // 예를 들어, 각 탭에는 다른 내용이 표시되어야 하므로
        // 해당 탭에 맞는 Fragment를 반환하도록 구현합니다.
        return when (position) {
            0 -> ClothesFragment()
            1 -> ItemFragment()
//            2 -> Tab3Fragment()
//            3 -> Tab4Fragment()
            else -> throw IllegalArgumentException("Invalid tab position: $position")
        }
    }
}
