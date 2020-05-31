package br.com.woodriver.rpg.usecases.character

import br.com.woodriver.rpg.gateway.repository.CharacterRepository
import org.springframework.stereotype.Component

@Component
class DeletePlayerUseCase(val characterRepository: CharacterRepository) {

    fun execute(key: Long){
        return characterRepository.deleteById(key)
    }
}