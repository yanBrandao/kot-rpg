package br.com.woodriver.rpg.usecases.player

import br.com.woodriver.rpg.gateway.dto.PlayerResponse
import br.com.woodriver.rpg.gateway.mapper.PlayerMapper
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class GetAllPlayersUseCase(val playerRepository: PlayerRepository) {

    fun execute(): List<PlayerResponse>{
        val listPlayer = playerRepository.findAll()
        val listPlayerDTO = ArrayList<PlayerResponse>()
        for(pl in listPlayer){
            listPlayerDTO.add(PlayerMapper().convertEntityToResponse(pl))
        }
        return listPlayerDTO
    }
}