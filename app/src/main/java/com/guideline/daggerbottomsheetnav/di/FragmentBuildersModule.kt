package com.guideline.daggerbottomsheetnav.di

import com.guideline.daggerbottomsheetnav.presentation.FirstFragment
import com.guideline.daggerbottomsheetnav.presentation.SecondBottomSheetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeFirstFragment(): FirstFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeSecondBottomSheetFragment(): SecondBottomSheetFragment
}