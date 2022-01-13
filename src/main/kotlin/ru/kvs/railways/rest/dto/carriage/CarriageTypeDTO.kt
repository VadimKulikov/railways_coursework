package ru.kvs.railways.rest.dto.carriage

import io.swagger.v3.oas.annotations.media.Schema

data class CarriageTypeDTO(
    @Schema(description = "Название типа вагона")
    var name: String = "",

    @Schema(description = "Места в вагоне")
    var seats: List<SeatDTO> = listOf()
)
