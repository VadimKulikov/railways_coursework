package ru.kvs.railways.module.route.service

import org.springframework.stereotype.Service
import ru.kvs.railways.model.route.PopularRoute
import ru.kvs.railways.model.route.Route
import ru.kvs.railways.module.route.repisotory.RouteRepository

@Service
class RouteService(
    private val routeRepository: RouteRepository
) {
    fun save(route: Route): Route = routeRepository.save(route)
    fun find(routeId: Long): Route = routeRepository.findById(routeId).orElseThrow {
        RuntimeException("Маршрут с идентификатором $routeId не найден")
    }

    fun getPopularRoutes(month: Int, year: Int, amount: Int): List<PopularRoute> =
        routeRepository.getPopularRoutes(month, year, amount).map {
            PopularRoute(
                routeId = it.getRouteId(),
                name = it.getName(),
                ticketsSold = it.getTicketsSold()
            )
        }
}
