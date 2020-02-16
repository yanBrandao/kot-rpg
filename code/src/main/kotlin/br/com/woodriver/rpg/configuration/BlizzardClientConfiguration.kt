package br.com.woodriver.rpg.configuration

import feign.auth.BasicAuthRequestInterceptor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlizzardClientConfiguration() {

    @Value("\${blizzard.username}")
    lateinit var username: String
    @Value("\${blizzard.password}")
    lateinit var password: String
    val logger: Logger = LoggerFactory.getLogger("configuration.BlizzardClientConfiguration")



    @Bean
    fun basicAuthRequestInterceptor(): BasicAuthRequestInterceptor{
        logger.debug("$username:$password")
        return BasicAuthRequestInterceptor(username, password)
    }
}