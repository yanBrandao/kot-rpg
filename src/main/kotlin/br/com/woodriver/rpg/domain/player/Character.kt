package br.com.woodriver.rpg.domain.player

import br.com.woodriver.rpg.domain.*
import br.com.woodriver.rpg.domain.utils.types.GenderType
import br.com.woodriver.rpg.domain.utils.types.RaceType
import com.fasterxml.jackson.annotation.JsonIgnore
import com.sun.istack.NotNull
import javax.persistence.*
import javax.validation.constraints.Min

@Entity
@Table(name = "KOR_CHARACTER")
class Character() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CRT_ID", unique = true, nullable = false)
    var key: Long = 1L
    @NotNull
    @Column(name = "CRT_NAME")
    var name: String = "No Name"
    @NotNull
    @OneToOne
    @JoinColumn(name = "CRT_USER_ID")
    var user: User = User()
    @Column(name = "CRT_GENDER")
    var gender: GenderType = GenderType.MALE
    @Column(name = "CRT_RACE")
    var race: RaceType = RaceType.HUMAN
    @Column(name = "CRT_EXPERIENCE")
    @Min(0)
    var exp: Double = 0.0
    @Column(name = "CRT_MONEY")
    var money: Double = 0.0
    @OneToMany(
            mappedBy = "characterEffectId.cefCrtId",
            cascade = [CascadeType.ALL],
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @JsonIgnore
    var effects: Set<CharacterEffect> = emptySet()
    @OneToMany(
            mappedBy = "bagId.bagCrtId",
            cascade = [CascadeType.ALL],
            orphanRemoval = true
    )
    @JsonIgnore
    var bags: Set<Bag> = emptySet()
    @OneToMany(
            mappedBy = "skillTreeId.sktCrtId",
            cascade = [CascadeType.ALL],
            orphanRemoval = true
    )
    @JsonIgnore
    var skillTree: Set<SkillTree> = emptySet()


    fun characterLevel(): Double {
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
                user: User,
                race: RaceType,
                exp: Double,
                money: Double,
                effects: Set<CharacterEffect>,
                bags: Set<Bag>,
                skillTree: Set<SkillTree>) : this(){
        this.key = key
        this.name = name
        this.user = user
        this.race = race
        this.exp = exp
        this.money = money
        this.effects = effects
        this.bags = bags
        this.skillTree = skillTree
    }
}