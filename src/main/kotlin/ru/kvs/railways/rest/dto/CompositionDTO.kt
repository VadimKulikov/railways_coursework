package ru.kvs.railways.rest.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.kvs.railways.rest.dto.carriage.CarriageDTO

data class CompositionDTO(
    @Schema(description = "Вагоны состава")
    var carriages: List<CarriageDTO> = listOf()
)
