package com.unfunny.tip.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.unfunny.tip.presentation.StateVsEvent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ImmersiveTextField(
    modifier: Modifier = Modifier,
    statesVsEvent: StateVsEvent,
    hintText: String = "",
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    shape: Shape? = null,
    trailingIcon: ImageVector? = null,
    singleLine: Boolean = true,
    containerColor: Color? = null,
    keyboardActions: KeyboardActions? = null,
    keyboardOptions: KeyboardOptions? = null,
    focusDirection: FocusDirection = FocusDirection.Down,
    onAction: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current


    TextField(
        textStyle = textStyle,
        modifier = modifier,
        value = if (statesVsEvent.value.isBlank()) "" else statesVsEvent.value,
        onValueChange = statesVsEvent.onValueChange,
        placeholder = {
            Text(text = hintText, color = textStyle.color.copy(0.6f), style = textStyle)
        },
        shape = shape ?: RoundedCornerShape(14.dp),
        singleLine = singleLine,
        colors =
            TextFieldDefaults.textFieldColors(
                containerColor = containerColor
                        ?: MaterialTheme.colorScheme.surfaceVariant.copy(0.0f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
        keyboardActions = keyboardActions
                ?: KeyboardActions(
                    onSend = {
                        onAction()
                    },
                    onNext = { focusManager.moveFocus(focusDirection) }
                ),
        keyboardOptions = keyboardOptions
                ?: KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Send,
                    keyboardType = KeyboardType.Number
                ),
        trailingIcon = {
            if (trailingIcon != null) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.6f)
                )
            } else null
        }
    )


}
