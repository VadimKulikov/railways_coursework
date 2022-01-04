package ru.kvs.railways.rest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.kvs.railways.model.carriage.Carriage
import ru.kvs.railways.module.carriage.service.CarriageService
import ru.kvs.railways.module.carriage.service.CarriageTypeService
import ru.kvs.railways.rest.dto.carriage.CarriageDTO

@RestController
@RequestMapping("api/carriage")
@Tag(
    name = "Вагоны",
    description = "Методы для работы с вагонами"
)
class CarriageController(
    private val carriageService: CarriageService,
    private val carriageTypeService: CarriageTypeService
) {

    @GetMapping("/{carriageId}")
    @Operation(description = "Поиск вагона по идентификатору")
    fun find(
        @PathVariable
        carriageId: Int
    ) = carriageService.find(carriageId)

    @DeleteMapping("/{carriageId}")
    @Operation(description = "Удаление вагона по идентификатору")
    fun delete(
        @PathVariable
        carriageId: Int
    ): Int {
        carriageService.delete(carriageId)
        return carriageId
    }

    @PutMapping
    @Operation(description = "Обновление информации о вагоне")
    fun update(
        @RequestParam
        carriageId: Int,
        @RequestBody
        carriage: CarriageDTO
    ) = carriageService.save(
        Carriage(
            id = carriageId,
            type = carriageTypeService.find(carriage.typeId),
            number = carriage.number
        )
    )
}
