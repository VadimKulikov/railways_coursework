package ru.kvs.railways.rest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kvs.railways.module.route.service.RouteService
import ru.kvs.railways.rest.dto.route.RouteDTO
import ru.kvs.railways.rest.mapper.RouteMapper

@RestController
@RequestMapping("api/route")
@Tag(
    name = "Маршруты",
    description = "Операции для работы с маршрутами"
)
class RouteController(
    private val routeService: RouteService,
    private val routeMapper: RouteMapper
) {

    @PostMapping
    @Operation(
        description = "Сохранение маршрута"
    )
    fun save(
        @RequestBody
        routeDto: RouteDTO
    ) = routeService.save(
        routeMapper.map(routeDto)
    )

    @GetMapping("/{routeId}")
    @Operation(
        description = "Поиск маршрута"
    )
    fun find(
        @PathVariable
        routeId: Long
    ) = routeService.find(routeId)
}
