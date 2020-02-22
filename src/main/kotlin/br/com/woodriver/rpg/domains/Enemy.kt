package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.AbilityType
import javax.persistence.*

@Entity
@Table(name = "KOR_ENEMY")
class Enemy() {
    @Id
    @GeneratedValue
    @Column(name = "ENY_ID", unique = true, nullable = false)
    var key: Long = 1L
    @Column(name = "ENY_NAME")
    var name: String = "No Name"
    @Column(name = "ENY_TYPE")
    var type: AbilityType = AbilityType.MELEE
    @OneToMany
    @JoinColumn(name = "ENY_ITEM_ID")
    var drops: List<Item> = listOf()
}