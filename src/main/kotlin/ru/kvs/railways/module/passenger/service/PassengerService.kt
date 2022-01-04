package ru.kvs.railways.module.passenger.service

import org.springframework.stereotype.Service
import ru.kvs.railways.model.passenger.Passenger
import ru.kvs.railways.module.passenger.repository.PassengerRepository

@Service
class PassengerService(
    private val passengerRepository: PassengerRepository
) {
    fun save(passenger: Passenger) = passengerRepository.save(passenger)

    fun find(passengerId: Long): Passenger =
        passengerRepository.findById(passengerId).orElseThrow {
            RuntimeException("Пассажир с идентификатором $passengerId не найден")
        }

    fun delete(passengerId: Long) = passengerRepository.deleteById(passengerId)
}
