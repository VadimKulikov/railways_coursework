package ru.kvs.railways.module.station.service

import org.springframework.stereotype.Service
import ru.kvs.railways.model.station.Station
import ru.kvs.railways.module.station.repository.StationRepository

@Service
class StationService(
    private val stationRepository: StationRepository
) {
    fun saveAll(stations: List<Station>): MutableList<Station> = stationRepository.saveAll(stations)

    fun save(station: Station): Station = stationRepository.save(station)

    fun find(stationId: Long): Station = stationRepository.findById(stationId).orElseThrow {
            RuntimeException("Станция с идентификатором $stationId не найдена")
        }

    fun delete(stationId: Long) = stationRepository.deleteById(stationId)
}
