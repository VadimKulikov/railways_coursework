package ru.kvs.railways.module.ticket.validation

import org.springframework.stereotype.Component
import ru.kvs.railways.module.ticket.service.TicketService
import ru.kvs.railways.module.trip.service.TripService
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Валидатор для проверки возврата билета
 */
@Component
class TicketReturnValidator(
    private val ticketService: TicketService,
    private val tripService: TripService
) : ConstraintValidator<TicketReturnValidation, Long> {
    companion object {
        private const val MIN_DAYS_TO_RETURN_TICKET = 1L
    }

    override fun isValid(value: Long, context: ConstraintValidatorContext?): Boolean {
        val ticket = ticketService.find(value)
        val tripSchedule = tripService.getSchedule(ticket.trip.id)
        val schedule = tripSchedule.find { it.stationName == ticket.stationFrom.name }
        val currentDate = LocalDateTime.now()

        return schedule?.arrivalTime?.isAfter(currentDate)!! &&
                ChronoUnit.DAYS.between(currentDate, schedule.arrivalTime) >= MIN_DAYS_TO_RETURN_TICKET
    }
}
