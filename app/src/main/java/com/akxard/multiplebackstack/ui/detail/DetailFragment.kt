package com.akxard.multiplebackstack.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akxard.multiplebackstack.databinding.FragmentDefaultBinding
import com.akxard.multiplebackstack.ui.BaseFragment

class DetailFragment : BaseFragment() {
    private var binding: FragmentDefaultBinding? = null
    private val _binding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDefaultBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.tvTitle.text = this::class.java.simpleName

        referenceId?.let {
            _binding.tvBundle.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}