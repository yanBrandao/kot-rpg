package br.com.woodriver.rpg.domain

import br.com.woodriver.rpg.domain.utils.compositekeys.BagId
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.Min

@Entity
@Table(name = "KOR_BAG")
class Bag() {
     @EmbeddedId
     lateinit var bagId: BagId
     @Column(name = "BAG_IS_EQUIPPED")
     var isEquipped: Boolean = false
     @Min(1)
     @Column(name = "BAG_QUANTITY")
     var quantity: Int = 1

    constructor(bagId: BagId, isEquipped: Boolean, quantity: Int) : this(){
        this.bagId = bagId
        this.isEquipped = isEquipped
        this.quantity = quantity
    }
}