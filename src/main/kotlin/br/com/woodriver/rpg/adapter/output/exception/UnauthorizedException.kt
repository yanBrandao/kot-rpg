package br.com.woodriver.rpg.adapter.output.exception

import java.lang.RuntimeException

class UnauthorizedException(override val message: String): RuntimeException(message) {

}