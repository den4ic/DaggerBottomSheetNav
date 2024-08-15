package com.genesiseternity.daggerbottomsheetnav.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.genesiseternity.daggerbottomsheetnav.R
import com.genesiseternity.daggerbottomsheetnav.databinding.FragmentFirstBinding
import com.genesiseternity.daggerbottomsheetnav.di.ViewModelProviderFactory
import com.genesiseternity.daggerbottomsheetnav.utils.NavConstants.KEY
import dagger.android.support.DaggerFragment
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
    }

    private fun setupListeners() {
        binding.btnOpenBottomFragment.setOnClickListener { openBottomFragment() }
    }

    private fun observeBackStack() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(KEY)
            ?.observe(viewLifecycleOwner) { value ->
                binding.tvValue.text = value
            }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}