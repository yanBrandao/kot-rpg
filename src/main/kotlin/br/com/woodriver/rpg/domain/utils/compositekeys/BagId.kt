package br.com.woodriver.rpg.domain.utils.compositekeys

import br.com.woodriver.rpg.domain.Item
import br.com.woodriver.rpg.domain.player.Player
import java.io.Serializable
import javax.persistence.CascadeType
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Embeddable
class BagId(): Serializable{
        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        lateinit var bagPlrId: Player
        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        lateinit var bagItmId: Item

        constructor(player: Player, item: Item) : this(){
                this.bagPlrId = player
                this.bagItmId = item
        }


}