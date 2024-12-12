package br.com.woodriver.rpg.adapter.input.token.aspect

import br.com.woodriver.rpg.adapter.input.token.TokenUserValidation
import br.com.woodriver.rpg.adapter.output.exception.UnauthorizedException
import br.com.woodriver.rpg.adapter.output.logging.logger
import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTDecodeException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.core.annotation.AnnotationUtils
import java.util.*
import kotlin.jvm.Throws

class TokenValidationAspect {

    private val logger = logger<TokenValidationAspect>()

    @Pointcut("execution(* br.com.woodriver.rpg.adapter.input.web.v1.api.* (..))")
    fun repositoryClassMethods() {
        logger.info("Pointcut Controller execution done.")
    }

    @Around("repositoryClassMethods()")
    @Throws(Exception::class)
    fun tokenValidation(pjp: ProceedingJoinPoint): Any {
        val methodSignature: MethodSignature = pjp.signature as MethodSignature
        if(AnnotationUtils.findAnnotation(methodSignature.method, TokenUserValidation::class.java) != null) {
            val token = getParameter(pjp, AUTH_HEADER_NAME)
            try {
                val jwt = JWT.decode(token)

                val tokenUserId = jwt.getClaim(CLAIM_USER_ID).asString()
                val uriUserId = getParameter(pjp, USERID_PATH_PARAM)

                if (tokenUserId != uriUserId) throw UnauthorizedException(TOKEN_USER_ID_ERROR)
            } catch (e: JWTDecodeException) {
                throw UnauthorizedException(TOKEN_INVALID_ERROR)
            }
        }
        return pjp.proceed()
    }

    private fun getParameter(joinPoint: ProceedingJoinPoint, parameterName: String): String {
        val method = joinPoint.signature as MethodSignature
        lateinit var valueParameter: String
        method.parameterNames.mapIndexed { i, it ->
            if (it == parameterName)
                valueParameter = joinPoint.args[i] as String
        }

        return valueParameter
    }

    companion object {
        const val AUTH_HEADER_NAME = "authToken"
        const val TOKEN_INVALID_ERROR = "Token is not a JWT String"
        const val TOKEN_USER_ID_ERROR = "User ID from token doesn't belong to User ID requested!"
        const val CLAIM_USER_ID = "userID"
        const val USERID_PATH_PARAM = "userID"
    }
}
