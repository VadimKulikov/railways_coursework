package ru.kvs.railways.model.ticket

@Suppress("UnusedPrivateMember")
enum class TicketStatus(status: String) {
    CREATED("Создан"),
    RETURNED("Возвращен"),
    USED("Использован")
}
