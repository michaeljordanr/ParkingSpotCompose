package com.michaeljordanr.parkingspotfinder.domain.model

import com.michaeljordanr.parkingspotfinder.data.ParkingSpotEntity

data class ParkingSpot(
    val lat: Double,
    val lng: Double,
    val id: Int? = null
) {
    fun toParkingSpotEntity(): ParkingSpotEntity {
        return ParkingSpotEntity(
            lat = lat,
            lng = lng,
            id = id
        )
    }
}