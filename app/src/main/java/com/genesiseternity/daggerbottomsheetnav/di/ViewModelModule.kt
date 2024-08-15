package com.genesiseternity.daggerbottomsheetnav.di

import androidx.lifecycle.ViewModel
import com.genesiseternity.daggerbottomsheetnav.presentation.FirstFragmentViewModel
import com.genesiseternity.daggerbottomsheetnav.presentation.SecondBottomSheetFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FirstFragmentViewModel::class)
    abstract fun bindFirstFragmentViewModel(viewModel: FirstFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SecondBottomSheetFragmentViewModel::class)
    abstract fun bindSecondBottomSheetFragmentViewModel(viewModel: SecondBottomSheetFragmentViewModel): ViewModel
}