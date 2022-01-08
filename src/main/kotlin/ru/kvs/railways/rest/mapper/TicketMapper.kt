package ru.kvs.railways.rest.mapper

import org.springframework.stereotype.Component
import ru.kvs.railways.model.ticket.Ticket
import ru.kvs.railways.module.carriage.service.CarriageService
import ru.kvs.railways.module.carriage.service.SeatService
import ru.kvs.railways.module.passenger.service.PassengerService
import ru.kvs.railways.module.station.service.StationService
import ru.kvs.railways.module.trip.service.TripService
import ru.kvs.railways.rest.dto.TicketDTO

@Component
class TicketMapper(
    private val passengerService: PassengerService,
    private val stationService: StationService,
    private val tripService: TripService,
    private val carriageService: CarriageService,
    private val seatService: SeatService
) {
    fun map(dto: TicketDTO) = Ticket(
        passenger = passengerService.find(dto.passengerId),
        stationFrom = stationService.find(dto.stationFromId),
        stationTo = stationService.find(dto.stationToId),
        trip = tripService.find(dto.tripId),
        carriage = carriageService.find(dto.carriageId),
        seat = seatService.find(dto.seatId),
        cost = dto.cost,
        purchaseDate = dto.purchaseDate
    )
}
