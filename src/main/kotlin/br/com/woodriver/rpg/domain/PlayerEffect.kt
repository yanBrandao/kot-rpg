package br.com.woodriver.rpg.domain

import br.com.woodriver.rpg.domain.utils.compositekeys.PlayerEffectId
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "KOR_PLAYER_EFFECT")
class PlayerEffect (){
        @EmbeddedId
        lateinit var playerEffectId: PlayerEffectId

        constructor(playerEffectId: PlayerEffectId): this(){
                this.playerEffectId = playerEffectId
        }

}