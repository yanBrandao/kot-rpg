package br.com.woodriver.rpg.gateway.dto

import br.com.woodriver.rpg.domains.Bag
import br.com.woodriver.rpg.domains.Effect
import br.com.woodriver.rpg.domains.PlayerEffect
import java.math.BigDecimal

class PlayerDTO(){
    var key: Long = 0L
    var name: String = "No Name"
    var email: String = "a@a.com"
    var exp: Double = 0.0
    var effects: List<PlayerEffect> = listOf()
    var level: Int = 1
    var bags: List<Bag> = listOf()

 constructor(key: Long = 1L,
             name: String = "No Name",
             email: String = "a@a.com",
             exp: Double = 0.0,
             effects: List<PlayerEffect> = listOf(),
             bags: List<Bag> = listOf()) : this(){
     this.key = key
     this.name = name
     this.email = email
     this.exp = exp
     this.effects = effects
     this.bags = bags
 }
}