package com.unfunny.tip.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.unfunny.tip.R
import com.unfunny.tip.presentation.StateVsEvent
import com.unfunny.tip.presentation.components.ImmersiveTextField
import com.unfunny.tip.presentation.theme.fieldText
import com.unfunny.tip.presentation.theme.fieldTitle

@Composable
fun FieldColumns(
    modifier: Modifier,
    billStateVsEvent: StateVsEvent,
    tipStateVsEvent: StateVsEvent,
    onAction: () -> Unit
) {
    val fieldMargin = remember { 17.dp } // TODO: Use BasicTextField
    val smallMargin = remember { 6.dp }
    val normalMargin = remember { 17.dp }
    val mediumMargin = remember { 24.dp }
    val largeMargin = remember { 30.dp }



    val isPercentageEmpty by
        remember(tipStateVsEvent) { derivedStateOf { tipStateVsEvent.value.isBlank() } }

    val isBillAmountEmpty by
        remember(billStateVsEvent) { derivedStateOf { billStateVsEvent.value.isBlank() } }



    ConstraintLayout(modifier) {
        val (billField, billTitle, tipField, tipTitle) = createRefs()

        Text(
            text = "Bill Total",
            modifier =
                Modifier.padding(start = fieldMargin).constrainAs(billTitle) {
                    start.linkTo(parent.start, normalMargin)
                    top.linkTo(parent.top, normalMargin)
                    width = Dimension.fillToConstraints
                },
            style = MaterialTheme.typography.fieldTitle
        )

        ImmersiveTextField(
            modifier =
                Modifier.constrainAs(billField) {
                    start.linkTo(billTitle.start)
                    top.linkTo(billTitle.bottom, smallMargin)
                    end.linkTo(parent.end, normalMargin)
                    width = Dimension.fillToConstraints
                },
            statesVsEvent = billStateVsEvent,
            hintText = "0.00",
            textStyle = MaterialTheme.typography.fieldText,
            keyboardOptions =
                if (isPercentageEmpty || isBillAmountEmpty)
                    KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    )
                else null, // TODO: Check
            onAction = onAction
        )

        Text(
            text = "Tip",
            modifier =
                Modifier.padding(start = fieldMargin).constrainAs(tipTitle) {
                    start.linkTo(billField.start)
                    top.linkTo(billField.bottom, normalMargin)
                    width = Dimension.fillToConstraints
                },
            style = MaterialTheme.typography.fieldTitle
        )

        ImmersiveTextField(
            modifier =
                Modifier.constrainAs(tipField) {
                    start.linkTo(tipTitle.start)
                    top.linkTo(tipTitle.bottom, smallMargin)
                    end.linkTo(parent.end, normalMargin)
                    bottom.linkTo(parent.bottom, largeMargin)
                    width = Dimension.fillToConstraints
                },
            statesVsEvent = tipStateVsEvent,
            hintText = "0",
            textStyle = MaterialTheme.typography.fieldText,
            trailingIcon = ImageVector.vectorResource(R.drawable.percent_24),
            keyboardOptions =
                if (isPercentageEmpty || isBillAmountEmpty)
                    KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    )
                else null,
            focusDirection = FocusDirection.Up,
            onAction = onAction
        )
    }
}

@Preview
@Composable
private fun PreviewFieldColumns() {

    FieldColumns(Modifier.fillMaxWidth(), StateVsEvent("", {}), StateVsEvent("", {}), onAction = {})
}
