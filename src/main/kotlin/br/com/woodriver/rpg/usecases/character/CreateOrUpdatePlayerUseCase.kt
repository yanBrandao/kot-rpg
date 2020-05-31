package br.com.woodriver.rpg.usecases.character

import br.com.woodriver.rpg.domain.player.Character
import br.com.woodriver.rpg.domain.player.CharacterEditRequest
import br.com.woodriver.rpg.exceptions.KeyCannotBeZeroException
import br.com.woodriver.rpg.gateway.mapper.CharacterMapper
import br.com.woodriver.rpg.gateway.repository.CharacterRepository
import org.springframework.stereotype.Component

@Component
class CreateOrUpdatePlayerUseCase(val characterRepository: CharacterRepository) {

    fun execute(newCharacter: CharacterEditRequest, isUpdate: Boolean = false, key: Long? = null) : Character {
        val player = CharacterMapper().characterEditToEntity(newCharacter)
        if(key != null)
            player.key = key
        if (isUpdate && key == null)
            throw KeyCannotBeZeroException("Key is empty")
        return characterRepository.save(player)
    }
}