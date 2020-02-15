package br.com.woodriver.rpg.usecases.player

import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class DeletePlayerUseCase(val playerRepository: PlayerRepository) {

    fun execute(key: Long){
        return playerRepository.deleteById(key)
    }
}