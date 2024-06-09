package com.moviles.taller2_moviles.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.moviles.taller2_moviles.ui.navigation.ScreenRoute

@Composable
fun TopBar(
    title: String,
    navController: NavController,
    onSaveEvent: () -> Unit = {},
    onDeleteEvent: () -> Unit = {},
    OnSentApi: () -> Unit = {}
) {
    //Ruta actual

    val currentRoute = navController.currentBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (currentRoute != ScreenRoute.Home.route) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
                    .clickable { navController.popBackStack() },
                tint = Color.White,
            )
        }

        Text(
            text = title,
            modifier = Modifier
                .padding(start = 8.dp),
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))

//        if (currentRoute == ScreenRoute.RegisterDetails.route) {
//            Button(
//                onClick = { onDeleteEvent() },
//                modifier = Modifier
//                    .wrapContentSize(),
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = "Delete"
//                )
//            }
//        }

        if ( currentRoute == ScreenRoute.Home.route){
            Button(
                onClick = {

                          OnSentApi()

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF31AADA)),
            ){
                Text(text = "Enviar Datos")
                println("Datos Almacenados" )
            }
        }

        if (currentRoute == ScreenRoute.AddRegister.route) {
            Button(
                onClick = { onSaveEvent() },
                modifier = Modifier
                    .wrapContentSize(),

            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Save"
                )
            }
        }

    }
}