package ru.kvs.railways.rest.controller

import io.swagger.v3.oas.annotations.Operation
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
    @Operation(
        description = "Сохранение станции"
    )
    fun save(
        @RequestBody
        station: StationDTO
    ) = stationService.save(
        Station(
            name = station.name
        )
    )

    @GetMapping("/{stationId}")
    @Operation(
        description = "Поиск станции по идентификатору"
    )
    fun find(
        @PathVariable
        stationId: Long
    ) = stationService.find(stationId)

    @DeleteMapping("/{stationId}")
    @Operation(
        description = "Удаление станции"
    )
    fun delete(
        @PathVariable
        stationId: Long
    ): Long {
        stationService.delete(stationId)
        return stationId
    }

    @PutMapping("/{stationId}")
    @Operation(
        description = "Обновление информации о станции"
    )
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
