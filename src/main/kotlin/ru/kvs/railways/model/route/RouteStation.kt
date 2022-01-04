package ru.kvs.railways.model.route

data class RouteStation(
    val stationName: String,
    val arrivalPeriod: CustomDuration,
    val stop: CustomDuration
)
