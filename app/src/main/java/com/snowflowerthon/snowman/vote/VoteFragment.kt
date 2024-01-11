package com.snowflowerthon.snowman.vote


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.snowflowerthon.snowman.databinding.FragmentVoteBinding
import com.snowflowerthon.snowman.vote.category.BottomSheetFragment

class VoteFragment : BottomSheetDialogFragment() {


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

        binding.btn.setOnClickListener {
            // BottomSheetFragment를 띄우기
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
