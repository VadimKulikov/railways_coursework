package ru.kvs.railways.module.trip.validation

import ru.kvs.railways.module.trip.service.TripService
import ru.kvs.railways.rest.dto.TripDTO
import java.time.temporal.ChronoUnit
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Валидатор для проверки поезда, назначаемого на рейс
 */
class TrainValidator(
    private val tripService: TripService
) : ConstraintValidator<TrainValidation, TripDTO> {
    companion object {
        private const val MIN_DAYS_TILL_NEXT_TRIP = 3
    }

    override fun isValid(value: TripDTO, context: ConstraintValidatorContext?): Boolean {
        val lastTrip = tripService.findLast(value.trainId) ?: return true
        val lastArrivalTime = tripService.getSchedule(lastTrip.id).last().arrivalTime

        return value.departureTime.isAfter(lastArrivalTime) &&
                ChronoUnit.DAYS.between(lastArrivalTime, value.departureTime) >= MIN_DAYS_TILL_NEXT_TRIP
    }
}
