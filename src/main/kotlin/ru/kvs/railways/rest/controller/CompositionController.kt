package ru.kvs.railways.rest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kvs.railways.model.composition.Composition
import ru.kvs.railways.module.composition.service.CompositionService
import ru.kvs.railways.rest.dto.CompositionDTO
import ru.kvs.railways.rest.mapper.CarriageTypeMapper

@RestController
@RequestMapping("api/composition")
@Tag(
    name = "Составы",
    description = "Методы для работы с составами"
)
class CompositionController(
    private val compositionService: CompositionService,
    private val carriageTypeMapper: CarriageTypeMapper
) {

    @PostMapping
    @Operation(
        description = "Сохранение состава"
    )
    fun save(
        @RequestBody
        composition: CompositionDTO
    ) = compositionService.save(
        Composition(
            carriages = composition.carriages.map {
                carriageTypeMapper.map(it)
            }
        )
    )

    @DeleteMapping("/{compositionId}")
    @Operation(
        description = "Удаление состава"
    )
    fun delete(@PathVariable compositionId: Int) = compositionService.delete(compositionId)
}
