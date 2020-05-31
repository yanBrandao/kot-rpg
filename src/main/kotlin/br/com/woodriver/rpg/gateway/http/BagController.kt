package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domain.Bag
import br.com.woodriver.rpg.domain.player.Player
import br.com.woodriver.rpg.usecases.bag.GetItemsByPlayerUseCase
import br.com.woodriver.rpg.usecases.bag.InsertItemIntoBagPlayerUseCase
import br.com.woodriver.rpg.usecases.bag.SellPlayerItemUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("bags")
class BagController(val insertItemIntoBagPlayerUseCase: InsertItemIntoBagPlayerUseCase,
                    val getItemsByPlayerUseCase: GetItemsByPlayerUseCase,
                    val sellPlayerItemUseCase: SellPlayerItemUseCase) {

    @PostMapping
    fun insertItem(@RequestBody bag: Bag): Bag{
        return insertItemIntoBagPlayerUseCase.execute(bag)
    }

    @GetMapping("player/{playerKey}")
    fun getItemsByPlayer(pageable: Pageable, @PathVariable playerKey: Long): Page<Bag> {
        return getItemsByPlayerUseCase.execute(pageable, playerKey);
    }

    @DeleteMapping("player/{playerKey}/{itemKey}")
    fun sellItem(@RequestHeader quantity: Int, @PathVariable playerKey: Long,
                 @PathVariable itemKey: Long): Player {
        return sellPlayerItemUseCase.execute(playerKey,
                itemKey, quantity)
    }
}