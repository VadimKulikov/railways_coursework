package ru.kvs.railways.rest.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.kvs.railways.model.route.CustomDuration
import ru.kvs.railways.module.trip.validation.TrainValidation
import java.time.LocalDate
import java.time.LocalDateTime

@TrainValidation
data class TripDTO(
    @Schema(description = "Идентификатор поезда")
    var trainId: Int = 0,

    @Schema(description = "Идентификатор маршрута")
    var routeId: Long = 0,

    @Schema(description = "Время отправления")
    var departureTime: LocalDateTime = LocalDateTime.now()
)

data class PeriodicTripDTO(
    var trainId: Int = 0,
    var routeId: Long = 0,
    var firstTripDate: LocalDateTime = LocalDateTime.now(),
    var interval: CustomDuration,
    var finishDate: LocalDate = LocalDate.now()
)
