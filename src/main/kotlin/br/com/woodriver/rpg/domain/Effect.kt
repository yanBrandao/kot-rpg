package br.com.woodriver.rpg.domain

import br.com.woodriver.rpg.domain.utils.types.EffectType
import javax.persistence.*

@Entity
@Table(name = "KOR_EFFECT")
class Effect() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(
            mappedBy = "characterEffectId.cefEfcId",
            cascade = [CascadeType.ALL],
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    var effects: Set<CharacterEffect> = emptySet()

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