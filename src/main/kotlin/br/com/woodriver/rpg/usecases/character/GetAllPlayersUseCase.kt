package br.com.woodriver.rpg.usecases.character

import br.com.woodriver.rpg.domain.player.CharacterEditRequest
import br.com.woodriver.rpg.gateway.mapper.CharacterMapper
import br.com.woodriver.rpg.gateway.repository.CharacterRepository
import org.springframework.stereotype.Component

@Component
class GetAllPlayersUseCase(val characterRepository: CharacterRepository) {

    fun execute(): List<CharacterEditRequest>{
        val listPlayer = characterRepository.findAll()
        val listPlayerDTO = ArrayList<CharacterEditRequest>()
        for(pl in listPlayer){
            listPlayerDTO.add(CharacterMapper().convertEntityToRequest(pl))
        }
        return listPlayerDTO
    }
}