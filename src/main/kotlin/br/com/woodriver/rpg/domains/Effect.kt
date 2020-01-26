package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.EffectType
import javax.persistence.*

@Entity
@Table(name = "KOR_EFFECT")
class Effect(@Id
@Column(name = "EFC_ID")
             val key: Long,
             @Column(name = "EFC_NAME")
             val name:String,
             @Column(name = "EFC_VALUE")
             val value: Double,
             @Column(name = "EFC_TYPE")
             val type: EffectType,
             @Column(name = "EFC_RANGE")
             val range: Double,
             @Column(name = "EFC_DURATION")
             val duration: Double,
             @ManyToMany(mappedBy = "effects")
             var players: List<Player> = mutableListOf()) {
}