package com.genesiseternity.daggerbottomsheetnav.di

import com.genesiseternity.daggerbottomsheetnav.presentation.FirstFragment
import com.genesiseternity.daggerbottomsheetnav.presentation.SecondBottomSheetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeFirstFragment(): FirstFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeSecondBottomSheetFragment(): SecondBottomSheetFragment
}