package ru.kvs.railways.module.carriage.service

import org.springframework.stereotype.Service
import ru.kvs.railways.model.carriage.Carriage
import ru.kvs.railways.module.carriage.repository.CarriageRepository
import javax.transaction.Transactional

@Service
class CarriageService(
    private val carriageRepository: CarriageRepository
) {
    fun save(carriage: Carriage) = carriageRepository.save(carriage)

    fun find(carriageId: Int): Carriage = carriageRepository.findById(carriageId).orElseThrow {
            RuntimeException("Вагон с идентификатором $carriageId не найден")
        }

    @Transactional
    fun delete(carriageId: Int) = carriageRepository.deleteById(carriageId)
}
