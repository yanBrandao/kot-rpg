package br.com.woodriver.rpg.gateway.client

import br.com.woodriver.rpg.configuration.BlizzardClientConfiguration
import br.com.woodriver.rpg.domain.OAuthBlizzard
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(value = "blizzard", url = "\${blizzard.host-token}", configuration = [BlizzardClientConfiguration::class])
interface BlizzardSTSClient {

    @PostMapping(value = [])
    fun getToken() : OAuthBlizzard
}