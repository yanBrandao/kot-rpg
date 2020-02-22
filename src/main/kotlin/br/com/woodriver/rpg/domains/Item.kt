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
        var name: String = "No Name"
        @Column(name = "ITM_WEIGHT")
        var weight: Double = 1.0
        @Column(name = "ITM_PRICE")
        var price: Double = 1.0
        @Column(name = "ITM_POSITION")
        var position: PositionType = PositionType.NONE
        @Column(name = "ITM_RARITY")
        var rarity: RarityType = RarityType.COMMON
        @Column(name = "ITM_ICON")
        var icon: String = "No Icon"

        constructor(key: Long, name: String, weight: Double,
                    price: Double, position: PositionType, rarity: RarityType,
                    icon: String) : this(){
                this.key = key
                this.name = name
                this.weight = weight
                this.price = price
                this.position = position
                this.rarity = rarity
                this.icon = icon
        }
}