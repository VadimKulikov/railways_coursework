package ru.kvs.railways.module.carriage.service

import org.springframework.stereotype.Service
import ru.kvs.railways.model.seat.Seat
import ru.kvs.railways.module.carriage.repository.SeatRepository

@Service
class SeatService(
    private val seatRepository: SeatRepository
) {

    fun save(seat: Seat) {
        seatRepository.save(seat)
    }
}
