package ru.kvs.railways.rest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kvs.railways.model.passenger.Passenger
import ru.kvs.railways.module.passenger.service.PassengerService
import ru.kvs.railways.rest.dto.PassengerDTO
import ru.kvs.railways.rest.mapper.PassengerMapper

@RestController
@RequestMapping("api/passenger")
@Tag(
    name = "Пассажиры",
    description = "Методы работы с пассажирами"
)
class PassengerController(
    private val passengerService: PassengerService,
    private val passengerMapper: PassengerMapper
) {

    @PostMapping
    @Operation(description = "Сохранение пассажира")
    fun savePassenger(
        @RequestBody
        passenger: PassengerDTO
    ) = passengerService.save(
        passengerMapper.map(passenger)
    )

    @GetMapping("/{passengerId}")
    @Operation(
        description = "Поиск пассажира по идентификатору"
    )
    fun find(
        @PathVariable
        passengerId: Long
    ): Passenger = passengerService.find(passengerId)

    @DeleteMapping("/{passengerId}")
    @Operation(
        description = "Удаление пассажира"
    )
    fun delete(
        @PathVariable
        passengerId: Long
    ): Long {
        passengerService.delete(passengerId)
        return passengerId
    }

    @PutMapping("/{passengerId}")
    @Operation(
        description = "Обновление информации о пассажире"
    )
    fun update(
        @RequestBody
        passenger: PassengerDTO,

        @PathVariable
        passengerId: Long
    ) = passengerService.save(
        passengerMapper.map(passenger, passengerId)
    )
}
