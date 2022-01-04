package ru.kvs.railways.rest.dto.carriage

import ru.kvs.railways.model.seat.ESeatType

data class SeatDTO(
    var seatNumber: Int = 0,
    var seatType: ESeatType = ESeatType.LOWER
)
