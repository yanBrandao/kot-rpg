package br.com.woodriver.rpg.usecases.player

import br.com.woodriver.rpg.domains.Player
import br.com.woodriver.rpg.exceptions.KeyCannotBeZeroException
import br.com.woodriver.rpg.exceptions.PlayerAlreadyCreatedException
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class CreateOrUpdatePlayerUseCase(val playerRepository: PlayerRepository) {

    fun execute(newPlayer: Player, isUpdate: Boolean = false) : Player{
        val isPlayerAlreadyCreated = playerRepository.findFirstByKey(newPlayer.key) != null
        if (isUpdate && 0L == newPlayer.key)
            throw KeyCannotBeZeroException("Key is empty")

        if(!isUpdate && isPlayerAlreadyCreated)
            throw PlayerAlreadyCreatedException("Cannot create player with same key.")
        return playerRepository.save(newPlayer)
    }
}