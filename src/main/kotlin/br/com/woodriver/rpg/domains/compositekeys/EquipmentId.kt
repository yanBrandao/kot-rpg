package br.com.woodriver.rpg.domains.compositekeys

import br.com.woodriver.rpg.domains.Item
import br.com.woodriver.rpg.domains.Player
import java.io.Serializable
import javax.persistence.*

@Embeddable
class EquipmentId(
        @ManyToOne(fetch = FetchType.LAZY)
        val eqpPlrId: Player,
        @ManyToOne(fetch = FetchType.LAZY)
        val eqpItmId: Item) : Serializable{
}