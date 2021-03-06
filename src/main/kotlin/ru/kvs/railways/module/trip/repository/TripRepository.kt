package ru.kvs.railways.module.trip.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.kvs.railways.model.trip.ScheduleProjection
import ru.kvs.railways.model.trip.Trip

@Repository
interface TripRepository : JpaRepository<Trip, Long> {
    @Query(
        """
        SELECT * FROM get_schedule(?1);
        """,
        nativeQuery = true
    )
    fun getSchedule(tripId: Long): List<ScheduleProjection>

    @Query(
        """
        SELECT * FROM create_periodic_trip(?1);
        """,
        nativeQuery = true
    )
    fun createPeriodicTrip(trip: String): List<Trip>

    @Query(
        """
        SELECT * FROM t_trip tt 
        WHERE (train_id, departure_time) IN (
            SELECT train_id, max(departure_time)
            FROM t_trip
            WHERE train_id = :trainId
            GROUP BY train_id 
        )
        """,
        nativeQuery = true
    )
    fun findLastByTrainId(
        @Param("trainId") trainId: Int
    ): Trip?
}
