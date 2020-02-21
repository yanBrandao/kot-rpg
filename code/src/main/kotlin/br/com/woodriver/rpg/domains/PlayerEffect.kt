package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.compositekeys.PlayerEffectId
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "KOR_PLAYER_EFFECT")
data class PlayerEffect (
        @EmbeddedId
        var playerEffectId: PlayerEffectId)
{

}