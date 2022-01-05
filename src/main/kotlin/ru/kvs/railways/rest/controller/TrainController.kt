package ru.kvs.railways.rest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kvs.railways.module.train.service.TrainService
import ru.kvs.railways.rest.dto.TrainDTO
import ru.kvs.railways.rest.mapper.TrainMapper

@RestController
@RequestMapping("api/train")
@Tag(
    name = "Поезда",
    description = "Операции для работы с поездами"
)
class TrainController(
    private val trainService: TrainService,
    private val trainMapper: TrainMapper
) {

    @PostMapping
    @Operation(
        description = "Создание поезда"
    )
    fun save(
        @RequestBody
        train: TrainDTO
    ) = trainService.save(
        trainMapper.map(train)
    )

    @GetMapping("/{trainId}")
    @Operation(
        description = "Поиск поезда по идентификатору"
    )
    fun find(
        @PathVariable
        trainId: Int
    ) = trainService.find(trainId)
}
