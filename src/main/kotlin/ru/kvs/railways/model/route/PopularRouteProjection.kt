package ru.kvs.railways.model.route

interface PopularRouteProjection {
    fun getRouteId(): Long
    fun getTicketsSold(): Long
}
