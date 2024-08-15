package com.genesiseternity.daggerbottomsheetnav.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.genesiseternity.daggerbottomsheetnav.core.DaggerBottomSheetDialogFragment
import com.genesiseternity.daggerbottomsheetnav.databinding.FragmentSecondBottomSheetBinding
import com.genesiseternity.daggerbottomsheetnav.di.ViewModelProviderFactory
import com.genesiseternity.daggerbottomsheetnav.utils.NavConstants.KEY
import com.genesiseternity.daggerbottomsheetnav.utils.Operation
import kotlinx.coroutines.launch
import javax.inject.Inject

class SecondBottomSheetFragment : DaggerBottomSheetDialogFragment() {

    private var _binding: FragmentSecondBottomSheetBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var providerFactory: ViewModelProviderFactory

    private val viewModel: SecondBottomSheetFragmentViewModel by lazy {
        ViewModelProvider(this, providerFactory)[SecondBottomSheetFragmentViewModel::class.java]
    }

    private val args by navArgs<SecondBottomSheetFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArgs()
        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.btnAddValue.setOnClickListener { viewModel.updateValue(Operation.ADD) }
        binding.btnReduceValue.setOnClickListener { viewModel.updateValue(Operation.REDUCE) }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.totalValue.collect { value ->
                    binding.tvTotalValue.text = value.toString()
                    sendDataToParentFragment(value.toString())
                }
            }
        }
    }

    private fun initArgs() {
        args.value?.let {
            binding.tvTotalValue.text = it
            viewModel.presetValue(it.toInt())
        }
    }

    private fun sendDataToParentFragment(result: String) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(KEY, result)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}