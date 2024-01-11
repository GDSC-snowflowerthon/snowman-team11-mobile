package com.snowflowerthon.snowman.vote.category// com.snowflowerthon.snowman.vote.category.BottomSheetFragment.kt

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.snowflowerthon.snowman.databinding.FragmentBottomSheetBinding

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 탭 레이아웃의 탭 이름을 정의합니다. 여기서는 "Clothes"와 "Items"로 설정하였습니다.
        val tabNames = listOf("Clothes", "Items")

        binding.viewPager2.adapter = BottomSheetPagerAdapter(this, tabNames.size)

        // 뷰페이저와 탭 레이아웃을 연결합니다.
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }
    override fun onStart() {
        super.onStart()

        // 여기서 Bottom Sheet의 높이를 조정합니다.
        val dialog = dialog
        if (dialog != null) {
            val heightInDp = 100
            val heightInPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                heightInDp.toFloat(),
                resources.displayMetrics
            ).toInt()

            val layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                heightInPx
            )
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, heightInPx)

            // Bottom Sheet 외부 터치로 닫히지 않도록 설정
//            dialog.setCancelable(false)
        }

        // Bottom Sheet 내부 터치로 닫히지 않도록 설정
        binding.tabLayout.setOnClickListener { }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
