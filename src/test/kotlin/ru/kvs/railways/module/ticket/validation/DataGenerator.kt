package ru.kvs.railways.module.ticket.validation

import org.springframework.stereotype.Component
import ru.kvs.railways.model.carriage.Carriage
import ru.kvs.railways.model.carriage.CarriageType
import ru.kvs.railways.model.composition.Composition
import ru.kvs.railways.model.seat.ESeatType
import ru.kvs.railways.model.seat.Seat
import ru.kvs.railways.model.seat.SeatId
import ru.kvs.railways.model.station.Station
import java.util.UUID

@Component
class DataGenerator {

    fun generateComposition(): Triple<CarriageType, List<Seat>, Composition> {
        val carriageType = CarriageType(name = "Плацкарт")
        val seats = arrayListOf(
            Seat(
                SeatId(
                    carriageType = carriageType,
                    seatNumber = 1
                ),
                seatType = ESeatType.LOWER
            ),
            Seat(
                SeatId(
                    carriageType = carriageType,
                    seatNumber = 2
                ),
                seatType = ESeatType.UPPER
            )
        )

        val composition = Composition(
            carriages = arrayListOf(
                Carriage(
                    type = carriageType,
                    number = 1
                    )
            )
        )

        return Triple(carriageType, seats, composition)
    }

    fun generateStations(size: Int): ArrayList<Station> {
        val result = arrayListOf<Station>()

        for (i in 0 until size) {
            result.add(
                Station(
                    name = UUID.randomUUID().toString()
                )
            )
        }
        return result
    }

}
