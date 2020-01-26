package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.PositionType
import br.com.woodriver.rpg.domains.types.RarityType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EnemyTest {

    @Test fun dropItemWhenEnemyDie(){
        val bone = Item(1L, "Bone", 20.0, 1.0, PositionType.NONE, RarityType.COMMON, listOf())
        val sword = Item(2L, "Sword", 120.0, 100.0, PositionType.LEFT_HAND, RarityType.COMMON, listOf())
        val itemToDrop: MutableList<Item> = arrayListOf()
        itemToDrop.add(bone)
        itemToDrop.add(sword)
        val enemy = Enemy(99L, "Skeleton", 3, itemToDrop)

        val itemsDropped = enemy.dropItems()

        Assertions.assertEquals(true, itemsDropped.size <= itemToDrop.size)
    }
}