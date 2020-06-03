package br.com.woodriver.rpg.domain.utils.compositekeys

import br.com.woodriver.rpg.domain.Item
import br.com.woodriver.rpg.domain.player.Character
import java.io.Serializable
import javax.persistence.CascadeType
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Embeddable
class BagId(): Serializable{
        @ManyToOne(cascade = [CascadeType.ALL])
        lateinit var bagCrtId: Character
        @ManyToOne(cascade = [CascadeType.ALL])
        lateinit var bagItmId: Item

        constructor(character: Character, item: Item) : this(){
                this.bagCrtId = character
                this.bagItmId = item
        }


}