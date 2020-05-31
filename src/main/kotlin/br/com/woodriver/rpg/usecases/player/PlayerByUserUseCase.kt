package br.com.woodriver.rpg.usecases.player

import br.com.woodriver.rpg.domain.player.Player
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class PlayerByUserUseCase(val playerRepository: PlayerRepository) {

    fun execute(userId: Long): List<Player>{
        return playerRepository.findPlayerByUserId(userId)
    }
}