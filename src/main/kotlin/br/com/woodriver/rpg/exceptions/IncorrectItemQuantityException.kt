package br.com.woodriver.rpg.exceptions

import java.lang.RuntimeException

class IncorrectItemQuantityException(override val message: String): RuntimeException(message) {
}