package br.com.woodriver.rpg.application.domain

import org.apache.logging.log4j.util.Strings
import java.math.BigDecimal

data class Weapon(
    val type: WeaponType = WeaponType.OTHER,
    val isUnique: Boolean = false,
    val damage: Int = 0,
    val element: Element
): Item() {
    enum class WeaponType {
        OTHER, ONE_HAND, TWO_HAND, OFF_HAND
    }
}