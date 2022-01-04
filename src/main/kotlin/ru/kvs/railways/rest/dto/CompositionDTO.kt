package ru.kvs.railways.rest.dto

import ru.kvs.railways.rest.dto.carriage.CarriageDTO

data class CompositionDTO(
    var carriages: List<CarriageDTO> = listOf()
)
