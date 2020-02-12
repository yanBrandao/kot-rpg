package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domains.BlizzardItem
import br.com.woodriver.rpg.domains.Item
import br.com.woodriver.rpg.usecase.item.CreateItemUseCase
import br.com.woodriver.rpg.usecase.item.GetAllPaginatedUseCase
import br.com.woodriver.rpg.usecase.item.GetBlizzardItemsUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("items")
class ItemController(
        val getAllPaginatedUseCase: GetAllPaginatedUseCase,
        val createItemUseCase: CreateItemUseCase,
        val getBlizzardItemsUseCase: GetBlizzardItemsUseCase) {

    @GetMapping("/blizzard/{itemId}")
    fun item(@PathVariable itemId: String): BlizzardItem{
        return getBlizzardItemsUseCase.execute(itemId)
    }

    @PostMapping(name = "Create an item")
    fun postItem(@RequestBody item: Item): Item{
        return createItemUseCase.execute(item)
    }

    @GetMapping(name = "Get all items")
    fun getItems(pageable: Pageable): Page<Item> {
        return getAllPaginatedUseCase.execute(pageable)
    }
}