package br.com.woodriver.rpg.domain.utils.compositekeys

import br.com.woodriver.rpg.domain.Effect
import br.com.woodriver.rpg.domain.player.Player
import java.io.Serializable
import javax.persistence.*

@Embeddable
class PlayerEffectId(): Serializable{
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    lateinit var pefPlrId: Player
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    lateinit var pefEfcId: Effect

    constructor(playerId: Player, effectId: Effect) : this() {
        this.pefPlrId = playerId
        this.pefEfcId = effectId
    }
}