package br.com.woodriver.rpg.domains

import com.sun.istack.NotNull
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Min

@Entity
@Table(name = "KOR_PLAYER")
class Player() {
    @Id
    @GeneratedValue
    @Column(name = "PLR_ID", unique = true, nullable = false)
    var key: Long = 1L
    @NotNull
    @Column(name = "PLR_NAME")
    var name: String = "No Name"
    @NotNull
    @Column(name = "PLR_EMAIL")
    @Email
    var email: String = "a@a.com"
    @Column(name = "PLR_EXPERIENCE")
    @Min(0)
    var exp: Double = 0.0
    @OneToMany(
            mappedBy = "playerEffectId.pefPlrId",
            cascade = [CascadeType.ALL],
            orphanRemoval = true
    )
    var effects: List<PlayerEffect> = listOf()
    @OneToMany(
            mappedBy = "bagId.bagPlrId",
            cascade = [CascadeType.ALL],
            orphanRemoval = true
    )
    var bags: List<Bag> = listOf()
    @OneToMany(
            mappedBy = "skillTreeId.sktPlrId",
            cascade = [CascadeType.ALL],
            orphanRemoval = true
    )
    var skillTree: List<SkillTree> = listOf()


    fun playerLevel(): Double {
        var expVariant = exp
        var moveBase = BASE_EXP
        var level = 0.0
        while (expVariant > 0) {
            expVariant -= moveBase
            moveBase += BASE_EXP
            level++
        }
        return level
    }

    companion object {
        const val BASE_EXP = 100.00
    }

    constructor(key: Long,
                name: String,
                email: String,
                exp: Double,
                effects: List<PlayerEffect>,
                bags: List<Bag>,
                skillTree: List<SkillTree>) : this(){
        this.key = key
        this.name = name
        this.email = email
        this.exp = exp
        this.effects = effects
        this.bags = bags
        this.skillTree = skillTree
    }
}