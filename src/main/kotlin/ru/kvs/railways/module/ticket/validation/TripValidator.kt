package ru.kvs.railways.module.ticket.validation

import org.springframework.stereotype.Component
import ru.kvs.railways.module.station.service.StationService
import ru.kvs.railways.module.trip.service.TripService
import ru.kvs.railways.rest.dto.TicketDTO
import java.time.Duration
import java.time.LocalDateTime
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Валидатор проверяющий время покупки билета
 */
@Component
class TripValidator(
    private val tripService: TripService,
    private val stationService: StationService
) : ConstraintValidator<TripValidation, TicketDTO> {
    companion object {
        private const val MIN_HOURS_TO_BUY_TICKET = 1
    }
    override fun isValid(value: TicketDTO, context: ConstraintValidatorContext?): Boolean {
        val trip = tripService.find(value.tripId)
        val stationFrom = stationService.find(value.stationFromId)
        val tripSchedule = tripService.getSchedule(trip.id)
        val schedule = tripSchedule.find { it.stationName == stationFrom.name }
        val currentDate = LocalDateTime.now()

        val hourDiff = Duration.between(currentDate, schedule?.arrivalTime).toHours()

        return schedule?.arrivalTime?.isAfter(currentDate)!! && (hourDiff >= MIN_HOURS_TO_BUY_TICKET)
    }
}
