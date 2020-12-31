package br.com.woodriver.rpg.application.domain

data class Power(
    val value: Int = 0,
    val type: PowerType = PowerType.OTHER
) {
    enum class PowerType{
        OTHER, MANA, RAGE, ENERGY, RUNE
    }
}