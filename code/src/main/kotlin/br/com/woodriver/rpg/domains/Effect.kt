package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.EffectType
import javax.persistence.*

@Entity
@Table(name = "KOR_EFFECT")
class Effect() {
    @Id
    @GeneratedValue
    @Column(name = "EFC_ID")
    var key: Long = 0
    @Column(name = "EFC_NAME")
    var name: String = "No Name"
    @Column(name = "EFC_VALUE")
    var value: Double = 1.0
    @Column(name = "EFC_TYPE")
    var type: EffectType = EffectType.BUFF
    @Column(name = "EFC_RANGE")
    var range: Double = 1.0
    @Column(name = "EFC_DURATION")
    var duration: Double = 1.0

    constructor(key: Long,
                name: String,
                value: Double,
                type: EffectType,
                range: Double,
                duration: Double) : this(){
        this.key = key
        this.name = name
        this.value = value
        this.type = type
        this.range = range
        this.duration = duration
    }
}