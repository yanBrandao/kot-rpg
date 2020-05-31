package br.com.woodriver.rpg.usecases.bag

import br.com.woodriver.rpg.domain.player.Player
import br.com.woodriver.rpg.exceptions.IncorrectItemQuantityException
import br.com.woodriver.rpg.gateway.repository.BagRepository
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class SellPlayerItemUseCase(val bagRepository: BagRepository,
                            val playerRepository: PlayerRepository) {

    fun execute(playerKey: Long, itemKey: Long, quantity: Int): Player {
        val bagItem = bagRepository.findFirstByBagIdBagItmIdKeyAndBagIdBagPlrIdKey(itemKey,
                playerKey)
        if(quantity > bagItem.quantity)
            throw IncorrectItemQuantityException("Impossible to sell more items then you have.")

        if(quantity == bagItem.quantity)
            bagRepository.delete(bagItem)
        else{
            bagItem.quantity -= quantity
            bagRepository.saveAndFlush(bagItem)
        }
        val player = playerRepository.findFirstByKey(playerKey)!!

        player.money += quantity * bagItem.bagId.bagItmId.price

        return playerRepository.saveAndFlush(player)
    }
}