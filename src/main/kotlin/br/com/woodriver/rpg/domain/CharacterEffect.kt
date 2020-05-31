package br.com.woodriver.rpg.domain

import br.com.woodriver.rpg.domain.utils.compositekeys.CharacterEffectId
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "KOR_PLAYER_EFFECT")
class CharacterEffect (){
        @EmbeddedId
        lateinit var characterEffectId: CharacterEffectId

        constructor(characterEffectId: CharacterEffectId): this(){
                this.characterEffectId = characterEffectId
        }

}