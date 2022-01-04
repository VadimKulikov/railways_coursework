package ru.kvs.railways.module.train.service

import org.springframework.stereotype.Service
import ru.kvs.railways.model.train.Train
import ru.kvs.railways.module.train.repository.TrainRepository

@Service
class TrainService(
    private val trainRepository: TrainRepository
) {
    fun save(train: Train) = trainRepository.save(train)

    fun find(trainId: Int): Train = trainRepository.findById(trainId)
        .orElseThrow { RuntimeException("Поезд с идентификатором $trainId не найден") }
}
