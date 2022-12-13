package com.unfunny.tip.presentation

sealed class UIEvent {
    data class onSplitChange(val splitAction: SplitAction) : UIEvent()
    object onGetTotal : UIEvent()
}

enum class SplitAction {
    INCREASE,
    DECREASE
}
