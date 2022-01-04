package ru.kvs.railways.rest.mapper

import org.springframework.stereotype.Component
import ru.kvs.railways.model.carriage.Carriage
import ru.kvs.railways.module.carriage.service.CarriageTypeService
import ru.kvs.railways.rest.dto.carriage.CarriageDTO

@Component
class CarriageTypeMapper(
    private val carriageTypeService: CarriageTypeService
) {
    fun map(dto: CarriageDTO) = Carriage(
        type = carriageTypeService.find(dto.typeId),
        number = dto.number
    )
}
