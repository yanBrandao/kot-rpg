package br.com.woodriver.rpg.adapter.input.web.v1.request

import org.apache.logging.log4j.util.Strings
import java.math.BigDecimal

data class ItemRequest(
        val name: String = Strings.EMPTY,
        val weight: BigDecimal = BigDecimal.ZERO
)