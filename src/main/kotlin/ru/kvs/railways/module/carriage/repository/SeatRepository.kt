package ru.kvs.railways.module.carriage.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kvs.railways.model.seat.Seat
import ru.kvs.railways.model.seat.SeatId

@Repository
interface SeatRepository : JpaRepository<Seat, SeatId>
