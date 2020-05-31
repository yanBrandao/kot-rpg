package br.com.woodriver.rpg.domain

import br.com.woodriver.rpg.domain.utils.types.AbilityType
import javax.persistence.*

@Entity
@Table(name = "KOR_SKILL")
class Skill() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SKL_ID", unique = true, nullable = false)
    var key: Long = 1L
    @Column(name = "SKL_NAME")
    var name: String = "No Name"
    @Column(name = "SKL_TYPE")
    var type: AbilityType = AbilityType.MELEE
    @OneToOne
    @JoinColumn(name = "SKL_CLS_ID")
    var clazz: Clazz = Clazz()
    @OneToMany(
            mappedBy = "skillTreeId.sktSklId",
            cascade = [CascadeType.ALL],
            orphanRemoval = true
    )
    var skillTree: Set<SkillTree> = emptySet()

    constructor(key: Long, name: String, type: AbilityType, clazz: Clazz) : this(){
        this.key = key
        this.name = name
        this.type = type
        this.clazz = clazz
    }
}