package br.com.woodriver.blizzard.gateway.http

import br.com.woodriver.blizzard.domains.BlizzardItem
import br.com.woodriver.blizzard.domains.OAuthBlizzard
import br.com.woodriver.blizzard.usecases.token.GetTokenUseCase
import br.com.woodriver.blizzard.usecases.blizzard.GetBlizzardItemUseCase
import org.springframework.web.bind.annotation.*

@RestController
class BlizzardController(val getTokenUseCase: GetTokenUseCase,
                       val getBlizzardItemUseCase: GetBlizzardItemUseCase) {

    @PostMapping("/")
    fun getToken(): OAuthBlizzard {
        return getTokenUseCase.execute()
    }

    @GetMapping("/data/wow/media/item/{itemId}")
    fun getItem(@PathVariable itemId: String): BlizzardItem {
        return getBlizzardItemUseCase.execute(itemId)
    }

}