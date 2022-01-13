package ru.kvs.railways.rest.dto.route

import io.swagger.v3.oas.annotations.media.Schema

data class RouteDTO(
    @Schema(description = "Станции маршрута")
    var stations: List<RouteStationDTO>
)
