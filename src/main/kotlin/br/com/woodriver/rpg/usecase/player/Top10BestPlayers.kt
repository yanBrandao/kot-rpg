package br.com.woodriver.rpg.usecase.player

import br.com.woodriver.rpg.domains.Player
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class Top10BestPlayers(val playerRepository: PlayerRepository) {

    fun execute() : List<Player>{
        return playerRepository.findTop10ByOrderByLevelDesc()
    }
}