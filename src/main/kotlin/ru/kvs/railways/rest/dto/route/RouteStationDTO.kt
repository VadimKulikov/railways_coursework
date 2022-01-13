package ru.kvs.railways.rest.dto.route

import io.swagger.v3.oas.annotations.media.Schema
import ru.kvs.railways.model.route.CustomDuration

data class RouteStationDTO(
    @Schema(description = "Идентификатор станции")
    var stationId: Long,

    @Schema(description = "Время прибытия")
    var arrivalPeriod: CustomDuration,

    @Schema(description = "Время остановки")
    var stop: CustomDuration
)
