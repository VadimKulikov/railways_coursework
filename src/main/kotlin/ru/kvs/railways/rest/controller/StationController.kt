package ru.kvs.railways.rest.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kvs.railways.model.station.Station
import ru.kvs.railways.module.station.service.StationService
import ru.kvs.railways.rest.dto.StationDTO

@RestController
@RequestMapping("api/station")
@Tag(
    name = "Станции",
    description = "Методы работы со станциями"
)
class StationController(
    private val stationService: StationService
) {

    @PostMapping
    fun save(
        @RequestBody
        station: StationDTO
    ) = stationService.save(
        Station(
            name = station.name
        )
    )

    @GetMapping("/{stationId}")
    fun find(
        @PathVariable
        stationId: Long
    ) = stationService.find(stationId)

    @DeleteMapping("/{stationId}")
    fun delete(
        @PathVariable
        stationId: Long
    ): Long {
        stationService.delete(stationId)
        return stationId
    }

    @PutMapping("/{stationId}")
    fun update(
        @PathVariable
        stationId: Long,
        @RequestBody
        station: StationDTO
    ) = stationService.save(
        Station(
            id = stationId,
            name = station.name
        )
    )
}
