package br.com.woodriver.rpg.usecase.item

import br.com.woodriver.rpg.configuration.BlizzardTokenConfiguration
import br.com.woodriver.rpg.domains.BlizzardItem
import br.com.woodriver.rpg.gateway.client.BlizzardAPIClient
import br.com.woodriver.rpg.gateway.client.BlizzardSTSClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GetBlizzardItemsUseCase() {

    @Autowired
    lateinit var blizzardSTSClient: BlizzardSTSClient

    @Autowired
    lateinit var blizzardTokenConfiguration: BlizzardTokenConfiguration

    @Autowired
    lateinit var blizzardAPIClient: BlizzardAPIClient

    constructor(blizzardTokenConfiguration: BlizzardTokenConfiguration,
                blizzardSTSClient: BlizzardSTSClient,
                blizzardAPIClient: BlizzardAPIClient) : this(){
        this.blizzardSTSClient = blizzardSTSClient
        this.blizzardTokenConfiguration = blizzardTokenConfiguration
        this.blizzardAPIClient = blizzardAPIClient
    }

    fun execute(itemId: String): BlizzardItem{
        return blizzardAPIClient.getItemIcon(itemId, "Bearer " + blizzardTokenConfiguration.getToken())
    }
}