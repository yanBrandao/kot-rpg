package br.com.woodriver.rpg.gateway.mapper

import br.com.woodriver.rpg.domain.player.Character
import br.com.woodriver.rpg.domain.player.CharacterEditRequest

class CharacterMapper {

    fun convertEntityToRequest(character: Character): CharacterEditRequest{
        val characterDTO = CharacterEditRequest()
        characterDTO.name = character.name
        characterDTO.exp = character.exp
        characterDTO.userId = character.userId
        characterDTO.money = character.money
        characterDTO.gender = character.gender
        characterDTO.race = character.race
        return characterDTO
    }

    fun characterEditToEntity(characterEditRequest: CharacterEditRequest): Character {
        val character = Character()
        character.name = characterEditRequest.name
        character.exp = characterEditRequest.exp
        character.gender = characterEditRequest.gender
        character.money = characterEditRequest.money
        character.race = characterEditRequest.race
        character.userId = characterEditRequest.userId
        return character
    }
}