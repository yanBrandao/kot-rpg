package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.PositionType
import br.com.woodriver.rpg.domains.types.RarityType
import org.springframework.data.annotation.Id


class Item(@Id val key: String,
           val name: String,
           val weight: Double,
           val price: Double,
           val position: PositionType,
           val rarity: RarityType,
           val effects: List<Effect>) {
}