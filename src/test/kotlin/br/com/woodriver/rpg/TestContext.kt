package br.com.woodriver.rpg

import br.com.woodriver.rpg.configuration.BlizzardClientConfiguration
import br.com.woodriver.rpg.configuration.BlizzardTokenConfiguration
import br.com.woodriver.rpg.configuration.SwaggerConfiguration
import br.com.woodriver.rpg.gateway.client.BlizzardAPIClient
import org.springframework.boot.test.mock.mockito.MockBean

open class TestContext {
    @MockBean
    lateinit var blizzardClientConfiguration: BlizzardClientConfiguration

    @MockBean
    lateinit var blizzardTokenConfiguration: BlizzardTokenConfiguration

    @MockBean
    lateinit var blizzardAPIClient: BlizzardAPIClient
}