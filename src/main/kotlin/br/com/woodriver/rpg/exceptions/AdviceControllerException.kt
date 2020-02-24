package br.com.woodriver.rpg.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
class AdviceControllerException : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [(UsernameOrPasswordException::class)])
    fun handleUserAlreadyExists(ex: UsernameOrPasswordException,request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(Date(),
                "Login Failed",
                ex.message!!
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }
}