package com.moviles.taller2_moviles.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moviles.taller2_moviles.data.EnumDatas.ScholarityEnum
import com.moviles.taller2_moviles.data.database.entity.FamilyMember

@Composable
fun RegisterFamilyMember(
    onAddMember: (FamilyMember) -> Unit
) {

    val expanded = remember { mutableStateOf(false) }
    var canReadWrite = remember { mutableStateOf(false) }
    val isMinor = remember { mutableStateOf(false) }

    //Guardando datos de la persona
    var familyMemberData by remember {
        mutableStateOf(
            FamilyMember()
        )
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
    )
    {
        Button(
            onClick = {
                expanded.value = true
                familyMemberData = FamilyMember()
                isMinor.value = false
            },
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Text(text = "Agregar Miembro Familiar")
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { /* expand = false */ },
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)

        ) {
            Column {

                Row {
                    Text(
                        text = "Informacion Personal",
                        modifier = Modifier
                            .padding(20.dp),
                        fontSize = 20.sp,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Add Member Menu",
                        tint = Color.Red,
                        modifier = Modifier
                            .padding(end = 20.dp, bottom = 10.dp, top = 10.dp)
                            .clickable { expanded.value = false }
                            .shadow(2.dp)
                            .size(30.dp)
                    )
                }


                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                    value = familyMemberData.fullName,
                    onValueChange = { familyMemberData = familyMemberData.copy(fullName = it) },
                    label = { Text(text = "Nombre completo") },
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                    value = familyMemberData.DUI,
                    onValueChange = { familyMemberData = familyMemberData.copy(DUI = it) },
                    label = { Text(text = "Numero de DUI (Sin guion)") },
                    enabled = !isMinor.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.End),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Es menor de edad?")
                    Checkbox(checked = isMinor.value, onCheckedChange = {
                        isMinor.value = it
                        if (it) {
                            familyMemberData = familyMemberData.copy(DUI = "Menor de edad")
                        } else {
                            familyMemberData = familyMemberData.copy(DUI = "")
                        }
                    })
                }


                Row(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Puede leer y escribir")
                    Spacer(modifier = Modifier.weight(1f))
                    Checkbox(
                        checked = canReadWrite.value,
                        onCheckedChange = { canReadWrite.value = it }
                    )
                }

                DynamicDropdownMenu(
                    enumValues = ScholarityEnum.entries.toTypedArray(),
                    label = "Nivel de educacion",
                    initialValue = "Nivel de educacion",
                    onItemSelected = { selectedScholarity ->
                        familyMemberData =
                            familyMemberData.copy(scholarity = selectedScholarity.toString())
                    }
                )

                Calendary(
                    onDateSelected = { date ->
                        familyMemberData = familyMemberData.copy(dateBirth = date)
                    }
                )



                Row {
                    Button(onClick = {
                        expanded.value = false
                    }) {
                        Text(text = "Cancelar")
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(onClick = {
                        onAddMember(familyMemberData)
                        expanded.value = false
                    }) {
                        Text(text = "Agregar miembro")
                    }
                }

            }
        }

    }
}