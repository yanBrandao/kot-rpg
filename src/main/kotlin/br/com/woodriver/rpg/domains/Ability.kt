package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.AbilityType
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

@Document(collection = "ability")
data class Ability(
              val name: String,
              val typeAbility: AbilityType) {

    @Id
    val id: String = ""
}