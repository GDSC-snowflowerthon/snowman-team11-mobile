package com.snowflowerthon.snowman.ui.vote.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.snowflowerthon.snowman.data.enums.Clothes
import com.snowflowerthon.snowman.databinding.FragmentOuterBinding
import com.snowflowerthon.snowman.ui.vote.VoteViewModel


class OuterFragment : Fragment() {


    private var _binding: FragmentOuterBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: VoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOuterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(VoteViewModel::class.java)

        // 목도리 아이템 클릭 이벤트 처리
        binding.btnLongPadding.setOnClickListener {
            // VoteFragment에 알리기
            updateCodi(Clothes.LONG_PADDING)
            Log.d("AFragment", "LONG_PADDING Clicked")
        }

        binding.btnShortPadding.setOnClickListener {
            // VoteFragment에 알리기
            updateCodi(Clothes.SHORT_PADDING)
            Log.d("AFragment", "LONG_PADDING Clicked")
        }

        binding.btnCoat.setOnClickListener {
            // VoteFragment에 알리기
            updateCodi(Clothes.COAT)
            Log.d("AFragment", "LONG_PADDING Clicked")
        }
    }


    private fun updateCodi(newValue: Clothes) {
        sharedViewModel.selectedOuterwear.value = newValue
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}