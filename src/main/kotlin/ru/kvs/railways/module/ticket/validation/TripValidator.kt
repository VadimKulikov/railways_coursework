package ru.kvs.railways.module.ticket.validation

import ru.kvs.railways.model.trip.TripStatus
import ru.kvs.railways.module.trip.service.TripService
import ru.kvs.railways.rest.dto.TicketDTO
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class TripValidator(
    private val tripService: TripService
) : ConstraintValidator<TripValidation, TicketDTO> {
    override fun isValid(value: TicketDTO, context: ConstraintValidatorContext?): Boolean {
        val trip = tripService.find(value.tripId)
        return trip.tripStatus != TripStatus.COMPLETED
    }
}
