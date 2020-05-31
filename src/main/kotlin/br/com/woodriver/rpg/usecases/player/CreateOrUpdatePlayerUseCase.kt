package br.com.woodriver.rpg.usecases.player

import br.com.woodriver.rpg.domain.player.Player
import br.com.woodriver.rpg.domain.player.PlayerEditRequest
import br.com.woodriver.rpg.exceptions.KeyCannotBeZeroException
import br.com.woodriver.rpg.gateway.mapper.PlayerMapper
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class CreateOrUpdatePlayerUseCase(val playerRepository: PlayerRepository) {

    fun execute(newPlayer: PlayerEditRequest, isUpdate: Boolean = false, key: Long? = null) : Player {
        val player = PlayerMapper().playerEditToEntity(newPlayer)
        if(key != null)
            player.key = key
        if (isUpdate && key == null)
            throw KeyCannotBeZeroException("Key is empty")
        return playerRepository.save(player)
    }
}