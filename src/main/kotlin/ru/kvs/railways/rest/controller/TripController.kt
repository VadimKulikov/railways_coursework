package ru.kvs.railways.rest.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kvs.railways.module.trip.service.TripService
import ru.kvs.railways.rest.dto.TripDTO
import ru.kvs.railways.rest.mapper.TripMapper

@RestController
@RequestMapping("api/trip")
@Tag(
    name = "Рейсы",
    description = "Операции для работы с рейсами"
)
class TripController(
    private val tripService: TripService,
    private val tripMapper: TripMapper
) {

    @PostMapping
    fun save(
        @RequestBody
        trip: TripDTO
    ) = tripService.save(
        tripMapper.map(trip)
    )

    @GetMapping("/{tripId}/schedule")
    fun getTripSchedule(
        @PathVariable
        tripId: Int
    ) = tripService.getSchedule(tripId)
}
