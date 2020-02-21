package br.com.woodriver.rpg.domains.compositekeys

import br.com.woodriver.rpg.domains.Effect
import br.com.woodriver.rpg.domains.Player
import java.io.Serializable
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Embeddable
class PlayerEffectId(
    @ManyToOne(fetch = FetchType.LAZY)
    val pefPlrId: Player,
    @ManyToOne(fetch = FetchType.LAZY)
    val pefEfcId: Effect): Serializable{
}