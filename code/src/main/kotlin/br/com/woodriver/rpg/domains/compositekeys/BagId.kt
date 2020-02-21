package br.com.woodriver.rpg.domains.compositekeys

import br.com.woodriver.rpg.domains.Item
import br.com.woodriver.rpg.domains.Player
import java.io.Serializable
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Embeddable
class BagId(
        @ManyToOne(fetch = FetchType.LAZY)
        val bagPlrId: Player,
        @ManyToOne(fetch = FetchType.LAZY)
        val bagItmId: Item) : Serializable{
}