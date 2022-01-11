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
        val stations = routeDto.stations.sortedBy { it.arrivalPeriod }
            .map {
                RouteStation(
                    stationName = stationService.find(it.stationId).name,
                    arrivalPeriod = it.arrivalPeriod,
                    stop = it.stop
                )
            }

        return Route(
            stations = stations,
            name = "${stations.first().stationName} - ${stations.last().stationName}"
        )
    }
}
