package br.com.woodriver.rpg.adapter.output.database.entity

import br.com.woodriver.rpg.adapter.output.database.keys.ItemsInBagKey
import javax.persistence.*

@Entity
@Table(name = "KOT_ITEMS_IN_BAG")
data class ItemsInBagEntity(
        @EmbeddedId
    val id: ItemsInBagKey = ItemsInBagKey(),
        @ManyToOne
    @MapsId("bagId")
    val bag: BagEntity = BagEntity(),
        @ManyToOne
    @MapsId("itemId")
    val item: ItemEntity = ItemEntity(),
        val quantity: Int = 0
)