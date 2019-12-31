package br.com.woodriver.rpg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RpgApplication

fun main(args: Array<String>) {
	runApplication<RpgApplication>(*args)
}
