package ru.kvs.railways.rest.mapper

import org.springframework.stereotype.Component
import ru.kvs.railways.model.trip.Trip
import ru.kvs.railways.module.route.service.RouteService
import ru.kvs.railways.module.train.service.TrainService
import ru.kvs.railways.rest.dto.TripDTO

@Component
class TripMapper(
    private val trainService: TrainService,
    private val routeService: RouteService
){
    fun map(trip: TripDTO) = Trip(
        train = trainService.find(trip.trainId),
        route = routeService.find(trip.routeId),
        departureTime = trip.departureTime
    )
}
