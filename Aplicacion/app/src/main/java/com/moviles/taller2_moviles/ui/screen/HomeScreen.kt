package com.moviles.taller2_moviles.ui.screen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.moviles.taller2_moviles.MainViewModel
import com.moviles.taller2_moviles.UIState
import com.moviles.taller2_moviles.data.api.FamilyApi
import com.moviles.taller2_moviles.data.api.MembersApi
import com.moviles.taller2_moviles.ui.component.ListItem
import com.moviles.taller2_moviles.ui.component.TopBar
import com.moviles.taller2_moviles.ui.navigation.ScreenRoute

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    navController: NavController
) {


    //Estado para controlar el estado de la interfaz
    val addScreenState = viewModel.uiState.collectAsState()
    if (addScreenState.value is UIState.Success){
        val message = (addScreenState.value as UIState.Success).msg
        Toast.makeText(LocalContext.current, "Success send to API", Toast.LENGTH_SHORT).show()
        viewModel.setStateToReady()
        navController.popBackStack()
    }

    viewModel.selectAllFamilies()
    val famiList = viewModel.familiesGetted.collectAsState()



    Scaffold(
        topBar = {
            TopBar(
                title = "Home",
                navController = navController,
                OnSentApi = { viewModel.insertApiFamily() }

            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(ScreenRoute.AddRegister.route) }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Lista de familias inscritas",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                items(famiList.value) { family ->
                    ListItem(
                        family = family,
                        onClick = { idSelectedFamily ->
                            navController.navigate("registerDetails/$idSelectedFamily")
                            Log.i("Family", idSelectedFamily.toString())
                        }
                    )
                }
            }

        }
    }
}


