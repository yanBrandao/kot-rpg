package br.com.woodriver.rpg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients


@SpringBootApplication
@EnableFeignClients
class RpgApplication

fun main(args: Array<String>) {
	runApplication<RpgApplication>(*args)
}