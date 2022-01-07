package ru.kvs.railways.module.ticket.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [TicketAvailableValidator::class])
annotation class TicketAvailableValidation(
    val message: String = "Данное место уже выкуплено",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
