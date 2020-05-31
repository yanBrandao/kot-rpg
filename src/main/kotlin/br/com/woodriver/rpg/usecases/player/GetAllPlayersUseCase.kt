package br.com.woodriver.rpg.usecases.player

import br.com.woodriver.rpg.domain.player.PlayerEditRequest
import br.com.woodriver.rpg.gateway.mapper.PlayerMapper
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class GetAllPlayersUseCase(val playerRepository: PlayerRepository) {

    fun execute(): List<PlayerEditRequest>{
        val listPlayer = playerRepository.findAll()
        val listPlayerDTO = ArrayList<PlayerEditRequest>()
        for(pl in listPlayer){
            listPlayerDTO.add(PlayerMapper().convertEntityToRequest(pl))
        }
        return listPlayerDTO
    }
}