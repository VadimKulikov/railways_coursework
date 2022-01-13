package ru.kvs.railways.rest.dto.carriage

import io.swagger.v3.oas.annotations.media.Schema
import ru.kvs.railways.model.seat.ESeatType

data class SeatDTO(

    @Schema(description = "Номер места")
    var seatNumber: Int = 0,

    @Schema(description = "Тип места")
    var seatType: ESeatType = ESeatType.LOWER
)
