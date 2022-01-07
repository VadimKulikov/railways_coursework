package ru.kvs.railways.rest.dto

import ru.kvs.railways.model.route.CustomDuration
import java.time.LocalDate
import java.time.LocalDateTime

data class TripDTO(
    var trainId: Int = 0,
    var routeId: Long = 0,
    var departureTime: LocalDateTime = LocalDateTime.now()
)

data class PeriodicTripDTO(
    var trainId: Int = 0,
    var routeId: Long = 0,
    var firstTripDate: LocalDateTime = LocalDateTime.now(),
    var interval: CustomDuration,
    var finishDate: LocalDate = LocalDate.now()
)
