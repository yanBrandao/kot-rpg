package br.com.woodriver.rpg.adapter.input.web.v1.request

import org.apache.logging.log4j.util.Strings
import java.math.BigDecimal

data class BagRequest(
        val color: String = Strings.EMPTY,
        val slots: Int = 0
)