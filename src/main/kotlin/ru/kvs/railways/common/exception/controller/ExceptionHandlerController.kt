package ru.kvs.railways.common.exception.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.kvs.railways.common.exception.ErrorMessage
import java.time.LocalDateTime

@ControllerAdvice
class ExceptionHandlerController : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
           ErrorMessage(
               timestamp = LocalDateTime.now(),
               status = HttpStatus.BAD_REQUEST,
               message = ex.bindingResult.globalError?.defaultMessage ?: ""
           )
        )
    }
}
