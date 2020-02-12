package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.AbilityType

data class Ability(
              val name: String,
              val typeAbility: AbilityType) {
}