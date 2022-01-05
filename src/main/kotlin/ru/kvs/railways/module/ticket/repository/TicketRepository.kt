package ru.kvs.railways.module.ticket.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kvs.railways.model.ticket.Ticket

@Repository
interface TicketRepository : JpaRepository<Ticket, Long>
