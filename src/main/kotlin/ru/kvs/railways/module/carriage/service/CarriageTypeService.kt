package ru.kvs.railways.module.carriage.service

import org.springframework.stereotype.Service
import ru.kvs.railways.model.carriage.CarriageType
import ru.kvs.railways.module.carriage.repository.CarriageTypeRepository

@Service
class CarriageTypeService(
    private val carriageTypeRepository: CarriageTypeRepository
) {

    fun save(carriageType: CarriageType): CarriageType {
        return carriageTypeRepository.save(carriageType)
    }

    fun find(typeId: Int): CarriageType? = carriageTypeRepository.findById(typeId).orElseThrow {
        RuntimeException("Тип вагона с идентификатором $typeId не найден")
    }

    fun delete(typeId: Int) {
        carriageTypeRepository.deleteById(typeId)
    }
}
