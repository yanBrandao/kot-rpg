package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domain.Item
import br.com.woodriver.rpg.usecases.item.CreateItemUseCase
import br.com.woodriver.rpg.usecases.item.GetAllPaginatedUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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
    fun getItems(@RequestParam(defaultValue = "0") page: Int,
                 @RequestParam(defaultValue = "10") size: Int = 10,
                 @RequestParam(defaultValue = "ASC") direction: Sort.Direction,
                 @RequestParam(defaultValue = "KEY") ItemProperties: Item.Companion.ItemProperties): Page<Item>? {
        var pageable = PageRequest.of(page, size, direction, ItemProperties.toString().toLowerCase())
        return getAllPaginatedUseCase.execute(pageable)
    }
}