package com.unfunny.tip.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unfunny.tip.domain.use_cases.TipUseCases
import com.unfunny.tip.utils.extensions.parseInput
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(private val useCases: TipUseCases) : ViewModel() {

    private var savePercentageJob: Job? = null

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    private val totalAmountFlow = MutableSharedFlow<Double>()

    init {
        viewModelScope.launch {

            val result = useCases.getPercentage().first()
            result?.let { double -> _state.update { it.copy(tipPercentage = double.toString()) } }

            totalAmountFlow.collect { total -> _state.update { it.copy(totalAmount = total) } }
        }
    }

    fun onUIEvent(action: UIEvent) {
        when (action) {
            is UIEvent.onSplitChange -> onSplitChange(action.splitAction)
            is UIEvent.onGetTotal -> getTotal()
        }
    }

    fun setBill(amount: String) {
        amount.parseInput { result -> _state.update { it.copy(billAmount = result) } }
    }

    fun setTip(percentage: String) {
        percentage.parseInput { result -> _state.update { it.copy(tipPercentage = result) } }
    }

    private fun onSplitChange(action: SplitAction) {
        when (action) {
            SplitAction.INCREASE -> {
                _state.update { it.copy(split = state.value.split + 1) }
            }
            SplitAction.DECREASE -> {
                _state.update { it.copy(split = state.value.split - 1) }
            }
        }
    }

    fun getTotal() {
        savePercentageJob?.cancel()
        savePercentageJob =
            viewModelScope.launch {
                val result =
                    useCases.getTotal(
                        billAmount = state.value.billAmount.toDoubleOrNull() ?: 0.00,
                        tipPercentage = state.value.tipPercentage.toDoubleOrNull() ?: 0.00,
                        splits = state.value.split
                    )
                totalAmountFlow.emit(result)
                state.value.tipPercentage.let { useCases.savePercentage(it.toDoubleOrNull() ?: 0.0) }
            }
    }
}
