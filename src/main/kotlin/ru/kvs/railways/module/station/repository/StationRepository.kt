package ru.kvs.railways.module.station.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kvs.railways.model.station.Station

@Repository
interface StationRepository: JpaRepository<Station, Long>
