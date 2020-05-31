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
                ex.message
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(IncorrectItemQuantityException::class)])
    fun handleIncorrectItemQuantity(ex: IncorrectItemQuantityException,request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(Date(),
                "Selling error",
                ex.message
        )
        return ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleGenericException(ex: Exception, request: WebRequest): ResponseEntity<ErrorsDetails>{
        val errorsDetails = ErrorsDetails(Date(),
                "Internal Error",
                ex.message!!)
        return ResponseEntity(errorsDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}