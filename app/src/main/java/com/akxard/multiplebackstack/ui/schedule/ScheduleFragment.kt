package com.akxard.multiplebackstack.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.akxard.multiplebackstack.databinding.FragmentDefaultBinding
import com.akxard.multiplebackstack.ui.BaseFragment
import com.akxard.multiplebackstack.ui.navigation.nav_graph

class ScheduleFragment : BaseFragment() {

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


        _binding.bNavigate.setOnClickListener {
            findNavController().navigate(
                nav_graph.dest.catalog, bundleOf(
                    ARG_REFERENCE_ID to _binding.etArgs.text.toString()
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}