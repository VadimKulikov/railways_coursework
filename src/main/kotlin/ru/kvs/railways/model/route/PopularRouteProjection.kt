package ru.kvs.railways.model.route

interface PopularRouteProjection {
    fun getRouteId(): Long
    fun getName(): String
    fun getTicketsSold(): Long
}
