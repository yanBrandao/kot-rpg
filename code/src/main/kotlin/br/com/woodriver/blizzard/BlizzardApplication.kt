package br.com.woodriver.blizzard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class BlizzardApplication

fun main(args: Array<String>) {
	runApplication<BlizzardApplication>(*args)
}