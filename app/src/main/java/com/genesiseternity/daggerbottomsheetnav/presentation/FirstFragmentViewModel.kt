package com.genesiseternity.daggerbottomsheetnav.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class FirstFragmentViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {
    private val TAG = this::class.java.simpleName

    init {

    }
}