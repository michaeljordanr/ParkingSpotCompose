package com.michaeljordanr.parkingspotfinder.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    viewModel: MapsViewModel = viewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MapEvent.ToggleDifferentStyleMap)
            }) {
                Icon(
                    imageVector = if (viewModel.state.isFalloutMap) {
                        Icons.Default.ToggleOff
                    } else {
                        Icons.Default.ToggleOn
                    }, contentDescription = "Toggle Fallout map"
                )
            }
        }
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
            onMapLongClick = { latLng ->
                viewModel.onEvent(MapEvent.OnMapLongClick(latLng))
            }
        ) {
            viewModel.state.parkingSpots.forEach { spot ->
                Marker(
                    position = LatLng(spot.lat, spot.lng),
                    title = "Parking spot (${spot.lat}, ${spot.lng})",
                    snippet = "Long click to delete",
                    onInfoWindowLongClick = {
                        viewModel.onEvent(MapEvent.OnInfoWindowsLongClick(spot))
                    },
                    onClick =  { marker ->
                        marker.showInfoWindow()
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_BLUE
                    )
                )
            }
        }
    }
}