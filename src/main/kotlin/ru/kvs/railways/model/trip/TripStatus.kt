package ru.kvs.railways.model.trip

@Suppress("UnusedPrivateMember")
enum class TripStatus(status: String) {
    SCHEDULED("Запланирован"),
    ON_THE_WAY("В пути"),
    COMPLETED("Завершен")
}
