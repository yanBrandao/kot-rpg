package br.com.woodriver.rpg.domain.utils.compositekeys

import br.com.woodriver.rpg.domain.player.Player
import br.com.woodriver.rpg.domain.Skill
import java.io.Serializable
import javax.persistence.*

@Embeddable
class SkillTreeId(): Serializable{
        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        lateinit var sktPlrId: Player
        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        lateinit var sktSklId: Skill

        constructor(player: Player, skill: Skill): this(){
                this.sktPlrId = player
                this.sktSklId = skill
        }
}