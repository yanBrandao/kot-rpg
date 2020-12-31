package br.com.woodriver.rpg.adapter.input.web.v1.request

import org.apache.logging.log4j.util.Strings

data class AddItemRequest (
    val name: String = Strings.EMPTY,
    val quantity: Int = 0
)