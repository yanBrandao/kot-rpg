package br.com.woodriver.rpg.usecases.player

import br.com.woodriver.rpg.domain.player.Player
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class Top10BestPlayersUseCase(val playerRepository: PlayerRepository) {

    fun execute() : List<Player>{
        return playerRepository.findTop10ByOrderByExpDesc()
    }
}