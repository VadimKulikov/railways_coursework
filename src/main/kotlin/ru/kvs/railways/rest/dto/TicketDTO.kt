package ru.kvs.railways.rest.dto

import ru.kvs.railways.model.seat.SeatId
import ru.kvs.railways.module.ticket.validation.StationOnRouteValidation

@StationOnRouteValidation
data class TicketDTO(
    var passengerId: Long = 0,
    var stationToId: Long = 0,
    var stationFromId: Long = 0,
    var tripId: Long,
    var seatId: SeatId,
    var carriageId: Int,
    var cost: Long
)
