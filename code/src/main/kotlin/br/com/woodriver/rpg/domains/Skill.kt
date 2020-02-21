package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.AbilityType
import javax.persistence.*

@Entity
@Table(name = "KOR_SKILL")
class Skill() {
    @Id
    @GeneratedValue
    @Column(name = "SKL_ID", unique = true, nullable = false)
    var key: Long = 1L
    @Column(name = "SKL_NAME")
    var name: String = "No Name"
    @Column(name = "SKL_TYPE")
    var type: AbilityType = AbilityType.MELEE
    @OneToOne
    @JoinColumn(name = "SKL_CLS_ID")
    var clazz: Clazz = Clazz()

}