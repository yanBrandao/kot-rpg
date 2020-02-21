package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.compositekeys.SkillTreeId
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SkillTree

        if (skillTreeId != other.skillTreeId) return false
        if (level != other.level) return false
        if (rune != other.rune) return false

        return true
    }

    override fun hashCode(): Int {
        var result = skillTreeId.hashCode()
        result += rune.hashCode()
        result = 31 * result + level.hashCode()
        return result
    }
}