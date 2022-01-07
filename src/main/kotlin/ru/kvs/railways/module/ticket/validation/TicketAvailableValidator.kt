package ru.kvs.railways.module.ticket.validation

import ru.kvs.railways.module.ticket.service.TicketService
import ru.kvs.railways.rest.dto.TicketDTO
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class TicketAvailableValidator(
    private val ticketService: TicketService
) : ConstraintValidator<TicketAvailableValidation, TicketDTO> {
    override fun isValid(value: TicketDTO, context: ConstraintValidatorContext?): Boolean {
        val ticket = ticketService.find(value.seatId)
        return if (ticket != null)
            ticket.trip.id != value.tripId
        else true
    }
}
