package br.com.woodriver.rpg

import br.com.woodriver.rpg.domain.*
import br.com.woodriver.rpg.domain.player.Player
import br.com.woodriver.rpg.domain.utils.compositekeys.BagId
import br.com.woodriver.rpg.domain.utils.compositekeys.PlayerEffectId
import br.com.woodriver.rpg.domain.utils.compositekeys.SkillTreeId
import br.com.woodriver.rpg.domain.utils.types.*
import org.junit.jupiter.api.Assertions

class TestUtils {

    companion object {
        @JvmStatic
        fun createPlayerWithCustomRace(race: RaceType): Player {
            val playerEffects = emptySet<PlayerEffect>()
            val bag = emptySet<Bag>()
            val skills = emptySet<SkillTree>()
            val player = Player(1L, "Yan", 1L, race, 0.0, 0.0, playerEffects, bag, skills)

            val playerEffectId = PlayerEffectId(player, createAuraEffect())
            val playerItemId = BagId(player, createRingItem())
            val playerSkillId = SkillTreeId(player, createSkill())

            val playerEffect = PlayerEffect(playerEffectId)
            val playerItem = Bag(playerItemId,false, 10)
            val playerSkill = SkillTree(playerSkillId, 1, "No Rune")

            playerEffects.plus(playerEffect)
            bag.plus(playerItem)
            skills.plus(playerSkill)

            return player
        }

        @JvmStatic
        fun createUser(): User{
            return User(1L, "Yan", "yan@gmail.com", "yan123")
        }

        @JvmStatic
        fun createUserWithEncryptPassword(): User{
            return User(1L, "Yan", "yan@gmail.com", "eWFuMTIz")
        }

        @JvmStatic
        fun createRingItem(): Item {
            return Item(1L, "Ring", 100.0, 100.0, PositionType.RIGHT_EAR, RarityType.LEGENDARY, "0")
        }

        @JvmStatic
        fun createRingItemWithIcon(icon: String): Item {
            return Item(1L, "Ring", 100.0, 100.0, PositionType.RIGHT_EAR, RarityType.LEGENDARY, icon)
        }

        @JvmStatic
        fun createAuraEffect(): Effect {
            return Effect(2L, "None", 0.0, EffectType.AURA, 0.0, 0.0)
        }

        @JvmStatic
        fun createClazz(): Clazz{
            return Clazz(1L, "Magician")
        }

        @JvmStatic
        fun createSkill(): Skill{
            return Skill(1L, "Fireball", AbilityType.MELEE, createClazz())
        }

        @JvmStatic
        fun createBagId(): BagId {
            return BagId(createPlayerWithCustomRace(RaceType.ELF), createRingItem())
        }

        @JvmStatic
        fun createBag(): Bag{
            return Bag(createBagId(), true, 10)
        }

        @JvmStatic
        fun assertBag(expected: Bag, actual: Bag){
            assertPlayerAttributes(expected.bagId.bagPlrId, actual.bagId.bagPlrId)
            assertItem(expected.bagId.bagItmId, actual.bagId.bagItmId)
            Assertions.assertEquals(expected.quantity, actual.quantity)
            Assertions.assertEquals(expected.isEquipped, actual.isEquipped)
        }

        @JvmStatic
        fun assertPlayerAttributes(expected: Player, actual: Player){
            Assertions.assertEquals(expected.key, actual.key)
            Assertions.assertEquals(expected.name, actual.name)
            Assertions.assertEquals(expected.exp, actual.exp)
            Assertions.assertEquals(expected.money, actual.money)
            Assertions.assertEquals(expected.gender, actual.gender)
            Assertions.assertEquals(expected.race, actual.race)
            Assertions.assertEquals(expected.userId, actual.userId)
        }

        @JvmStatic
        fun assertClazz(expected: Clazz, actual: Clazz){
            Assertions.assertEquals(expected.key, actual.key)
            Assertions.assertEquals(expected.name, actual.name)
        }

        @JvmStatic
        fun assertSkill(expected: Skill, actual: Skill){
            Assertions.assertEquals(expected.key, actual.key)
            Assertions.assertEquals(expected.name, actual.name)
            Assertions.assertEquals(expected.type, actual.type)
            assertClazz(expected.clazz, actual.clazz)
        }

        @JvmStatic
        fun assertEffect(expected: Effect, actual: Effect){
            Assertions.assertEquals(expected.key, actual.key)
            Assertions.assertEquals(expected.name, actual.name)
            Assertions.assertEquals(expected.duration, actual.duration)
            Assertions.assertEquals(expected.range, actual.range)
            Assertions.assertEquals(expected.type, actual.type)
            Assertions.assertEquals(expected.value, actual.value)
        }

        @JvmStatic
        fun assertItem(expected: Item, actual: Item){
            Assertions.assertEquals(expected.key, actual.key)
            Assertions.assertEquals(expected.icon, actual.icon)
            Assertions.assertEquals(expected.name, actual.name)
            Assertions.assertEquals(expected.position, actual.position)
            Assertions.assertEquals(expected.price, actual.price)
            Assertions.assertEquals(expected.rarity, actual.rarity)
            Assertions.assertEquals(expected.weight, actual.weight)
        }

        @JvmStatic
        fun assertPlayer(expected: Player, actual: Player) {
            //-- Assertions in player
            Assertions.assertEquals(expected.key, actual.key)
            Assertions.assertEquals(expected.name, actual.name)
            Assertions.assertEquals(expected.exp, actual.exp)
            Assertions.assertEquals(expected.gender, actual.gender)
            Assertions.assertEquals(expected.race, actual.race)
            Assertions.assertEquals(expected.userId, actual.userId)

            //-- Assertions in Player Skill
            Assertions.assertEquals(expected.skillTree.size, actual.skillTree.size)
            if (actual.skillTree.isNotEmpty()) {
                Assertions.assertEquals(expected.skillTree.elementAt(0) .level,
                        actual.skillTree.elementAt(0).level)
                Assertions.assertEquals(expected.skillTree.elementAt(0).rune,
                        actual.skillTree.elementAt(0).rune)
                assertPlayerAttributes(expected.skillTree.elementAt(0).skillTreeId.sktPlrId,
                        actual.skillTree.elementAt(0).skillTreeId.sktPlrId)
                assertSkill(expected.skillTree.elementAt(0).skillTreeId.sktSklId,
                        actual.skillTree.elementAt(0).skillTreeId.sktSklId)
            }

            //-- Assertions in Player Bag
            Assertions.assertEquals(expected.bags.size, actual.bags.size)
            if(actual.bags.isNotEmpty()){
                Assertions.assertEquals(expected.bags.elementAt(0).isEquipped,
                        expected.bags.elementAt(0).isEquipped)
                Assertions.assertEquals(expected.bags.elementAt(0).quantity,
                        expected.bags.elementAt(0).quantity)
                assertItem(expected.bags.elementAt(0).bagId.bagItmId,
                        expected.bags.elementAt(0).bagId.bagItmId)
                assertPlayerAttributes(expected.bags.elementAt(0).bagId.bagPlrId,
                        expected.bags.elementAt(0).bagId.bagPlrId)
            }

            //Assertions in Player Effect
            Assertions.assertEquals(expected.effects.size, actual.effects.size)
            if(actual.effects.isNotEmpty()){
                assertPlayerAttributes(expected.effects.elementAt(0).playerEffectId.pefPlrId,
                        actual.effects.elementAt(0).playerEffectId.pefPlrId)
                assertEffect(expected.effects.elementAt(0).playerEffectId.pefEfcId,
                        actual.effects.elementAt(0).playerEffectId.pefEfcId)
            }
        }
    }


}