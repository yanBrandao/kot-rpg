package br.com.woodriver.rpg.adapter.input.web.v1.api

import br.com.woodriver.rpg.adapter.input.web.v1.request.AddItemRequest
import br.com.woodriver.rpg.adapter.input.web.v1.request.BagRequest
import br.com.woodriver.rpg.adapter.input.web.v1.request.ItemRequest
import br.com.woodriver.rpg.adapter.input.web.v1.response.BagResponse
import org.springframework.web.bind.annotation.*

@RequestMapping(value = ["bags"])
interface BagAPI {

    @GetMapping
    fun getBag(): BagResponse

    @PostMapping
    fun createBag(@RequestBody bagRequest: BagRequest): BagResponse

    @PostMapping(path = ["/{playerId}/player"])
    fun addItem(@RequestBody item: AddItemRequest, @PathVariable playerId: Long): BagResponse

    @DeleteMapping
    fun removeItem(item: ItemRequest): BagResponse
}