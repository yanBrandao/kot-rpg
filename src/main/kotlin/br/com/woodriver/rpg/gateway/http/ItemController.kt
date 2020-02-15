package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domains.Item
import br.com.woodriver.rpg.usecases.item.CreateItemUseCase
import br.com.woodriver.rpg.usecases.item.GetAllPaginatedUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("items")
class ItemController(
        val getAllPaginatedUseCase: GetAllPaginatedUseCase,
        val createItemUseCase: CreateItemUseCase) {

    @PostMapping(name = "Create an item")
    fun postItem(@RequestBody item: Item): Item{
        return createItemUseCase.execute(item)
    }

    @GetMapping(name = "Get all items")
    fun getItems(pageable: Pageable): Page<Item> {
        return getAllPaginatedUseCase.execute(pageable)
    }
}