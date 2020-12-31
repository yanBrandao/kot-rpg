package br.com.woodriver.rpg.gateway.client

import br.com.woodriver.rpg.domain.BlizzardItem
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(value = "blizzardAPI", url = "\${blizzard.host-api}")
interface BlizzardAPIClient {

    @GetMapping(value = ["/data/wow/media/item/{itemId}?namespace=static-us&locale=en_US"])
    fun getItemIcon(@PathVariable itemId: String, @RequestHeader("Authorization") token: String): BlizzardItem
}