package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domains.BlizzardItem
import br.com.woodriver.rpg.usecase.item.GetBlizzardItemsUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("items")
class ItemController(val getBlizzardItemsUseCase: GetBlizzardItemsUseCase) {

    @GetMapping("/{itemId}")
    fun item(@PathVariable itemId: String): BlizzardItem{
        return getBlizzardItemsUseCase.execute(itemId)
    }

}