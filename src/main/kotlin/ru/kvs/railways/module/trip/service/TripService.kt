package ru.kvs.railways.module.trip.service

import org.springframework.stereotype.Service
import ru.kvs.railways.model.trip.Schedule
import ru.kvs.railways.model.trip.Trip
import ru.kvs.railways.module.trip.repository.TripRepository

@Service
class TripService(
    private val tripRepository: TripRepository
) {
    fun save(trip: Trip) = tripRepository.save(trip)

    fun getSchedule(tripId: Int): List<Schedule>? {
        return tripRepository.getSchedule(tripId).map {
            Schedule(
                it.getName(),
                it.getArrivalTime(),
                it.getDepartureTime()
            )
        }
    }

    fun find(tripId: Int): Trip = tripRepository.findById(tripId)
        .orElseThrow { RuntimeException("Рейс с идентификатором $tripId не найден") }
}
