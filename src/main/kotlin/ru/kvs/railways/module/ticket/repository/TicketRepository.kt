package ru.kvs.railways.module.ticket.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.kvs.railways.model.seat.SeatId
import ru.kvs.railways.model.ticket.Ticket

@Repository
interface TicketRepository : JpaRepository<Ticket, Long> {
    @Query(
        """
        SELECT * FROM t_ticket
        ORDER BY purchase_date DESC
        LIMIT 1;
        """,
        nativeQuery = true
    )
    fun findLastBySeatIdOrderByPurchaseDate(seatId: SeatId): Ticket?
}
