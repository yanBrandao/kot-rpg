package br.com.woodriver.blizzard.exceptions

import java.lang.RuntimeException

class IconEmptyException(override val message: String): RuntimeException(message) {
}