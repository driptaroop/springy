package org.dripto.springy.web.config

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class SampleControllerAdvice {

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    fun illegalArgument(e: IllegalArgumentException) = object {
            val error = e
            val message = "You don goofed up"
        }
}
