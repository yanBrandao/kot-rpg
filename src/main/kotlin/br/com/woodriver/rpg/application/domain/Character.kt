package br.com.woodriver.rpg.application.domain

import org.apache.logging.log4j.util.Strings

data class Character(
    val name: String = Strings.EMPTY,
    val attributes: Attributes = Attributes(),
    val gameClass: GameClass = GameClass(),
    val health: Int,
    val lifeShield: Int,
    val power: Power = Power(),
    val level: Int = 1,
    val karma: Int = 0,
    val bag: Bag = Bag(),
    val equipment: Equipment = Equipment()
)