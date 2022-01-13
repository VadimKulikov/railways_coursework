package ru.kvs.railways.rest.dto

import io.swagger.v3.oas.annotations.media.Schema

data class TrainDTO(
    @Schema(description = "Номер поезда")
    var number: Int = 0,

    @Schema(description = "Идентификатор состава")
    var compositionId: Int = 0
)
