package ru.kvs.railways.module.passenger.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kvs.railways.model.passenger.Passenger

@Repository
interface PassengerRepository: JpaRepository<Passenger, Long>
