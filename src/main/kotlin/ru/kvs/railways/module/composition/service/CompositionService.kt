package ru.kvs.railways.module.composition.service

import org.springframework.stereotype.Service
import ru.kvs.railways.model.composition.Composition
import ru.kvs.railways.module.carriage.repository.CarriageRepository
import ru.kvs.railways.module.composition.repository.CompositionRepository

@Service
class CompositionService(
    private val compositionRepository: CompositionRepository,
    private val carriageRepository: CarriageRepository
) {

    fun save(composition: Composition): Composition {
        carriageRepository.saveAll(composition.carriages)
        return compositionRepository.save(composition)
    }

    fun delete(compositionId: Int) = compositionRepository.deleteById(compositionId)

    fun find(compositionId: Int): Composition = compositionRepository.findById(compositionId)
        .orElseThrow { RuntimeException("Состав с идентификатором $compositionId не найден") }
}
