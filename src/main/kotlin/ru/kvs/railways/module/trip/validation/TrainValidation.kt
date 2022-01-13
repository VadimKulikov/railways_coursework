package ru.kvs.railways.module.trip.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [TrainValidator::class])
annotation class TrainValidation(
    val message: String = "Выбранный поезд находится в пути",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
