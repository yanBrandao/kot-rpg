package br.com.woodriver.rpg.gateway.mapper

import br.com.woodriver.rpg.domain.player.Player
import br.com.woodriver.rpg.domain.player.PlayerEditRequest

class PlayerMapper {

    fun convertEntityToRequest(player: Player): PlayerEditRequest{
        val playerDTO = PlayerEditRequest()
        playerDTO.name = player.name
        playerDTO.exp = player.exp
        playerDTO.userId = player.userId
        playerDTO.money = player.money
        playerDTO.gender = player.gender
        playerDTO.race = player.race
        return playerDTO
    }

    fun playerEditToEntity(playerEditRequest: PlayerEditRequest): Player{
        val player = Player()
        player.name = playerEditRequest.name
        player.exp = playerEditRequest.exp
        player.gender = playerEditRequest.gender
        player.money = playerEditRequest.money
        player.race = playerEditRequest.race
        player.userId = playerEditRequest.userId
        return player
    }
}