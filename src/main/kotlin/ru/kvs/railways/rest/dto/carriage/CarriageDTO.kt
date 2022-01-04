package ru.kvs.railways.rest.dto.carriage

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "DTO вагона")
data class CarriageDTO(

    @Schema(description = "Идентификатор типа вагона")
    var typeId: Int = 0,

    @Schema(description = "Номер вагона")
    var number: Int = 0
)
