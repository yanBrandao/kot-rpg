package br.com.woodriver.rpg.usecase.player

import br.com.woodriver.rpg.domains.Player
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class CreateOrUpdatePlayerUseCase(val playerRepository: PlayerRepository) {

    fun execute(newPlayer: Player) : Player{
        return playerRepository.save(newPlayer)
    }
}