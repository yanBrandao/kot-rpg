package br.com.woodriver.rpg.configuration

import feign.auth.BasicAuthRequestInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlizzardClientConfiguration() {

    @Value("\${blizzard.username}")
    lateinit var username: String
    @Value("\${blizzard.password}")
    lateinit var password: String

    @Bean
    fun basicAuthRequestInterceptor(): BasicAuthRequestInterceptor{
        return BasicAuthRequestInterceptor(username, password)
    }
}