package br.com.woodriver.rpg.application.domain

import org.apache.logging.log4j.util.Strings
import java.math.BigDecimal

abstract class Item(
        val name: String = Strings.EMPTY,
        var quantity: Int = 0,
        val weight: BigDecimal = BigDecimal.ZERO,
        val bonus: Bonus = Bonus()
) {
    companion object {
        const val ITEM_IDENTIFICATION = "ItemId"
    }
}