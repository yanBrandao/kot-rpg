package br.com.woodriver.rpg.usecases.player

import br.com.woodriver.rpg.domains.Player
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class GetAllPlayersUseCase(val playerRepository: PlayerRepository) {

    fun execute(): List<Player>{
        return playerRepository.findAll()
    }
}