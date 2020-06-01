package br.com.woodriver.rpg.domain.player

import br.com.woodriver.rpg.domain.User
import br.com.woodriver.rpg.domain.utils.types.GenderType
import br.com.woodriver.rpg.domain.utils.types.RaceType

data class CharacterEditRequest(var name: String = "No Name",
                                var user: User = User(),
                                var gender: GenderType = GenderType.MALE,
                                var race: RaceType = RaceType.HUMAN,
                                var exp: Double = 0.0,
                                var money: Double = 0.0){

}