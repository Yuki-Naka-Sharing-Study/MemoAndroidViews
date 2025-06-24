package com.example.memoandroidviews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.memoandroidviews.databinding.FragmentMemoBinding

import com.example.memoandroidviews.viewmodel.MemoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MemoFragment : Fragment() {

    private var _binding: FragmentMemoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MemoViewModel by viewModel()
    private val adapter = MemoAdapter { memo -> viewModel.delete(memo) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter

        binding.btnAdd.setOnClickListener {
            val text = binding.editMemo.text.toString()
            if (text.isNotBlank()) {
                viewModel.insert(text)
                binding.editMemo.text.clear()
            }
        }

        viewModel.allMemos.observe(viewLifecycleOwner) { memos ->
            adapter.submitList(memos)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
