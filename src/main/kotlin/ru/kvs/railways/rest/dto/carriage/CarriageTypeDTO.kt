package ru.kvs.railways.rest.dto.carriage

data class CarriageTypeDTO(
    var name: String = "",
    var seats: List<SeatDTO> = listOf()
)
