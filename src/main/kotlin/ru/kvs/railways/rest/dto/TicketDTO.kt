package ru.kvs.railways.rest.dto

import io.swagger.v3.oas.annotations.media.Schema
import ru.kvs.railways.model.seat.SeatId
import ru.kvs.railways.module.ticket.validation.StationOnRouteValidation
import ru.kvs.railways.module.ticket.validation.TicketAvailableValidation
import ru.kvs.railways.module.ticket.validation.TripValidation
import java.time.LocalDateTime

@StationOnRouteValidation
@TicketAvailableValidation
@TripValidation
data class TicketDTO(
    @Schema(description = "Идентификатор пассажира")
    var passengerId: Long = 0,

    @Schema(description = "Идентификатор станции прибытия")
    var stationToId: Long = 0,

    @Schema(description = "Идентификатор станции отправления")
    var stationFromId: Long = 0,

    @Schema(description = "Идентификатор рейса")
    var tripId: Long = 0,

    @Schema(description = "Идентификатор места")
    var seatId: SeatId,

    @Schema(description = "Идентификатор вагона")
    var carriageId: Int = 0,

    @Schema(description = "Цена")
    var cost: Long = 0,

    @Schema(description = "Дата покупки")
    var purchaseDate: LocalDateTime = LocalDateTime.now()
)
