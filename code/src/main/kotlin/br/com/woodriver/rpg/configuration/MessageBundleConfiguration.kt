package br.com.woodriver.rpg.configuration

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

@Configuration
class MessageBundleConfiguration {

    @Bean
    fun messageSource(): MessageSource{
        var messageBundle = ReloadableResourceBundleMessageSource()
        messageBundle.setBasename("classpath:messages")
        messageBundle.setDefaultEncoding("UTF-8")
        return messageBundle
    }


    @Bean
    fun localeResolver(): LocaleResolver{
        var sessionLocalResolver = SessionLocaleResolver()
        sessionLocalResolver.setDefaultLocale(Locale("pt", "BR"))
        return sessionLocalResolver
    }
}