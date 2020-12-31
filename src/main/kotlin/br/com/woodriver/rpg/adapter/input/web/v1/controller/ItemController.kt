package br.com.woodriver.rpg.adapter.input.web.v1.controller

import br.com.woodriver.rpg.adapter.input.web.v1.api.ItemAPI
import br.com.woodriver.rpg.adapter.input.web.v1.converter.toDomain
import br.com.woodriver.rpg.adapter.input.web.v1.converter.toResponse
import br.com.woodriver.rpg.adapter.input.web.v1.request.ItemRequest
import br.com.woodriver.rpg.adapter.input.web.v1.response.ItemResponse
import br.com.woodriver.rpg.application.port.input.CreateItemUseCase
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController(
    val createItemUseCase: CreateItemUseCase
): ItemAPI {
    override fun getItem(): ItemResponse {
        TODO("Not yet implemented")
    }

    override fun createItem(item: ItemRequest): ItemResponse {
        return createItemUseCase.execute(item.toDomain()).toResponse()
    }

    override fun removeItem(item: ItemRequest): ItemResponse {
        TODO("Not yet implemented")
    }

}