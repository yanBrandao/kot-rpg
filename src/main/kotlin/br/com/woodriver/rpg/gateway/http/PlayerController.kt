package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domain.player.Player
import br.com.woodriver.rpg.domain.player.PlayerEditRequest
import br.com.woodriver.rpg.usecases.player.*
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/players")
class PlayerController(val top10BestPlayersUseCase: Top10BestPlayersUseCase,
                       val createOrUpdatePlayerUseCase: CreateOrUpdatePlayerUseCase,
                       val deletePlayerUseCase: DeletePlayerUseCase,
                       val getAllPlayersUseCase: GetAllPlayersUseCase,
val playerByUserUseCase: PlayerByUserUseCase) {

    private val logger: Log = LogFactory.getLog(this.javaClass)


    @GetMapping
    fun all(): List<PlayerEditRequest>{
        return getAllPlayersUseCase.execute()
    }

    @GetMapping("/top10")
    fun top10(): List<Player>{
        return top10BestPlayersUseCase.execute()
    }

    @GetMapping("/user/")
    fun playerByUserID(@RequestHeader("x-kot-id") userId: Long): List<Player>{
        return playerByUserUseCase.execute(userId)
    }

    @PostMapping
    fun create(@RequestBody player: PlayerEditRequest): Player {
        logger.debug("Create player $player")
        return createOrUpdatePlayerUseCase.execute(player)
    }

    @PutMapping("/{key}")
    fun update(@PathVariable key: Long, @RequestBody player: PlayerEditRequest): Player {
        return createOrUpdatePlayerUseCase.execute(player, true, key)
    }

    @DeleteMapping("/{key}")
    fun delete(@PathVariable key: Long){
        return deletePlayerUseCase.execute(key)
    }

}