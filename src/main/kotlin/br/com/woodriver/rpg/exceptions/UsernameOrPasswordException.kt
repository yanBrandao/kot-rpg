package br.com.woodriver.rpg.exceptions

import java.lang.RuntimeException

class UsernameOrPasswordException(override val message: String):RuntimeException(message) {

}