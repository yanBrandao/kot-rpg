package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domains.Player
import br.com.woodriver.rpg.exceptions.BadRequestException
import br.com.woodriver.rpg.usecase.player.CreateOrUpdatePlayer
import br.com.woodriver.rpg.usecase.player.DeletePlayer
import br.com.woodriver.rpg.usecase.player.GetAllPlayers
import br.com.woodriver.rpg.usecase.player.Top10BestPlayers
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("players")
class PlayerController(val top10BestPlayers: Top10BestPlayers,
                       val createOrUpdatePlayer: CreateOrUpdatePlayer,
                       val deletePlayer: DeletePlayer,
                       val getAllPlayers: GetAllPlayers) {

    @GetMapping
    fun all(): List<Player>{
        return getAllPlayers.execute()
    }

    @GetMapping("/top10")
    fun top10(): List<Player>{
        return top10BestPlayers.execute()
    }

    @PostMapping
    fun create(@RequestBody player: Player): Player{
        return createOrUpdatePlayer.execute(player)
    }

    @PutMapping
    fun update(@RequestBody player: Player): Player{
        if(player.key.isEmpty())
            throw BadRequestException("Key cannot be empty to update a player")
        return createOrUpdatePlayer.execute(player)
    }

    @DeleteMapping("/{key}")
    fun delete(@PathVariable key: String){
        return deletePlayer.execute(key)
    }

}