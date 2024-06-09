package com.moviles.taller2_moviles.ui.screen

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.moviles.taller2_moviles.MainViewModel
import com.moviles.taller2_moviles.data.database.entity.FamilyMember
import com.moviles.taller2_moviles.ui.component.TopBar
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun RegisterDetails(
    viewModels: MainViewModel,
    navController: NavController,
    familyId: Int
) {

    viewModels.selectFamilyById(familyId)
    val family = viewModels.family.collectAsState()

    viewModels.selectAllFamilyMembers(familyId)
    val familyMembers = viewModels.famiMembers.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                title = "Register Details",
                navController = navController
            )
        },
    ) { paddingValues ->

        if (family.value == null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {
                Text(
                    text = "Family not found",
                    modifier = Modifier.fillMaxWidth().padding(10.dp),

                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Back to home")
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {
                Text(
                    text = "Family Name: ${family.value.familyName}",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,

                    ),
                    modifier = Modifier.padding(10.dp),
                )
                Text(
                    text = "Location: ${family.value.location}",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier.padding(10.dp),
                )


                Text(
                    text = "House Type: ${family.value.houseType}",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier.padding(10.dp),
                )
                Text(
                    text = "risk: ${family.value.risk}",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier.padding(10.dp),
                )

                Divider(
                    thickness = 2.dp,
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp),
                )

                val geographicLocation = family.value.geographicLocation
                val coordinates = geographicLocation.split("|")

                if (coordinates.size == 2) {
                    val latitudeString = coordinates[0].split(":")[1].trim()
                    val longitudeString = coordinates[1].split(":")[1].trim()

                    val latitude = latitudeString.toDoubleOrNull()
                    val longitude = longitudeString.toDoubleOrNull()

                    if (latitude != null && longitude != null) {
                        MapView(latitude = latitude, longitude = longitude )
                        println("Latitude: $latitude, Longitude: $longitude")
                    } else {
                        // Manejar el caso en el que 'latitudeString' o 'longitudeString' no puedan convertirse en Double
                    }
                } else {
                    // Manejar el caso en el que 'geographicLocation' no tenga el formato correcto
                }

                Divider(
                    thickness = 2.dp,
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp),
                )

                Text(
                    text = "Family Members",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(10.dp),
                )

                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(familyMembers.value) { member ->
                        MemberItem(member = member)
                    }
                }
            }
        }
    }
}

@Composable
fun MemberItem(member: FamilyMember) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Full Name: ${member.fullName}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Text(
            text = "DUI: ${member.DUI}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Text(
            text = "Date of Birth: ${member.dateBirth}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Text(
            text = "Can Read/Write: ${if (member.canReadWrite) "Yes" else "No"}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Text(
            text = "Scholarity: ${member.scholarity}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun MapView(latitude: Double, longitude: Double) {
    val position = LatLng(latitude, longitude)
    val zoomLevel = 15.0f // Ajusta este valor para cambiar el nivel de zoom

    AndroidView({ context ->
        com.google.android.gms.maps.MapView(context).apply {
            onCreate(Bundle())
            getMapAsync { googleMap ->
                googleMap.addMarker(
                    MarkerOptions()
                        .position(position)
                        .title("Marker Title")
                )
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, zoomLevel))
            }
        }
    }, modifier = Modifier.fillMaxWidth().height(200.dp)) // Ajusta estos valores para cambiar el tamaño del mapa
}


@Preview(showBackground = true)
@Composable
fun RegisterDetailsPreview() {
    RegisterDetails(
        viewModels = MainViewModel(),
        navController = rememberNavController(),
        familyId = 1
    )
}
