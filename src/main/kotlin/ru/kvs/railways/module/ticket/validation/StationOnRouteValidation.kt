package ru.kvs.railways.module.ticket.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [StationOnRouteValidator::class])
annotation class StationOnRouteValidation(
    val message: String = "Выбранный маршрут не проходит через выбранную станцию",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
