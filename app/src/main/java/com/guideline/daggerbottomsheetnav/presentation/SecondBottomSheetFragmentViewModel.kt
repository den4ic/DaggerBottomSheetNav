package com.guideline.daggerbottomsheetnav.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import com.guideline.daggerbottomsheetnav.utils.Operation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SecondBottomSheetFragmentViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {
    private val TAG = this::class.java.simpleName

    private val _totalValue = MutableStateFlow(0)
    val totalValue: StateFlow<Int> get() = _totalValue

    fun presetValue(value: Int) {
        _totalValue.value = value
    }

    fun updateValue(operation: Operation) {
        when (operation) {
            Operation.ADD -> _totalValue.value++
            Operation.REDUCE -> _totalValue.value--
        }
    }
}