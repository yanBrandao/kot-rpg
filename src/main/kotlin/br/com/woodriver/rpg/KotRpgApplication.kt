package br.com.woodriver.rpg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.web.config.EnableSpringDataWebSupport

@SpringBootApplication
@EnableSpringDataWebSupport
class KotRpgApplication

fun main(args: Array<String>) {
	runApplication<KotRpgApplication>(*args)
}
