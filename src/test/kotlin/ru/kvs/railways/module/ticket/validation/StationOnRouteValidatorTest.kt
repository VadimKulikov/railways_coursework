package ru.kvs.railways.module.ticket.validation

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import ru.kvs.railways.model.route.CustomDuration
import ru.kvs.railways.model.route.Route
import ru.kvs.railways.model.route.RouteStation
import ru.kvs.railways.model.train.Train
import ru.kvs.railways.model.trip.Trip
import ru.kvs.railways.module.carriage.repository.CarriageRepository
import ru.kvs.railways.module.carriage.repository.CarriageTypeRepository
import ru.kvs.railways.module.carriage.repository.SeatRepository
import ru.kvs.railways.module.composition.repository.CompositionRepository
import ru.kvs.railways.module.route.repisotory.RouteRepository
import ru.kvs.railways.module.station.repository.StationRepository
import ru.kvs.railways.module.train.repository.TrainRepository
import ru.kvs.railways.module.trip.repository.TripRepository
import ru.kvs.railways.rest.dto.TicketDTO
import java.time.LocalDateTime

@SpringBootTest
@ActiveProfiles("test")
internal class StationOnRouteValidatorTest {

    @Autowired
    private lateinit var stationOnRouteValidator: StationOnRouteValidator

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

    @AfterEach
    fun tearDown() {
        stationRepository.deleteAll()
        tripRepository.deleteAll()
        routeRepository.deleteAll()
        trainRepository.deleteAll()
        compositionRepository.deleteAll()
        carriageRepository.deleteAll()
        seatsRepository.deleteAll()
        carriageTypeRepository.deleteAll()
    }

    @Test
    fun testStationIsNotOnRoute() {
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
                        arrivalPeriod = CustomDuration(hours = 1, minutes = 30),
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
                departureTime = LocalDateTime.now().plusDays(7)
            )
        )

        val ticket = TicketDTO(
            stationToId = stations[2].id,
            stationFromId = stations[0].id,
            tripId = trip.id,
            seatId = seats[0].id
        )

        // when
        val result = stationOnRouteValidator.isValid(ticket, null)

        //then
        assertFalse(result)
    }

    @Test
    fun testStationsNotInOrder() {
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
                        arrivalPeriod = CustomDuration(hours = 1, minutes = 30),
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
                departureTime = LocalDateTime.now().plusDays(7)
            )
        )

        val ticket = TicketDTO(
            stationToId = stations[0].id,
            stationFromId = stations[1].id,
            tripId = trip.id,
            seatId = seats[0].id
        )

        // when
        val result = stationOnRouteValidator.isValid(ticket, null)

        //then
        assertFalse(result)
    }

    @Test
    fun testStationsCorrect() {
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
                        arrivalPeriod = CustomDuration(hours = 1, minutes = 30),
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
                departureTime = LocalDateTime.now().plusDays(7)
            )
        )

        val ticket = TicketDTO(
            stationToId = stations[1].id,
            stationFromId = stations[0].id,
            tripId = trip.id,
            seatId = seats[0].id
        )

        // when
        val result = stationOnRouteValidator.isValid(ticket, null)

        //then
        assertTrue(result)
    }
}