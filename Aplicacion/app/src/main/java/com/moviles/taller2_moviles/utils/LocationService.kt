package com.moviles.taller2_moviles.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LocationService {

    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("MissingPermission")
    suspend fun getUserLocation(context: Context): Location? {

        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        val isUserPermissionGranted = true // Aquí deberías comprobar los permisos realmente
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!isUserPermissionGranted || !isGpsEnabled) {
            return null
        }

        return suspendCancellableCoroutine { cont ->
            fusedLocationProviderClient.lastLocation.apply {
                addOnSuccessListener { location ->
                    cont.resume(location)
                }
                addOnFailureListener { exception ->
                    cont.resumeWithException(exception)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}
