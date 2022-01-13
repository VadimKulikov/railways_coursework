package ru.kvs.railways.rest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kvs.railways.model.carriage.CarriageType
import ru.kvs.railways.model.seat.Seat
import ru.kvs.railways.model.seat.SeatId
import ru.kvs.railways.module.carriage.service.CarriageTypeService
import ru.kvs.railways.module.carriage.service.SeatService
import ru.kvs.railways.rest.dto.carriage.CarriageTypeDTO

@RestController
@RequestMapping("api/carriage-type")
@Tag(
    name = "Типы вагонов",
    description = "Операции для работы с типами вагонов и их планировкой"
)
class CarriageTypeController(
    private val carriageTypeService: CarriageTypeService,
    private val seatService: SeatService
) {

    @PostMapping
    @Operation(
        description = "Создание типа вагона и его планировки"
    )
    fun save(
        @RequestBody carriageType: CarriageTypeDTO
    ): CarriageType {
        val carriage = carriageTypeService.save(
            CarriageType(
                name = carriageType.name
            )
        )

        carriageType.seats.forEach {
            seatService.save(
                Seat(
                    id = SeatId(
                        carriageType = carriage,
                        seatNumber = it.seatNumber
                    ),
                    seatType = it.seatType
                )
            )
        }

        return carriage
    }

    @DeleteMapping("/{typeId}")
    @Operation(
        description = "Удаление типа вагона"
    )
    fun deleteCarriageType(
        @PathVariable typeId: Int
    ) {
        carriageTypeService.delete(typeId)
    }
}
