package br.com.woodriver.rpg.domains

import javax.persistence.Id
import kotlin.random.Random
import kotlin.random.nextInt

class Enemy(@Id
            private val key: Long,
            private val name: String,
            private val level: Int,
            private val drop: List<Item> = listOf()) {

    fun dropItems(): List<Item> {
        var quantityOfItemsToDrop = Random.nextInt(IntRange(1, drop.size))
        var itemsToDrop = arrayListOf<Item>()
        var count = 0;
        for (item: Item in drop) {
            if (count < quantityOfItemsToDrop) {
                itemsToDrop.add(item)
                count++;
            }
        }
        return itemsToDrop
    }
}