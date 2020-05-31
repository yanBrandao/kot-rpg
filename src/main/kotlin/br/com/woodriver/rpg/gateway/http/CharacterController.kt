package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domain.player.Character
import br.com.woodriver.rpg.domain.player.CharacterEditRequest
import br.com.woodriver.rpg.usecases.character.*
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/characters")
class CharacterController(val top10BestPlayersUseCase: Top10BestPlayersUseCase,
                          val createOrUpdatePlayerUseCase: CreateOrUpdatePlayerUseCase,
                          val deletePlayerUseCase: DeletePlayerUseCase,
                          val getAllPlayersUseCase: GetAllPlayersUseCase,
                          val characterByUserUseCase: CharacterByUserUseCase) {

    private val logger: Log = LogFactory.getLog(this.javaClass)


    @GetMapping
    fun all(): List<CharacterEditRequest>{
        return getAllPlayersUseCase.execute()
    }

    @GetMapping("/top10")
    fun top10(): List<Character>{
        return top10BestPlayersUseCase.execute()
    }

    @GetMapping("/user/")
    fun charecterByUserID(@RequestHeader("x-kot-id") userId: Long): List<Character>{
        return characterByUserUseCase.execute(userId)
    }

    @PostMapping
    fun create(@RequestBody character: CharacterEditRequest): Character {
        logger.debug("Create player $character")
        return createOrUpdatePlayerUseCase.execute(character)
    }

    @PutMapping("/{key}")
    fun update(@PathVariable key: Long, @RequestBody character: CharacterEditRequest): Character {
        return createOrUpdatePlayerUseCase.execute(character, true, key)
    }

    @DeleteMapping("/{key}")
    fun delete(@PathVariable key: Long){
        return deletePlayerUseCase.execute(key)
    }

}