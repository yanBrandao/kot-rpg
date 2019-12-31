package br.com.woodriver.rpg.domain

import org.springframework.data.annotation.Id

import org.springframework.data.annotation.TypeAlias
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection="player")
data class Player(@Id val key: String = "",
                  val name: String = "",
                  val level: Int = 0){
}