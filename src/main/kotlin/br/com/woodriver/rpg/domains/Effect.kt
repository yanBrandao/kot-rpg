package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.EffectType

class Effect(val name:String,
             val value: Double,
             val type: EffectType,
             val range: Double) {
}