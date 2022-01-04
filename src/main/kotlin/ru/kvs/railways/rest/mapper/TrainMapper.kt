package ru.kvs.railways.rest.mapper

import org.springframework.stereotype.Component
import ru.kvs.railways.model.train.Train
import ru.kvs.railways.module.composition.service.CompositionService
import ru.kvs.railways.rest.dto.TrainDTO

@Component
class TrainMapper(
    private val compositionService: CompositionService
) {
    fun map(dto: TrainDTO) = Train(
            number = dto.number,
            composition = compositionService.find(dto.compositionId)
        )
}
