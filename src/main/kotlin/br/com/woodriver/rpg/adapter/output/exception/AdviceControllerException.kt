package br.com.woodriver.rpg.adapter.output.exception

import br.com.woodriver.rpg.adapter.output.logging.logger
import br.com.woodriver.rpg.adapter.output.logging.objectToJson
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*


@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class AdviceControllerException : ResponseEntityExceptionHandler() {

    private val logger = logger<AdviceControllerException>()

    @ExceptionHandler
    fun genericHandleException(ex: Exception, request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(
                message = "Internal Error",
                details = ex.message!!
        )
        logger.error(errorDetails.objectToJson())
        return ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(ex: UnauthorizedException, request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorDetails = ErrorsDetails(
            message = "Critical Error",
            details = ex.message
        )
        logger.error(errorDetails.objectToJson())
        return ResponseEntity(errorDetails, HttpStatus.UNAUTHORIZED)
    }
}