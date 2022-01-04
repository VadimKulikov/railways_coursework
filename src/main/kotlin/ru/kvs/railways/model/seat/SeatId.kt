package ru.kvs.railways.model.seat

import ru.kvs.railways.model.carriage.CarriageType
import java.io.Serializable
import javax.persistence.Embeddable
import javax.persistence.OneToOne

@Embeddable
data class SeatId(
    @OneToOne
    var carriageType: CarriageType = CarriageType(),
    var seatNumber: Int = 0
) : Serializable {
    companion object {
        const val serialVersionUID = 1L
    }
}
