package br.com.woodriver.rpg.usecases.character

import br.com.woodriver.rpg.domain.player.Character
import br.com.woodriver.rpg.gateway.repository.CharacterRepository
import org.springframework.stereotype.Component

@Component
class CharacterByUserUseCase(val characterRepository: CharacterRepository) {

    fun execute(userId: Long): List<Character>{
        return characterRepository.findCharacterByUserKey(userId)
    }
}