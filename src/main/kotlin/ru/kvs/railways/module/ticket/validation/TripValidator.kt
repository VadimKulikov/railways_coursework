package ru.kvs.railways.module.ticket.validation

import ru.kvs.railways.module.station.service.StationService
import ru.kvs.railways.module.trip.service.TripService
import ru.kvs.railways.rest.dto.TicketDTO
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class TripValidator(
    private val tripService: TripService,
    private val stationService: StationService
) : ConstraintValidator<TripValidation, TicketDTO> {
    override fun isValid(value: TicketDTO, context: ConstraintValidatorContext?): Boolean {
        val trip = tripService.find(value.tripId)
        val stationFrom = stationService.find(value.stationFromId)
        val tripSchedule = tripService.getSchedule(trip.id)
        val schedule = tripSchedule?.find { it.stationName == stationFrom.name }
        val currentDate = LocalDateTime.now()

        return schedule?.arrivalTime?.isAfter(currentDate)!! &&
                (ChronoUnit.HOURS.between(schedule.arrivalTime, currentDate) >= 2)
    }
}
