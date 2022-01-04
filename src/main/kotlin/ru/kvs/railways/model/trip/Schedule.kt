package ru.kvs.railways.model.trip

import java.time.LocalDateTime

data class Schedule(
    var stationName: String = "",
    var arrivalTime: LocalDateTime = LocalDateTime.now(),
    var departureTime: LocalDateTime = LocalDateTime.now()
)
