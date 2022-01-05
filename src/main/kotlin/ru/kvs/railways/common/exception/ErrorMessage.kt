package ru.kvs.railways.common.exception

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorMessage(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: HttpStatus = HttpStatus.BAD_REQUEST,
    val message: String = ""
)
