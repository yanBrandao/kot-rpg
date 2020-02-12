package br.com.woodriver.rpg.exceptions

import java.lang.RuntimeException

class IconEmptyException(override val message: String): RuntimeException(message) {
}