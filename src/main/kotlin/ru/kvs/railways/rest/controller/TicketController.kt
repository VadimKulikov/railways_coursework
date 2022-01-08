package ru.kvs.railways.rest.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kvs.railways.model.ticket.Ticket
import ru.kvs.railways.module.ticket.service.TicketService
import ru.kvs.railways.module.ticket.validation.TicketReturnValidation
import ru.kvs.railways.rest.dto.TicketDTO
import ru.kvs.railways.rest.mapper.TicketMapper
import javax.validation.Valid

@RestController
@RequestMapping("api/ticket")
@Validated
@Tag(
    name = "Билеты",
    description = "Операции для работы с билетами"
)
class TicketController(
    private val ticketService: TicketService,
    private val ticketMapper: TicketMapper
) {
    @PostMapping
    fun save(
        @Valid
        @RequestBody
        ticket: TicketDTO
    ): Ticket = ticketService.save(
        ticketMapper.map(ticket)
    )

    @DeleteMapping("/{ticketId}")
    fun returnTicket(
        @TicketReturnValidation
        @PathVariable
        ticketId: Long
    ) = ticketService.returnTicket(ticketId)
}
