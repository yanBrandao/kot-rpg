package br.com.woodriver.rpg.adapter.input.web.v1.converter

import br.com.woodriver.rpg.adapter.input.web.v1.request.AddItemRequest
import br.com.woodriver.rpg.adapter.input.web.v1.request.BagRequest
import br.com.woodriver.rpg.adapter.input.web.v1.request.ItemRequest
import br.com.woodriver.rpg.adapter.input.web.v1.response.BagResponse
import br.com.woodriver.rpg.adapter.input.web.v1.response.ItemResponse
import br.com.woodriver.rpg.adapter.output.database.entity.BagEntity
import br.com.woodriver.rpg.application.domain.Bag
import br.com.woodriver.rpg.application.domain.Item
import java.math.BigDecimal

fun ItemRequest.toDomain(): Item =
        Item(
                name = this.name,
                weight = this.weight
        )

fun Item.toResponse(): ItemResponse =
        ItemResponse(
                name = this.name,
                weight = this.weight
        )

fun Bag.toResponse(): BagResponse =
        BagResponse(
                color = this.color,
                slots = this.slots,
                items = this.items.map {
                    BagResponse.Item(
                            name = it.name,
                            quantity = it.quantity,
                            weight = it.weight,
                            totalWeight = it.weight.multiply(it.quantity)
                    )
                }
        )

fun BigDecimal.multiply(value: Int): BigDecimal =
        this.multiply(BigDecimal(value))

fun BagRequest.toDomain(): Bag =
        Bag(
                color = this.color,
                slots = this.slots
        )

fun createBag(bag: BagEntity, items: ArrayList<Item>): Bag =
        Bag(
                color = bag.color,
                slots = bag.slots,
                items = items
        )

fun AddItemRequest.toDomain(): Item =
        Item(
                name = this.name,
                quantity = this.quantity
        )