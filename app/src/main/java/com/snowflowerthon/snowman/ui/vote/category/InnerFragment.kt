package com.snowflowerthon.snowman.ui.vote.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.snowflowerthon.snowman.data.enums.Clothes
import com.snowflowerthon.snowman.databinding.FragmentInnerBinding
import com.snowflowerthon.snowman.ui.vote.VoteViewModel


class InnerFragment : Fragment() {


    private var _binding: FragmentInnerBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: VoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInnerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(VoteViewModel::class.java)

        // 목도리 아이템 클릭 이벤트 처리
        binding.btnLongShirt.setOnClickListener {
            // VoteFragment에 알리기
            updateInner(Clothes.LONG_SLEEVE)
            Log.d("InnerFragment", "LONG_PADDING Clicked")
        }

        binding.btnLongNeat.setOnClickListener {
            // VoteFragment에 알리기
            updateInner(Clothes.NEAT)
            Log.d("InnerFragment", "LONG_PADDING Clicked")
        }

    }


    private fun updateInner(newValue: Clothes) {
        sharedViewModel.selectedInnerwear.value = newValue
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}