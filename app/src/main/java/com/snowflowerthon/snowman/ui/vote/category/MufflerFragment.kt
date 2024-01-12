package com.snowflowerthon.snowman.ui.vote.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.snowflowerthon.snowman.data.enums.Clothes
import com.snowflowerthon.snowman.databinding.FragmentMufflerBinding
import com.snowflowerthon.snowman.ui.vote.VoteViewModel


class MufflerFragment : Fragment() {

    private var _binding: FragmentMufflerBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedViewModel: VoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMufflerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity()).get(VoteViewModel::class.java)

        // 목도리 아이템 클릭 이벤트 처리
        binding.btnMuffler.setOnClickListener {
            // VoteFragment에 알리기
            updateMuffler(Clothes.SCARF)
            Log.d("AFragment", "LONG_PADDING Clicked")
        }

        binding.btnNone.setOnClickListener {
            // VoteFragment에 알리기
            updateMuffler(Clothes.NONE)
            Log.d("AFragment", "LONG_PADDING Clicked")
        }

    }


    private fun updateMuffler(newValue: Clothes) {
        sharedViewModel.selectedMuffler.value = newValue
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}