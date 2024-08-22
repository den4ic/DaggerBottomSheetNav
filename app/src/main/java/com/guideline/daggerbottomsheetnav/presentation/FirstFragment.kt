package com.guideline.daggerbottomsheetnav.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.guideline.daggerbottomsheetnav.R
import com.guideline.daggerbottomsheetnav.databinding.FragmentFirstBinding
import com.guideline.daggerbottomsheetnav.di.ViewModelProviderFactory
import com.guideline.daggerbottomsheetnav.utils.NavConstants.KEY
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirstFragment : DaggerFragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var providerFactory: ViewModelProviderFactory

    private val viewModel: FirstFragmentViewModel by lazy {
        ViewModelProvider(this, providerFactory)[FirstFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeBackStack()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.btnOpenBottomFragment.setOnClickListener { openBottomFragment() }
    }

    private fun openBottomFragment() {
        val navController = findNavController()
        val currentFragmentId = navController.currentDestination?.id
        if (currentFragmentId == R.id.firstFragment) {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondBottomSheetFragment(
                value = binding.tvValue.text.toString()
            )
            navController.navigate(action)
        }
    }

    private fun observeBackStack() {
        lifecycleScope.launch {
            findNavController().currentBackStackEntryFlow
                .distinctUntilChanged()
                .collect { backStackEntry ->
                    backStackEntry.savedStateHandle.getStateFlow(KEY, "")
                        .filter { it.isNotEmpty() }
                        .collect { value ->
                            viewModel.updateValue(value)
                        }
                }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.value.collect { value ->
                    binding.tvValue.text = value
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}