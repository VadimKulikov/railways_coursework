package ru.kvs.railways.rest.dto.route

import ru.kvs.railways.model.route.CustomDuration

data class RouteStationDTO(
    var stationId: Long,
    var arrivalPeriod: CustomDuration,
    var stop: CustomDuration
)
