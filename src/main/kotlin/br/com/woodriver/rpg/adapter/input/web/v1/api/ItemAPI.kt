package br.com.woodriver.rpg.adapter.input.web.v1.api

import br.com.woodriver.rpg.adapter.input.web.v1.request.ItemRequest
import br.com.woodriver.rpg.adapter.input.web.v1.response.BagResponse
import br.com.woodriver.rpg.adapter.input.web.v1.response.ItemResponse
import org.springframework.web.bind.annotation.*

@RequestMapping(value = ["items"])
interface ItemAPI {

    @GetMapping
    fun getItem(): ItemResponse

    @PostMapping
    fun createItem(@RequestBody item: ItemRequest): ItemResponse

    @DeleteMapping
    fun removeItem(item: ItemRequest): ItemResponse
}