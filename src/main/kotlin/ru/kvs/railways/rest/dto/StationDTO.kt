package ru.kvs.railways.rest.dto

import io.swagger.v3.oas.annotations.media.Schema

data class StationDTO(
    @Schema(description = "Название станции")
    var name: String = ""
)
