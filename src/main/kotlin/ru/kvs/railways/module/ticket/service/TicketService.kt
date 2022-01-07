package ru.kvs.railways.module.ticket.service

import org.springframework.stereotype.Service
import ru.kvs.railways.model.seat.SeatId
import ru.kvs.railways.model.ticket.Ticket
import ru.kvs.railways.module.ticket.repository.TicketRepository

@Service
class TicketService(
    private val ticketRepository: TicketRepository
) {
    fun save(ticket: Ticket): Ticket = ticketRepository.save(ticket)
    fun find(seatId: SeatId) = ticketRepository.findBySeatId(seatId)
}
