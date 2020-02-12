package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.PositionType
import br.com.woodriver.rpg.domains.types.RarityType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {

    @Test fun changePlayerItem(){
        var sword = Item(1, "Katana", 2.00, 10000.00, PositionType.LEFT_HAND, RarityType.UNCOMMON, "")
        var secondSword = Item(2, "White Sword", 1.00, 2000.00, PositionType.LEFT_HAND, RarityType.COMMON, "")
        var player = Player(1, "Yan")


    }

}