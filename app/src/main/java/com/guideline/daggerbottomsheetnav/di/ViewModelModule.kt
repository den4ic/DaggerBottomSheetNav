package com.guideline.daggerbottomsheetnav.di

import androidx.lifecycle.ViewModel
import com.guideline.daggerbottomsheetnav.presentation.FirstFragmentViewModel
import com.guideline.daggerbottomsheetnav.presentation.SecondBottomSheetFragmentViewModel
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