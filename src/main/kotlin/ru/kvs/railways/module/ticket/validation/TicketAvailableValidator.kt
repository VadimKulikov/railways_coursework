package ru.kvs.railways.module.ticket.validation

import ru.kvs.railways.model.ticket.Ticket
import ru.kvs.railways.module.station.service.StationService
import ru.kvs.railways.module.ticket.service.TicketService
import ru.kvs.railways.rest.dto.TicketDTO
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Валидатор определяющий занято место или нет
 */
class TicketAvailableValidator(
    private val ticketService: TicketService,
    private val stationService: StationService
) : ConstraintValidator<TicketAvailableValidation, TicketDTO> {
    override fun isValid(value: TicketDTO, context: ConstraintValidatorContext?): Boolean {
        val existingTicket = ticketService.find(value.seatId)
        return if (existingTicket == null) true
        else seatIsAvailable(existingTicket, value)
    }

    private fun seatIsAvailable(ticket: Ticket, dto: TicketDTO): Boolean {
        return if (ticket.trip.id == dto.tripId) {
            val ticketLastStationName = ticket.stationTo.name
            val newTicketFirstStationName = stationService.find(dto.stationFromId).name

            val routeStations = ticket.trip.route?.stations

            val ticketLastStation = routeStations?.find { it.stationName == ticketLastStationName }
            val newTicketFirstStation = routeStations?.find { it.stationName == newTicketFirstStationName }

            ticketLastStation?.arrivalPeriod!! <= newTicketFirstStation?.arrivalPeriod!!
        } else true
    }
}
