package ru.kvs.railways.module.ticket.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [TripValidator::class])
annotation class TripValidation(
    val message: String = "Данный рейс уже ушел, покупка билетов невозможна",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
