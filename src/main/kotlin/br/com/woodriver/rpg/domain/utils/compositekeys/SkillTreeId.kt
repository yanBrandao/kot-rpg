package br.com.woodriver.rpg.domain.utils.compositekeys

import br.com.woodriver.rpg.domain.player.Character
import br.com.woodriver.rpg.domain.Skill
import java.io.Serializable
import javax.persistence.*

@Embeddable
class SkillTreeId(): Serializable{
        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        lateinit var sktCrtId: Character
        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        lateinit var sktSklId: Skill

        constructor(character: Character, skill: Skill): this(){
                this.sktCrtId = character
                this.sktSklId = skill
        }
}