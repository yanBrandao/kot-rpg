package br.com.woodriver.rpg.application.domain

import org.apache.logging.log4j.util.Strings

data class Effect(
    val name: String = Strings.EMPTY,
    val description: String = Strings.EMPTY,
    val type: EffectType = EffectType.NONE
)