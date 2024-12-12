package br.com.woodriver.rpg.application.domain

import org.apache.logging.log4j.util.Strings
import java.math.BigDecimal

data class Armour(
    val position: String = Strings.EMPTY,
    val isUnique: Boolean = false
)
