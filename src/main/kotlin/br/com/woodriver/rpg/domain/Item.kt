package br.com.woodriver.rpg.domain

import br.com.woodriver.rpg.domain.utils.types.PositionType
import br.com.woodriver.rpg.domain.utils.types.RarityType
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "KOR_ITEM")
class Item() {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        @OneToMany(
                mappedBy = "bagId.bagItmId",
                cascade = [CascadeType.ALL],
                orphanRemoval = true
        )
        @JsonIgnore
        var bags: Set<Bag> = emptySet()

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

        companion object{
                enum class ItemProperties {
                        KEY, NAME, WEIGHT,
                        PRICE, POSITION,
                        RARITY
                }
        }
}