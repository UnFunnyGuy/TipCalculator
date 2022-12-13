package com.unfunny.tip.presentation.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.unfunny.tip.presentation.theme.splitTotal
import com.unfunny.tip.utils.extensions.iconBg

@Composable
fun Splitter(
    modifier: Modifier,
    splitValue: String = "1",
    onMinus: () -> Unit,
    onPlus: () -> Unit,
) {
    val iconsSize = remember { 26.dp }
    val margin = remember { 12.dp }
    val isMinusEnabled by remember(splitValue) { derivedStateOf { splitValue != "1" } }
    ConstraintLayout(modifier) {
        val (minusIcon, split, plusIcon) = createRefs()


        IconButton(
            modifier = Modifier.iconBg()
                .size(iconsSize)
                .constrainAs(minusIcon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            enabled = isMinusEnabled,
            onClick = onMinus
        ) {
            Icon(
                imageVector = Icons.Rounded.Remove,
                contentDescription = "minus_split"
            )
        }

        Text(
            text = splitValue,
            modifier =
            Modifier.constrainAs(split) {
                    start.linkTo(minusIcon.end, margin)
                    top.linkTo(minusIcon.top)
                    bottom.linkTo(minusIcon.bottom)
                width  = Dimension.value(54.dp)
                },
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.splitTotal
        )

        IconButton(
            modifier = Modifier.iconBg()
                .size(iconsSize)
                .constrainAs(plusIcon) {
                    start.linkTo(split.end, margin)
                    top.linkTo(minusIcon.top)
                    bottom.linkTo(minusIcon.bottom)
                },
            onClick = onPlus
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "plus_split"
            )
        }


    }
}
