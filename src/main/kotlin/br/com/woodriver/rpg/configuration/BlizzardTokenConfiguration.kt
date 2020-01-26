package br.com.woodriver.rpg.configuration

import br.com.woodriver.rpg.domains.OAuthBlizzard
import br.com.woodriver.rpg.gateway.client.BlizzardSTSClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class BlizzardTokenConfiguration() {

    @Autowired
    lateinit var blizzardSTSClient: BlizzardSTSClient

    var token: OAuthBlizzard = OAuthBlizzard("", "", 0)
    var timeTokenRequested: Date = Date()

    constructor(blizzardSTSClient: BlizzardSTSClient) : this(){
        this.blizzardSTSClient = blizzardSTSClient
    }

    @Bean
    fun getToken(): String {
        val checkExpiredToken = Date().time - timeTokenRequested.time
        if(token.accessToken.isEmpty() || checkExpiredToken > token.expiresIn)
            token = blizzardSTSClient.getToken()
        timeTokenRequested = Date()
        return token.accessToken
    }


}