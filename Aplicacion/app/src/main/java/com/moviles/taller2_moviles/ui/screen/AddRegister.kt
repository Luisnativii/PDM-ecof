package com.moviles.taller2_moviles.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.moviles.taller2_moviles.MainViewModel
import com.moviles.taller2_moviles.UIState
import com.moviles.taller2_moviles.data.EnumDatas.HouseTypeEnum
import com.moviles.taller2_moviles.data.EnumDatas.RiskEnum
import com.moviles.taller2_moviles.data.database.entity.Family
import com.moviles.taller2_moviles.data.familyMemberList
import com.moviles.taller2_moviles.ui.component.DynamicDropdownMenu
import com.moviles.taller2_moviles.ui.component.RegisterFamilyMember
import com.moviles.taller2_moviles.ui.component.TopBar
import com.moviles.taller2_moviles.utils.LocationService
import kotlinx.coroutines.launch

@Composable
fun AddRegister(
    viewModel: MainViewModel,
    navController: NavController
) {

    //Estado que almacena la informacion de familia
    var familyData by remember {
        mutableStateOf(
            Family()
        )
    }

    var expanded by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val locationService = LocationService()
    var latitude by remember { mutableStateOf<Double?>(null) }
    var longitude by remember { mutableStateOf<Double?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val texto = remember { mutableStateOf("Agregar Ubicacion") }


    //Estado para controlar el estado de la interfaz
    val addScreenState = viewModel.uiState.collectAsState()

    if (addScreenState.value is UIState.Error) {
        val message = (addScreenState.value as UIState.Error).msg
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        viewModel.setStateToReady()
    }
    if (addScreenState.value is UIState.Success) {
        val message = (addScreenState.value as UIState.Success).msg
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        viewModel.setStateToReady()
        navController.popBackStack()
    }



    Scaffold(
        topBar = {
            TopBar(
                title = "Add Register",
                navController = navController,
                onSaveEvent = {

                    viewModel.insertFamily(familyData, familyMemberList)
                }
            )
        },
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {

            //============= Informacion Familiar =============

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable(onClick = { expanded = !expanded }),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Informacion Familiar",
                    modifier = Modifier
                        .padding(20.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                if (expanded) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Close Add Member Menu",
                        tint = Color.Red,
                        modifier = Modifier
                            .padding(end = 20.dp, bottom = 10.dp, top = 10.dp)
                            .shadow(2.dp)
                            .size(30.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Open Add Member Menu",
                        tint = Color.Green,
                        modifier = Modifier
                            .padding(end = 20.dp, bottom = 10.dp, top = 10.dp)
                            .shadow(2.dp)
                            .size(30.dp)
                    )
                }

            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(650.dp)
            ) {


                //El apellido sacarlo del 3 nombre de una persona de la familia?
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp, top = 20.dp),
                    value = familyData.familyName,
                    onValueChange = { familyData = familyData.copy(familyName = it) },
                    label = { Text(text = "Apellido Familiar") }
                )

                Text(
                    text = "Ubicacion",
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp),
                    value = familyData.location,
                    onValueChange = { familyData = familyData.copy(location = it) },
                    label = { Text(text = "Comunidad o Canton") }
                )

                Text(
                    text = "Nivel de riesgo",
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                )
                DynamicDropdownMenu(
                    enumValues = RiskEnum.entries.toTypedArray(),
                    label = "Selecionar Riesgo",
                    initialValue = "Nivel de riesgo",
                    onItemSelected = { selectedRisk ->
                        familyData = familyData.copy(risk = selectedRisk.toString())
                    }
                )


                Text(
                    text = "Tipo de casa",
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                )
                DynamicDropdownMenu(
                    enumValues = HouseTypeEnum.entries.toTypedArray(),
                    label = "Tipo de casa",
                    initialValue = "Tipo de casa",
                    onItemSelected = { selectedHouseType ->
                        familyData = familyData.copy(houseType = selectedHouseType.toString())
                    }
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
                    onClick = {
                        coroutineScope.launch {
                            val location = locationService.getUserLocation(context)
                            latitude = location?.latitude
                            longitude = location?.longitude
                            if (latitude != null && longitude != null) {
                                Log.d("Location", "Latitude: $latitude, Longitude: $longitude")
                            } else {
                                Log.d("Location", "Failed to get location")
                            }
                            familyData = familyData.copy(geographicLocation = "Latitud: $latitude | Longitud: $longitude")
                        }

                        println(latitude)
                        println(longitude)


                    }
                ) {
                    Text(text = "Agregar ubicacion")
                }

                Button(
                    onClick = { expanded = false},
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 20.dp)
                ) {
                    Text(text = "Aceptar")
                }

            }

//            ======================== Miembros de familia ========================


            Divider(
                thickness = 3.dp,
                color = Color(0xFF11358F),
                modifier = Modifier.padding(10.dp)
            )

            Text(
                text = "Miembros Familiares",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Miembros de familia
            RegisterFamilyMember(
                onAddMember = { familyMemberTemp ->
                    familyMemberList.add(familyMemberTemp)
                }
            )


            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentHeight()

            ) {
                items(familyMemberList) { FamMember ->
                    Card (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                    ){

                        Text(
                            text = "Nombre: ${FamMember.fullName}",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                            ),
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = "DUI: ${FamMember.DUI}",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = "Puede leer y escribir: ${FamMember.canReadWrite}",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = "Fecha de nacimiento: ${FamMember.dateBirth}",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.padding(10.dp)
                        )
                    }

                    Divider(thickness = 1.dp, color = Color(0xFF11358F))
                }
            }
        }


    }
}

