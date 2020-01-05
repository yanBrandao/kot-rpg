package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.PositionType
import org.springframework.data.annotation.Id

import org.springframework.data.mongodb.core.mapping.Document
import kotlin.collections.HashMap

@Document(collection="player")
data class Player(@Id val key: String = "",
                  var name: String,
                  val level: Int = 1,
                  val equipment: HashMap<PositionType, Item> = hashMapOf(),
                  val bag: List<Item> = listOf()){

    fun equipItem(item: Item){
        equipment[item.position] = item
    }
}