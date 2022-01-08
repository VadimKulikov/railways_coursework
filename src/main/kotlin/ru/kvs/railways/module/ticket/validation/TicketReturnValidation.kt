package ru.kvs.railways.module.ticket.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [TicketReturnValidator::class])
annotation class TicketReturnValidation(
    val message: String = "Билет можно вернуть как минимум за день до отправления",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
