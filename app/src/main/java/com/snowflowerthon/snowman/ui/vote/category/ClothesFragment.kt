package com.snowflowerthon.snowman.ui.vote.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.snowflowerthon.snowman.databinding.FragmentClothesBinding
import com.snowflowerthon.snowman.ui.vote.SharedViewModel


class ClothesFragment : Fragment() {


    private var _binding: FragmentClothesBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClothesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // 목도리 아이템 클릭 이벤트 처리
        binding.btnLongPadding.setOnClickListener {
            // VoteFragment에 알리기
            updateVariableX(true)
            Log.d("AFragment", "ImageButton Clicked")

        }
    }


    private fun updateVariableX(newValue: Boolean) {
        sharedViewModel.variableX.value = newValue
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}