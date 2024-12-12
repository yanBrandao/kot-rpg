package br.com.woodriver.rpg.adapter.output.database.keys

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class ItemsInBagKey(
        @Column(name = "IIB_BAG_ID")
        val bagId: Long = 0L,
        @Column(name = "IIB_ITM_ID")
        val itemId: Long = 0L
): Serializable
