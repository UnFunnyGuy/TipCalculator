package com.unfunny.tip.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.unfunny.tip.presentation.composables.BillCard
import com.unfunny.tip.presentation.composables.FieldColumns

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    MainScreenContents(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        billStateVsEvent = StateVsEvent(state.billAmount, viewModel::setBill),
        tipStateVsEvent = StateVsEvent(state.tipPercentage, viewModel::setTip),
        onClick = { viewModel.onUIEvent(UIEvent.onGetTotal) },
        total = state.totalAmount.toString(),
        split = state.split.toString(),
        onPlus = { viewModel.onUIEvent(UIEvent.onSplitChange(SplitAction.INCREASE)) },
        onMinus = { viewModel.onUIEvent(UIEvent.onSplitChange(SplitAction.DECREASE)) },
    )

    LaunchedEffect(state){
        viewModel.getTotal()
    }
}

@Composable
private fun MainScreenContents(
    modifier: Modifier,
    billStateVsEvent: StateVsEvent,
    tipStateVsEvent: StateVsEvent,
    onClick: () -> Unit,
    total: String,
    split: String,
    onPlus: () -> Unit,
    onMinus: () -> Unit
) {
    ConstraintLayout(modifier.fillMaxSize().imePadding()) {
        val (fieldView, splitCard) = createRefs()

        FieldColumns(
            modifier =
                Modifier.constrainAs(fieldView) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(splitCard.top)
                    width = Dimension.fillToConstraints
                },
            billStateVsEvent = billStateVsEvent,
            tipStateVsEvent = tipStateVsEvent,
            onAction = onClick
        )

        BillCard(
            modifier =
                Modifier.constrainAs(splitCard) {
                    start.linkTo(fieldView.start)
                    end.linkTo(fieldView.end)
                    bottom.linkTo(parent.bottom, 40.dp)
                    width = Dimension.fillToConstraints
                },
            total = total,
            splitValue = split,
            onMinus = onMinus,
            onPlus = onPlus
        )
    }
}
