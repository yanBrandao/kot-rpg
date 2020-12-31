package br.com.woodriver.rpg.adapter.input.web.v1.controller

import br.com.woodriver.rpg.adapter.input.web.v1.api.BagAPI
import br.com.woodriver.rpg.adapter.input.web.v1.converter.toDomain
import br.com.woodriver.rpg.adapter.input.web.v1.converter.toResponse
import br.com.woodriver.rpg.adapter.input.web.v1.request.AddItemRequest
import br.com.woodriver.rpg.adapter.input.web.v1.request.BagRequest
import br.com.woodriver.rpg.adapter.input.web.v1.request.ItemRequest
import br.com.woodriver.rpg.adapter.input.web.v1.response.BagResponse
import br.com.woodriver.rpg.application.port.input.AddBagItemUseCase
import br.com.woodriver.rpg.application.port.input.CreateBagUseCase
import org.springframework.web.bind.annotation.RestController

@RestController
class BagController(
    val addBagItemUseCase: AddBagItemUseCase,
    val createBagUseCase: CreateBagUseCase
): BagAPI {
    override fun getBag(): BagResponse {
        TODO("Not yet implemented")
    }

    override fun createBag(bagRequest: BagRequest): BagResponse {
        return createBagUseCase.execute(bagRequest.toDomain()).toResponse()
    }

    override fun addItem(item: AddItemRequest, playerId: Long): BagResponse {
        return addBagItemUseCase.execute(item.toDomain(), playerId).toResponse()
    }

    override fun removeItem(item: ItemRequest): BagResponse {
        TODO("Not yet implemented")
    }
}