package br.com.woodriver.rpg.domain

import br.com.woodriver.rpg.domain.utils.compositekeys.SkillTreeId
import javax.persistence.Column
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "KOR_SKILL_TREE")
class SkillTree() {
    @EmbeddedId
    lateinit var skillTreeId: SkillTreeId
    @Column(name = "SKT_LEVEL")
    var level: Int = 0
    @Column(name = "SKT_RUNE")
    var rune: String = "None"

    constructor(skillTreeId: SkillTreeId, level: Int, rune: String) : this(){
        this.skillTreeId = skillTreeId
        this.level = level
        this.rune = rune
    }
}