package br.com.woodriver.rpg.domain

import br.com.woodriver.rpg.domain.utils.types.AbilityType
import javax.persistence.*

@Entity
@Table(name = "KOR_ENEMY")
class Enemy() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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