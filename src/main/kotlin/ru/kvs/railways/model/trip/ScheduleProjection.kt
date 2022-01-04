package ru.kvs.railways.model.trip

import java.time.LocalDateTime

interface ScheduleProjection {
    fun getName(): String
    fun getArrivalTime(): LocalDateTime
    fun getDepartureTime(): LocalDateTime
}
