package com.snowflowerthon.snowman.ui.vote.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.snowflowerthon.snowman.R
import com.snowflowerthon.snowman.data.enums.Clothes
import com.snowflowerthon.snowman.databinding.FragmentItemBinding
import com.snowflowerthon.snowman.databinding.FragmentOuterBinding
import com.snowflowerthon.snowman.ui.vote.VoteViewModel


class ItemFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: VoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(VoteViewModel::class.java)

        // 목도리 아이템 클릭 이벤트 처리
        binding.btnBalaclava.setOnClickListener {
            // VoteFragment에 알리기
            updateItem(Clothes.BALACLAVA)
            Log.d("AFragment", "LONG_PADDING Clicked")
        }

        binding.btnEar.setOnClickListener {
            // VoteFragment에 알리기
            updateItem(Clothes.EAR_MUFFS)
            Log.d("AFragment", "LONG_PADDING Clicked")
        }

        binding.btnNone.setOnClickListener {
            // VoteFragment에 알리기
            updateItem(Clothes.NONE)
            Log.d("AFragment", "LONG_PADDING Clicked")
        }

    }


    private fun updateItem(newValue: Clothes) {
        sharedViewModel.selectedItem.value = newValue
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}