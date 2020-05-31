package br.com.woodriver.rpg.domain.utils.compositekeys

import br.com.woodriver.rpg.domain.Effect
import br.com.woodriver.rpg.domain.player.Character
import java.io.Serializable
import javax.persistence.*

@Embeddable
class CharacterEffectId(): Serializable{
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    lateinit var cefCrtId: Character
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    lateinit var cefEfcId: Effect

    constructor(characterId: Character, effectId: Effect) : this() {
        this.cefCrtId = characterId
        this.cefEfcId = effectId
    }
}