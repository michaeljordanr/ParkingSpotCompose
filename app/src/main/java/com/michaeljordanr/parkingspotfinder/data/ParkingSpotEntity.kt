package com.michaeljordanr.parkingspotfinder.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.michaeljordanr.parkingspotfinder.domain.model.ParkingSpot

@Entity
data class ParkingSpotEntity(
    val lat: Double,
    val lng: Double,
    @PrimaryKey val id: Int? = null
) {
    fun toParkingSpot(): ParkingSpot {
        return ParkingSpot(
            lat = lat,
            lng = lng,
            id = id
        )
    }
}
