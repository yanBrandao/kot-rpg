package br.com.woodriver.rpg.domain.player

import br.com.woodriver.rpg.domain.Bag
import br.com.woodriver.rpg.domain.PlayerEffect
import br.com.woodriver.rpg.domain.SkillTree
import br.com.woodriver.rpg.domain.utils.types.GenderType
import br.com.woodriver.rpg.domain.utils.types.RaceType
import com.fasterxml.jackson.annotation.JsonIgnore
import com.sun.istack.NotNull
import javax.persistence.*
import javax.validation.constraints.Min

@Entity
@Table(name = "KOR_PLAYER")
class Player() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLR_ID", unique = true, nullable = false)
    var key: Long = 1L
    @NotNull
    @Column(name = "PLR_NAME")
    var name: String = "No Name"
    @NotNull
    @Column(name = "PLR_USER")
    var userId: Long = 1L
    @Column(name = "PLR_GENDER")
    var gender: GenderType = GenderType.MALE
    @Column(name = "PLR_RACE")
    var race: RaceType = RaceType.HUMAN
    @Column(name = "PLR_EXPERIENCE")
    @Min(0)
    var exp: Double = 0.0
    @Column(name = "PLR_MONEY")
    var money: Double = 0.0
    @OneToMany(
            mappedBy = "playerEffectId.pefPlrId",
            cascade = [CascadeType.ALL],
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @JsonIgnore
    var effects: Set<PlayerEffect> = emptySet()
    @OneToMany(
            mappedBy = "bagId.bagPlrId",
            cascade = [CascadeType.ALL],
            orphanRemoval = true
    )
    @JsonIgnore
    var bags: Set<Bag> = emptySet()
    @OneToMany(
            mappedBy = "skillTreeId.sktPlrId",
            cascade = [CascadeType.ALL],
            orphanRemoval = true
    )
    @JsonIgnore
    var skillTree: Set<SkillTree> = emptySet()


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
                userId: Long,
                race: RaceType,
                exp: Double,
                money: Double,
                effects: Set<PlayerEffect>,
                bags: Set<Bag>,
                skillTree: Set<SkillTree>) : this(){
        this.key = key
        this.name = name
        this.userId = userId
        this.race = race
        this.exp = exp
        this.money = money
        this.effects = effects
        this.bags = bags
        this.skillTree = skillTree
    }
}