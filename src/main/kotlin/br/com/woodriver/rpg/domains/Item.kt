package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.PositionType
import br.com.woodriver.rpg.domains.types.RarityType
import javax.persistence.*

@Entity
@Table(name = "KOR_ITEM")
class Item(
        @Id
        @Column(name = "ITM_ID", unique = true, nullable = false)
        var key: Long,
        @Column(name = "ITM_NAME")
        val name: String,
        @Column(name = "ITM_WEIGHT")
        val weight: Double,
        @Column(name = "ITM_PRICE")
        val price: Double,
        @Column(name = "ITM_POSITION")
        val position: PositionType,
        @Column(name = "ITM_RARITY")
        val rarity: RarityType,
        @ManyToMany(mappedBy = "bag")
        var players: List<Player> = mutableListOf()) {
}