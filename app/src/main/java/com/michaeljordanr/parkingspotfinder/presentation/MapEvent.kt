package com.michaeljordanr.parkingspotfinder.presentation

import com.google.android.gms.maps.model.LatLng
import com.michaeljordanr.parkingspotfinder.domain.model.ParkingSpot

sealed class MapEvent {
    object ToggleDifferentStyleMap: MapEvent()
    data class OnMapLongClick(val latLng: LatLng): MapEvent()
    data class OnInfoWindowsLongClick(val spot: ParkingSpot): MapEvent()
}
