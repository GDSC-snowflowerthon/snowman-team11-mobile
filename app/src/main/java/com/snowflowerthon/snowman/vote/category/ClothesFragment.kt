package com.snowflowerthon.snowman.vote.category

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.snowflowerthon.snowman.R
import com.snowflowerthon.snowman.databinding.FragmentClothesBinding
import com.snowflowerthon.snowman.databinding.FragmentVoteBinding
import com.snowflowerthon.snowman.vote.PenguinInteractionListener


class ClothesFragment : Fragment() {


    private var _binding: FragmentClothesBinding? = null
    private val binding get() = _binding!!

    private var interactionListener: PenguinInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClothesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PenguinInteractionListener) {
            interactionListener = context
        } else {
            throw RuntimeException("$context must implement PenguinInteractionListener")
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 목도리 아이템 클릭 이벤트 처리
        binding.btnLongPadding.setOnClickListener {
            // VoteFragment에 알리기
            interactionListener?.dressPenguinWithScarf()
            Log.d("AFragment", "ImageButton Clicked")

        }
    }



    // VoteFragment에서 호출할 수 있도록 설정
    fun setInteractionListener(listener: PenguinInteractionListener) {
        interactionListener = listener
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}