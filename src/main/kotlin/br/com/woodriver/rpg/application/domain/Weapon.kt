package br.com.woodriver.rpg.application.domain

data class Weapon(
    val type: WeaponType = WeaponType.OTHER,
    val isUnique: Boolean = false,
    val damage: Int = 0,
    val element: Element = Element.NONE,
) {
    enum class WeaponType {
        OTHER, ONE_HAND, TWO_HAND, OFF_HAND
    }
}
