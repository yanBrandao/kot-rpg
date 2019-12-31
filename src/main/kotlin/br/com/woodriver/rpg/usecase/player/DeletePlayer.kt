package br.com.woodriver.rpg.usecase.player

import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class DeletePlayer(val playerRepository: PlayerRepository) {

    fun execute(key: String){
        return playerRepository.deleteById(key)
    }
}