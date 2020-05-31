package br.com.woodriver.rpg.usecases.character

import br.com.woodriver.rpg.domain.player.Character
import br.com.woodriver.rpg.gateway.repository.CharacterRepository
import org.springframework.stereotype.Component

@Component
class Top10BestPlayersUseCase(val characterRepository: CharacterRepository) {

    fun execute() : List<Character>{
        return characterRepository.findTop10ByOrderByExpDesc()
    }
}