package com.guideline.daggerbottomsheetnav.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class FirstFragmentViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {
    private val TAG = this::class.java.simpleName

    private val _value = MutableStateFlow<String>("0")
    val value: StateFlow<String> get() = _value

    fun updateValue(newValue: String) {
        _value.value = newValue
    }
}