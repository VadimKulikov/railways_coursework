package ru.kvs.railways.module.route.service

import org.springframework.stereotype.Service
import ru.kvs.railways.model.route.Route
import ru.kvs.railways.module.route.repisotory.RouteRepository

@Service
class RouteService(
    private val routeRepository: RouteRepository
) {
    fun save(route: Route) = routeRepository.save(route)
    fun find(routeId: Long): Route = routeRepository.findById(routeId).orElseThrow {
        RuntimeException("Маршрут с идентификатором $routeId не найден")
    }
}
