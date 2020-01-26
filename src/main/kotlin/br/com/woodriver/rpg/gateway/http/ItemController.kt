package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domains.BlizzardItem
import br.com.woodriver.rpg.domains.Player
import br.com.woodriver.rpg.usecase.item.GetBlizzardItemsUseCase
import br.com.woodriver.rpg.usecase.player.CreateOrUpdatePlayerUseCase
import br.com.woodriver.rpg.usecase.player.DeletePlayerUseCase
import br.com.woodriver.rpg.usecase.player.GetAllPlayersUseCase
import br.com.woodriver.rpg.usecase.player.Top10BestPlayersUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("items")
class ItemController(val getBlizzardItemsUseCase: GetBlizzardItemsUseCase) {

    @GetMapping("/{itemId}")
    fun item(@PathVariable itemId: String): BlizzardItem{
        return getBlizzardItemsUseCase.execute(itemId)
    }

}