package br.com.woodriver.rpg.gateway.client

import br.com.woodriver.rpg.configuration.BlizzardClientConfiguration
import br.com.woodriver.rpg.domains.OAuthBlizzard
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(value = "blizzard", url = "https://us.battle.net", configuration = [BlizzardClientConfiguration::class])
interface BlizzardSTSClient {

    @PostMapping(value = ["/oauth/token?grant_type=client_credentials"])
    fun getToken() : OAuthBlizzard
}