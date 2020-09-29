package com.hynes.james.fibtest.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.hynes.james.fibtest.R
import com.hynes.james.fibtest.databinding.SummaryFragmentBinding
import com.hynes.james.fibtest.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SummaryFragment : Fragment() {

    companion object {

        fun newInstance() = SummaryFragment()
    }

    private lateinit var binding: SummaryFragmentBinding
    private val viewModel: MainViewModel by sharedViewModel()
    private val adapter = SummaryAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        setupDataBinding(inflater, container)
        setupToolbar()
        setupRecyclerView()

        return binding.root
    }

    private fun setupDataBinding(inflater: LayoutInflater, container: ViewGroup?) {

        binding = DataBindingUtil.inflate(inflater, R.layout.summary_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupToolbar() {

        binding.summaryToolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setupRecyclerView() {

        binding.summaryRecyclerView.adapter = adapter
        binding.summaryRecyclerView.setHasFixedSize(true)
        binding.summaryRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        adapter.updateFibonacciCalculationResults(viewModel.fibonacciCalculationResults)
    }
}