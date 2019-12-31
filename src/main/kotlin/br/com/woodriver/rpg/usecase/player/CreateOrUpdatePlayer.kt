package br.com.woodriver.rpg.usecase.player

import br.com.woodriver.rpg.domain.Player
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class CreateOrUpdatePlayer(val playerRepository: PlayerRepository) {

    fun execute(newPlayer: Player) : Player{
        return playerRepository.save(newPlayer)
    }
}