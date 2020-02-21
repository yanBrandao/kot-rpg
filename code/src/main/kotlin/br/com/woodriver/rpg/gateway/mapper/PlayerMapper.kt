package br.com.woodriver.rpg.gateway.mapper

import br.com.woodriver.rpg.domains.Player
import br.com.woodriver.rpg.gateway.dto.PlayerDTO

class PlayerMapper {

    fun ConvertEntityToDTO(player: Player): PlayerDTO{
        var playerDTO = PlayerDTO()
        playerDTO.key = player.key
        playerDTO.name = player.name
        playerDTO.bags = player.bags
        playerDTO.effects = player.effects
        playerDTO.email = player.email
        playerDTO.level = player.playerLevel().toInt()
        playerDTO.exp = player.exp
        return playerDTO
    }
}