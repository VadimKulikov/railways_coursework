package ru.kvs.railways.model.ticket

import ru.kvs.railways.model.carriage.Carriage
import ru.kvs.railways.model.passenger.Passenger
import ru.kvs.railways.model.seat.Seat
import ru.kvs.railways.model.station.Station
import ru.kvs.railways.model.trip.Trip
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "t_ticket")
data class Ticket(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToOne
    val passenger: Passenger,

    @ManyToOne
    @JoinColumn(name = "station_from")
    val stationFrom: Station,

    @ManyToOne
    @JoinColumn(name = "station_to")
    val stationTo: Station,

    @ManyToOne
    @JoinColumn(name = "trip_id")
    val trip: Trip,

    @ManyToOne
    @JoinColumn(name = "carriage_id")
    val carriage: Carriage,

    @OneToOne
    val seat: Seat,

    val cost: Long = 0,

    @Enumerated(EnumType.STRING)
    val ticketStatus: TicketStatus = TicketStatus.CREATED
)
