package com.moviles.taller2_moviles.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviles.taller2_moviles.data.database.entity.Family

@Composable
fun ListItem(
    family: Family,
    onClick: (ID: Int) -> Unit = {}
){
    Button(
        onClick = { onClick(family.id)},
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = CutCornerShape(0f),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color(0xFF617CCE),
            contentColor = Color.White
        )
    ){

        Row {
            Box(modifier = Modifier){
                Text(
                    text = "Familia: ${family.familyName}, Ubicacion: ${family.location}, Riesgo: ${family.risk}",
                )
            }
        }
    }
}