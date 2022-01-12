package ru.kvs.railways.module.ticket.validation

import org.springframework.stereotype.Component
import ru.kvs.railways.module.ticket.service.TicketService
import ru.kvs.railways.module.trip.service.TripService
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class TicketReturnValidator(
    private val ticketService: TicketService,
    private val tripService: TripService
) : ConstraintValidator<TicketReturnValidation, Long> {

    override fun isValid(value: Long, context: ConstraintValidatorContext?): Boolean {
        val ticket = ticketService.find(value)
        val tripSchedule = tripService.getSchedule(ticket.trip.id)
        val schedule = tripSchedule.find { it.stationName == ticket.stationFrom.name }
        val currentDate = LocalDateTime.now()

        return schedule?.arrivalTime?.isAfter(currentDate)!! &&
                ChronoUnit.DAYS.between(currentDate, schedule.arrivalTime) >= 1L
    }
}
