package com.moviles.taller2_moviles.ui.navigation

sealed class ScreenRoute( var route : String) {
    object Home: ScreenRoute("home")
    object AddRegister: ScreenRoute("addRegister")
    object RegisterDetails: ScreenRoute("registerDetails/{familyId}")
}