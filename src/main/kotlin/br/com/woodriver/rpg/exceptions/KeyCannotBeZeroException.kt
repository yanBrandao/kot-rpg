package br.com.woodriver.rpg.exceptions

import java.lang.RuntimeException

class KeyCannotBeZeroException(override val message: String): RuntimeException(message) {
}