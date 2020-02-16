package br.com.woodriver.rpg.gateway.client

import br.com.woodriver.rpg.configuration.BlizzardClientConfiguration
import br.com.woodriver.rpg.domains.OAuthBlizzard
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(value = "blizzard", url = "\${blizzard.host}", configuration = [BlizzardClientConfiguration::class])
interface BlizzardSTSClient {

    @PostMapping(value = [])
    fun getToken() : OAuthBlizzard
}