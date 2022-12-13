package com.unfunny.tip.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.unfunny.tip.presentation.theme.splitTitle
import com.unfunny.tip.presentation.theme.splitTotal

@Composable
fun BillCard(
    modifier: Modifier,
    total: String = "0.00",
    splitValue: String = "1",
    onMinus: () -> Unit,
    onPlus: () -> Unit,
) {
    val smallMargin = remember { 16.dp }
    val normalMargin = remember { 22.dp }
    ConstraintLayout(
        modifier
            .padding(normalMargin)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        val (splitTitle, splitTotal, splitTotalTitle, splitter) = createRefs()

        Text(
            text = "Split",
            modifier =
                Modifier.constrainAs(splitTitle) {
                    start.linkTo(parent.start, smallMargin)
                    top.linkTo(parent.top, smallMargin)
                    width = Dimension.fillToConstraints
                },
            style = MaterialTheme.typography.splitTitle
        )

        Text(
            text = total,
            modifier =
                Modifier.constrainAs(splitTotal) {
                    start.linkTo(splitter.end)
                    end.linkTo(parent.end, smallMargin)
                    top.linkTo(splitTotalTitle.bottom, smallMargin)
                    bottom.linkTo(parent.bottom, smallMargin)
                    width = Dimension.fillToConstraints
                },
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.splitTotal
        )

        Text(
            text = "Split Total",
            modifier =
                Modifier.padding(start = smallMargin).constrainAs(splitTotalTitle) {
                    start.linkTo(splitTitle.start)
                    end.linkTo(parent.end, smallMargin)
                    top.linkTo(splitTitle.top)
                    bottom.linkTo(splitTitle.bottom)
                    width = Dimension.fillToConstraints
                },
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.splitTitle
        )
        Splitter(
            modifier = Modifier.constrainAs(splitter){
                      start.linkTo(splitTitle.start)
                    top.linkTo(splitTitle.bottom, smallMargin)
                bottom.linkTo(splitTotal.bottom)
            },
            splitValue = splitValue,
            onMinus = onMinus,
            onPlus = onPlus
        )
    }
}

@Preview @Composable private fun PreviewBillCard() {}
