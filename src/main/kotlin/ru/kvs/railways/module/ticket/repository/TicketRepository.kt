package ru.kvs.railways.module.ticket.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.kvs.railways.model.ticket.Ticket

@Repository
interface TicketRepository : JpaRepository<Ticket, Long> {
    @Query(
        """
            SELECT * FROM t_ticket tt
            WHERE tt.ticket_status = :ticketStatus
            AND trip_id = :tripId
            AND seat_carriage_type_id = :carriageTypeId
            AND seat_seat_number = :seatNumber
            AND carriage_id = :carriageId
            ORDER BY purchase_date
            LIMIT 1;
        """,
        nativeQuery = true
    )
    fun findFirstBySeatIdAndTicketStatusOrderByPurchaseDateDesc(
        @Param("tripId") tripId: Long,
        @Param("carriageTypeId") seatCarriageTypeId: Int,
        @Param("seatNumber") seatNumber: Int,
        @Param("carriageId") carriageId: Int,
        @Param("ticketStatus") ticketStatus: String
    ): Ticket?
}
