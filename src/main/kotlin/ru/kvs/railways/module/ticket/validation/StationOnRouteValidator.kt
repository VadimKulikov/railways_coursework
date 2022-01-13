package ru.kvs.railways.module.ticket.validation

import org.springframework.stereotype.Component
import ru.kvs.railways.module.station.service.StationService
import ru.kvs.railways.module.trip.service.TripService
import ru.kvs.railways.rest.dto.TicketDTO
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Валидатор, проверяющий что станции находятся на пути следования маршрута,
 * а также указаны в правильной последовательности
 */
@Component
class StationOnRouteValidator(
    private val stationService: StationService,
    private val tripService: TripService
) : ConstraintValidator<StationOnRouteValidation, TicketDTO> {

    override fun isValid(value: TicketDTO, context: ConstraintValidatorContext?): Boolean {
        val stationFrom = stationService.find(value.stationFromId).name
        val stationTo = stationService.find(value.stationToId).name
        val trip = tripService.find(value.tripId)

        val names = trip.route?.stations?.map { it.stationName }

        return if (!names?.containsAll(listOf(stationFrom, stationTo))!!) false
        else {
            val schedule = tripService.getSchedule(trip.id)

            val stationFromArrivalTime = schedule.find { it.stationName == stationFrom }
                ?.arrivalTime!!

            val stationToArrivalTime = schedule.find { it.stationName == stationTo }
                ?.arrivalTime!!

            stationToArrivalTime.isAfter(stationFromArrivalTime)
        }
    }
}
