package ru.kvs.railways.module.trip.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import ru.kvs.railways.model.trip.Schedule
import ru.kvs.railways.model.trip.Trip
import ru.kvs.railways.module.trip.repository.TripRepository
import ru.kvs.railways.rest.dto.PeriodicTripDTO

@Service
class TripService(
    private val tripRepository: TripRepository,
    private val objectMapper: ObjectMapper
) {
    fun save(trip: Trip): Trip = tripRepository.save(trip)

    fun getSchedule(tripId: Long): List<Schedule> {
        return tripRepository.getSchedule(tripId).map {
            Schedule(
                it.getName(),
                it.getArrivalTime(),
                it.getDepartureTime()
            )
        }
    }

    fun find(tripId: Long): Trip = tripRepository.findById(tripId)
        .orElseThrow { RuntimeException("Рейс с идентификатором $tripId не найден") }

    fun createPeriodicTrips(periodicTrip: PeriodicTripDTO) {
        tripRepository.createPeriodicTrip(
            objectMapper.writeValueAsString(periodicTrip)
        )
    }
}
