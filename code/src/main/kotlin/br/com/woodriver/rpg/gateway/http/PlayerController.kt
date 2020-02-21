package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domains.Player
import br.com.woodriver.rpg.gateway.dto.PlayerDTO
import br.com.woodriver.rpg.usecases.player.CreateOrUpdatePlayerUseCase
import br.com.woodriver.rpg.usecases.player.DeletePlayerUseCase
import br.com.woodriver.rpg.usecases.player.GetAllPlayersUseCase
import br.com.woodriver.rpg.usecases.player.Top10BestPlayersUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("players")
class PlayerController(val top10BestPlayersUseCase: Top10BestPlayersUseCase,
                       val createOrUpdatePlayerUseCase: CreateOrUpdatePlayerUseCase,
                       val deletePlayerUseCase: DeletePlayerUseCase,
                       val getAllPlayersUseCase: GetAllPlayersUseCase) {

    @GetMapping
    fun all(): List<PlayerDTO>{
        return getAllPlayersUseCase.execute()
    }

    @GetMapping("/top10")
    fun top10(): List<Player>{
        return top10BestPlayersUseCase.execute()
    }

    @PostMapping
    fun create(@RequestBody player: Player): Player{
        return createOrUpdatePlayerUseCase.execute(player)
    }

    @PutMapping
    fun update(@RequestBody player: Player): Player{
        return createOrUpdatePlayerUseCase.execute(player, true)
    }

    @DeleteMapping("/{key}")
    fun delete(@PathVariable key: Long){
        return deletePlayerUseCase.execute(key)
    }

}