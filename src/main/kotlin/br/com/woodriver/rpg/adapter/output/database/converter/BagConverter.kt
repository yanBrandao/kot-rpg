package br.com.woodriver.rpg.adapter.output.database.converter

import br.com.woodriver.rpg.adapter.output.database.entity.BagEntity
import br.com.woodriver.rpg.adapter.output.database.entity.ItemEntity
import br.com.woodriver.rpg.adapter.output.database.entity.ItemsInBagEntity
import br.com.woodriver.rpg.application.domain.Bag
import br.com.woodriver.rpg.application.domain.Item

fun Bag.toEntity(): BagEntity =
        BagEntity(
            color = this.color, slots = this.slots
        )

fun Item.toEntity(): ItemEntity =
        ItemEntity(
            name = this.name,
                weight = this.weight
        )

fun BagEntity.toDomain(): Bag =
        Bag(
                color = this.color,
                slots = this.slots
        )

fun ItemEntity.toDomain(): Item =
        Item(
                name = this.name,
                weight = this.weight
        )

fun ItemEntity.toDomain(quantity: Int): Item =
        Item(
                name = this.name,
                quantity = quantity
        )

fun List<ItemsInBagEntity>.toDomain(): Bag =
        Bag(
                color = this[0].bag.color,
                slots = this[0].bag.slots,
                items = this.map {
                    it.toItemDomain()
                } as ArrayList<Item>
        )

fun ItemsInBagEntity.toItemDomain(): Item =
        Item(
                name = this.item.name,
                quantity = this.quantity,
                weight = this.item.weight
        )