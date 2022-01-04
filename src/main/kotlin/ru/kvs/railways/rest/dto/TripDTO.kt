package ru.kvs.railways.rest.dto

import java.time.LocalDateTime

data class TripDTO(
    var trainId: Int = 0,
    var routeId: Long = 0,
    var departureTime: LocalDateTime = LocalDateTime.now()
)
