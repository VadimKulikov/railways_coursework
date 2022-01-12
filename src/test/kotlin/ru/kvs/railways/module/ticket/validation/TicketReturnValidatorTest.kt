package ru.kvs.railways.module.ticket.validation

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import ru.kvs.railways.model.passenger.Passenger
import ru.kvs.railways.model.route.CustomDuration
import ru.kvs.railways.model.route.Route
import ru.kvs.railways.model.route.RouteStation
import ru.kvs.railways.model.ticket.Ticket
import ru.kvs.railways.model.train.Train
import ru.kvs.railways.model.trip.Trip
import ru.kvs.railways.module.carriage.repository.CarriageRepository
import ru.kvs.railways.module.carriage.repository.CarriageTypeRepository
import ru.kvs.railways.module.carriage.repository.SeatRepository
import ru.kvs.railways.module.composition.repository.CompositionRepository
import ru.kvs.railways.module.passenger.repository.PassengerRepository
import ru.kvs.railways.module.route.repisotory.RouteRepository
import ru.kvs.railways.module.station.repository.StationRepository
import ru.kvs.railways.module.ticket.repository.TicketRepository
import ru.kvs.railways.module.train.repository.TrainRepository
import ru.kvs.railways.module.trip.repository.TripRepository
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
class TicketReturnValidatorTest {
    @Autowired
    private lateinit var ticketReturnValidator: TicketReturnValidator

    @Autowired
    private lateinit var stationRepository: StationRepository

    @Autowired
    private lateinit var routeRepository: RouteRepository

    @Autowired
    private lateinit var dataGenerator: DataGenerator

    @Autowired
    private lateinit var tripRepository: TripRepository

    @Autowired
    private lateinit var seatsRepository: SeatRepository

    @Autowired
    private lateinit var carriageTypeRepository: CarriageTypeRepository

    @Autowired
    private lateinit var compositionRepository: CompositionRepository

    @Autowired
    private lateinit var trainRepository: TrainRepository

    @Autowired
    private lateinit var carriageRepository: CarriageRepository

    @Autowired
    private lateinit var passengerRepository: PassengerRepository

    @Autowired
    private lateinit var ticketRepository: TicketRepository

    @AfterEach
    fun tearDown() {
        ticketRepository.deleteAll()
        stationRepository.deleteAll()
        tripRepository.deleteAll()
        routeRepository.deleteAll()
        trainRepository.deleteAll()
        compositionRepository.deleteAll()
        carriageRepository.deleteAll()
        seatsRepository.deleteAll()
        carriageTypeRepository.deleteAll()
        passengerRepository.deleteAll()
    }

    @Test
    fun testReturnLessThanDay() {
        // given
        val stations = stationRepository.saveAll(
            dataGenerator.generateStations(3)
        )

        val (carriageType, seats, composition) = dataGenerator.generateComposition()
        carriageTypeRepository.save(carriageType)
        seatsRepository.saveAll(seats)
        carriageRepository.saveAll(composition.carriages)
        compositionRepository.save(composition)

        val route = routeRepository.save(
            Route(
                stations = arrayListOf(
                    RouteStation(
                        stationName = stations[0].name,
                        arrivalPeriod = CustomDuration(hours = 0, minutes = 0),
                        stop = CustomDuration()
                    ),
                    RouteStation(
                        stationName = stations[1].name,
                        arrivalPeriod = CustomDuration(hours = 2, minutes = 30),
                        stop = CustomDuration()
                    )
                ),
                name = "testRoute"
            )
        )

        val train = trainRepository.save(
            Train(
                number = 1,
                composition = composition
            )
        )

        val trip = tripRepository.save(
            Trip(
                route = route,
                train = train,
                departureTime = LocalDateTime.now().plusMinutes(30)
            )
        )
        val passenger = passengerRepository.save(
            Passenger(
                name = "Pass",
                surname = "Pass",
                patronymic = "Pass",
                documentNumber = "1111 111111"
            )
        )
        val ticket = ticketRepository.save(
            Ticket(
                stationFrom = stations[0],
                stationTo = stations[1],
                trip = trip,
                carriage = composition.carriages[0],
                passenger = passenger,
                seat = seats[0]
            )
        )

        // when
        val result = ticketReturnValidator.isValid(ticket.id, null)

        //then
        assertFalse(result)
    }

    @Test
    fun testReturnCorrect() {
        // given
        val stations = stationRepository.saveAll(
            dataGenerator.generateStations(3)
        )

        val (carriageType, seats, composition) = dataGenerator.generateComposition()
        carriageTypeRepository.save(carriageType)
        seatsRepository.saveAll(seats)
        carriageRepository.saveAll(composition.carriages)
        compositionRepository.save(composition)

        val route = routeRepository.save(
            Route(
                stations = arrayListOf(
                    RouteStation(
                        stationName = stations[0].name,
                        arrivalPeriod = CustomDuration(hours = 0, minutes = 0),
                        stop = CustomDuration()
                    ),
                    RouteStation(
                        stationName = stations[1].name,
                        arrivalPeriod = CustomDuration(hours = 2, minutes = 30),
                        stop = CustomDuration()
                    )
                ),
                name = "testRoute"
            )
        )

        val train = trainRepository.save(
            Train(
                number = 1,
                composition = composition
            )
        )

        val trip = tripRepository.save(
            Trip(
                route = route,
                train = train,
                departureTime = LocalDateTime.now().plusDays(5)
            )
        )
        val passenger = passengerRepository.save(
            Passenger(
                name = "Pass",
                surname = "Pass",
                patronymic = "Pass",
                documentNumber = "1111 111111"
            )
        )
        val ticket = ticketRepository.save(
            Ticket(
                stationFrom = stations[0],
                stationTo = stations[1],
                trip = trip,
                carriage = composition.carriages[0],
                passenger = passenger,
                seat = seats[0]
            )
        )

        // when
        val result = ticketReturnValidator.isValid(ticket.id, null)

        //then
        assertTrue(result)
    }
}