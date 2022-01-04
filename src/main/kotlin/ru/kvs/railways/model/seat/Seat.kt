package ru.kvs.railways.model.seat

import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "t_seat")
data class Seat(

    @EmbeddedId
    val id: SeatId,

    @Enumerated(EnumType.STRING)
    val seatType: ESeatType = ESeatType.LOWER
)
