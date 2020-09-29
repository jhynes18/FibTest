package com.hynes.james.fibtest.ui.input

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.hynes.james.fibtest.BR
import com.hynes.james.fibtest.R
import com.hynes.james.fibtest.databinding.InputFragmentBinding
import com.hynes.james.fibtest.ui.MainViewModel
import com.hynes.james.fibtest.ui.summary.SummaryFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class InputFragment : Fragment() {

    companion object {

        fun newInstance() = InputFragment()
    }

    private lateinit var binding: InputFragmentBinding
    private val viewModel: MainViewModel by sharedViewModel()
    private val adapter = InputAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        setupDataBinding(inflater, container)
        setupToolbar()
        setupRecyclerView()
        setupInputEditText()

        return binding.root
    }

    private fun setupDataBinding(inflater: LayoutInflater, container: ViewGroup?) {

        binding = DataBindingUtil.inflate(inflater, R.layout.input_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
    }

    private fun setupToolbar() {

        binding.inputToolbar.setOnMenuItemClickListener {

            when (it.itemId) {
                R.id.menu_item_summary -> launchSummaryFragment()
            }

            true
        }
    }

    private fun setupRecyclerView() {

        binding.inputRecyclerView.adapter = adapter
        binding.inputRecyclerView.setHasFixedSize(true)
        binding.inputRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        viewModel.fibonacciSequence.observe(viewLifecycleOwner, {
            adapter.updateFibonacciResults(it)
        })
    }

    private fun setupInputEditText() {

        binding.inputTextInputEditText.setOnEditorActionListener { v, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_GO) {
                viewModel.updateInputValue(v.text.toString().toInt())
                hideSoftKeyboard()
            }

            true
        }
    }

    private fun hideSoftKeyboard() {

        if (requireActivity().currentFocus != null) {

            val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        }
    }

    private fun launchSummaryFragment() {

        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainerView, SummaryFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}