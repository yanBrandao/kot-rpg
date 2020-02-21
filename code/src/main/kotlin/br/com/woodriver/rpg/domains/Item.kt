package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.PositionType
import br.com.woodriver.rpg.domains.types.RarityType
import javax.persistence.*

@Entity
@Table(name = "KOR_ITEM")
class Item() {
        @Id
        @GeneratedValue
        @Column(name = "ITM_ID", unique = true, nullable = false)
        var key: Long = 0
        @Column(name = "ITM_NAME")
        val name: String = "No Name"
        @Column(name = "ITM_WEIGHT")
        val weight: Double = 1.0
        @Column(name = "ITM_PRICE")
        val price: Double = 1.0
        @Column(name = "ITM_POSITION")
        val position: PositionType = PositionType.NONE
        @Column(name = "ITM_RARITY")
        val rarity: RarityType = RarityType.COMMON
        @Column(name = "ITM_ICON")
        var icon: String = "No Icon"
}