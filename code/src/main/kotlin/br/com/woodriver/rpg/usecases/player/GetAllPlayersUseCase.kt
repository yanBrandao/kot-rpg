package br.com.woodriver.rpg.usecases.player

import br.com.woodriver.rpg.gateway.dto.PlayerDTO
import br.com.woodriver.rpg.gateway.mapper.PlayerMapper
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import org.springframework.stereotype.Component

@Component
class GetAllPlayersUseCase(val playerRepository: PlayerRepository) {

    fun execute(): List<PlayerDTO>{
        val listPlayer = playerRepository.findAll()
        val listPlayerDTO = ArrayList<PlayerDTO>()
        for(pl in listPlayer){
            listPlayerDTO.add(PlayerMapper().ConvertEntityToDTO(pl))
        }
        return listPlayerDTO
    }
}