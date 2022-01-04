package ru.kvs.railways.rest.mapper

import org.springframework.stereotype.Component
import ru.kvs.railways.model.route.Route
import ru.kvs.railways.model.route.RouteStation
import ru.kvs.railways.module.station.service.StationService
import ru.kvs.railways.rest.dto.route.RouteDTO

@Component
class RouteMapper(
    private val stationService: StationService
) {

    fun map(routeDto: RouteDTO): Route {
        return Route(
            stations = routeDto.stations.map {
                RouteStation(
                    stationName = stationService.find(it.stationId).name,
                    arrivalPeriod = it.arrivalPeriod,
                    stop = it.stop
                )
            }
        )
    }
}
