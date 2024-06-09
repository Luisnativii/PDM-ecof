package com.moviles.taller2_moviles.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize


@Composable
fun <T : Enum<T>> DynamicDropdownMenu(
    enumValues: Array<T>,
    label: String,
    initialValue: String,
    onItemSelected: (T) -> Unit = {}
) {
    var mExpanded by remember { mutableStateOf(false) }
    var mSelectedText by remember { mutableStateOf(initialValue) }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (mExpanded)
        Icons.Default.KeyboardArrowUp
    else
        Icons.Default.KeyboardArrowDown

    Column(
        Modifier
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
            .border(width = 1.dp, color =  Color.Gray, shape = RoundedCornerShape(5.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { mExpanded = !mExpanded }
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                }
                .padding(16.dp)
        ) {
            Text(
                text = mSelectedText,
                modifier = Modifier
                    .weight(1f)
            )
            Icon(icon, contentDescription = "Toggle Dropdown")
        }

        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            enumValues.forEach { value ->
                DropdownMenuItem(
                    text = {
                        Text(text = value.name)
                    },
                    onClick = {
                    mSelectedText = value.name
                    mExpanded = false
                    onItemSelected(value)
                })
            }
        }
    }
}