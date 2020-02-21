package br.com.woodriver.rpg.domains.compositekeys

import br.com.woodriver.rpg.domains.Player
import br.com.woodriver.rpg.domains.Skill
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Embeddable
class SkillTreeId(
        @ManyToOne(fetch = FetchType.LAZY)
        val sktPlrId: Player,
        @ManyToOne(fetch = FetchType.LAZY)
        val sktSklId: Skill) : Serializable{
}