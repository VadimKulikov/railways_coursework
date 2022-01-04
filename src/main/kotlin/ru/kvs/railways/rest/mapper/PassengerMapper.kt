package ru.kvs.railways.rest.mapper

import org.springframework.stereotype.Component
import ru.kvs.railways.model.passenger.Passenger
import ru.kvs.railways.rest.dto.PassengerDTO

@Component
class PassengerMapper {

    fun map(dto: PassengerDTO, id: Long = 0) =
        Passenger(
            id = id,
            name = dto.name,
            surname = dto.surname,
            patronymic = dto.patronymic,
            documentNumber = dto.documentNumber
        )
}
