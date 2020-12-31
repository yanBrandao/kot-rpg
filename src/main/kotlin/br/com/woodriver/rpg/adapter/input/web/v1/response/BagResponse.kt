package br.com.woodriver.rpg.adapter.input.web.v1.response

import org.apache.logging.log4j.util.Strings
import java.math.BigDecimal

data class BagResponse(
        val color: String,
        val slots: Int,
        val items: List<Item> = arrayListOf()
) {
    data class Item(
            val name: String = Strings.EMPTY,
            val quantity: Int = 0,
            val weight: BigDecimal = BigDecimal.ZERO,
            val totalWeight: BigDecimal = BigDecimal.ZERO
    )
}