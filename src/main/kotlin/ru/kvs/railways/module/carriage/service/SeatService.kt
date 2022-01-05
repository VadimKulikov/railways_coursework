package ru.kvs.railways.module.carriage.service

import org.springframework.stereotype.Service
import ru.kvs.railways.model.seat.Seat
import ru.kvs.railways.model.seat.SeatId
import ru.kvs.railways.module.carriage.repository.SeatRepository

@Service
class SeatService(
    private val seatRepository: SeatRepository
) {

    fun save(seat: Seat) = seatRepository.save(seat)

    fun find(seatId: SeatId): Seat = seatRepository.findById(seatId)
        .orElseThrow { RuntimeException("Место с идентификатором $seatId не найдено") }
}
