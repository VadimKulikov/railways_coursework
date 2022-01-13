package ru.kvs.railways.rest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kvs.railways.module.trip.service.TripService
import ru.kvs.railways.rest.dto.PeriodicTripDTO
import ru.kvs.railways.rest.dto.TripDTO
import ru.kvs.railways.rest.mapper.TripMapper
import javax.validation.Valid

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
    @Operation(
        description = "Создание рейса"
    )
    fun save(
        @Valid
        @RequestBody
        trip: TripDTO
    ) = tripService.save(
        tripMapper.map(trip)
    )

    @PostMapping("/periodic")
    @Operation(
        description = "Создание периодических рейсов до определенной даты"
    )
    fun createPeriodicTrips(
        @RequestBody
        periodicTrip: PeriodicTripDTO
    ) {
        tripService.createPeriodicTrips(periodicTrip)
    }

    @GetMapping("/{tripId}/schedule")
    @Operation(
        description = "Получение расписания рейса по идентификатору"
    )
    fun getTripSchedule(
        @PathVariable
        tripId: Long
    ) = tripService.getSchedule(tripId)
}
