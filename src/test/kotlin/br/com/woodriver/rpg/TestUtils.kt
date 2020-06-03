package br.com.woodriver.rpg

import br.com.woodriver.rpg.domain.*
import br.com.woodriver.rpg.domain.player.Character
import br.com.woodriver.rpg.domain.utils.compositekeys.BagId
import br.com.woodriver.rpg.domain.utils.compositekeys.CharacterEffectId
import br.com.woodriver.rpg.domain.utils.compositekeys.SkillTreeId
import br.com.woodriver.rpg.domain.utils.types.*
import org.junit.jupiter.api.Assertions

class TestUtils {

    companion object {
        @JvmStatic
        fun createPlayerWithCustomRace(race: RaceType): Character {
            val playerEffects = HashSet<CharacterEffect>()
            val bag = HashSet<Bag>()
            val skills = HashSet<SkillTree>()
            val player = Character(1L, "Yan", createUser(), race, 0.0, 0.0, playerEffects, bag, skills)

            val playerEffectId = CharacterEffectId(player, createAuraEffect())
            val playerItemId = BagId(player, createRingItem())
            val playerSkillId = SkillTreeId(player, createSkill())

            val playerEffect = CharacterEffect(playerEffectId)
            val playerItem = Bag(playerItemId, false, 10)
            val playerSkill = SkillTree(playerSkillId, 1, "No Rune")

            playerEffects.add(playerEffect)
            bag.add(playerItem)
            skills.add(playerSkill)

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
            val effect = Effect(2L, "None", 0.0, EffectType.AURA, 0.0, 0.0)
            effect.key = 3L
            effect.name = "No Name"
            effect.value = 2.0
            effect.type = EffectType.DEBUFF
            effect.range = 30.0
            effect.duration = 30.0
            return effect
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
            assertPlayerAttributes(expected.bagId.bagCrtId, actual.bagId.bagCrtId)
            assertItem(expected.bagId.bagItmId, actual.bagId.bagItmId)
            Assertions.assertEquals(expected.quantity, actual.quantity)
            Assertions.assertEquals(expected.isEquipped, actual.isEquipped)
        }

        @JvmStatic
        fun assertPlayerAttributes(expected: Character, actual: Character){
            Assertions.assertEquals(expected.key, actual.key)
            Assertions.assertEquals(expected.name, actual.name)
            Assertions.assertEquals(expected.exp, actual.exp)
            Assertions.assertEquals(expected.money, actual.money)
            Assertions.assertEquals(expected.gender, actual.gender)
            Assertions.assertEquals(expected.race, actual.race)
            Assertions.assertEquals(expected.user, actual.user)
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
        fun assertPlayer(expected: Character, actual: Character) {
            //-- Assertions in player
            Assertions.assertEquals(expected.key, actual.key)
            Assertions.assertEquals(expected.name, actual.name)
            Assertions.assertEquals(expected.exp, actual.exp)
            Assertions.assertEquals(expected.gender, actual.gender)
            Assertions.assertEquals(expected.race, actual.race)
            Assertions.assertEquals(expected.user, actual.user)

            //-- Assertions in Player Skill
            Assertions.assertEquals(expected.skillTree.size, actual.skillTree.size)
            if (actual.skillTree.isNotEmpty()) {
                Assertions.assertEquals(expected.skillTree.elementAt(0) .level,
                        actual.skillTree.elementAt(0).level)
                Assertions.assertEquals(expected.skillTree.elementAt(0).rune,
                        actual.skillTree.elementAt(0).rune)
                assertPlayerAttributes(expected.skillTree.elementAt(0).skillTreeId.sktCrtId,
                        actual.skillTree.elementAt(0).skillTreeId.sktCrtId)
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
                assertPlayerAttributes(expected.bags.elementAt(0).bagId.bagCrtId,
                        expected.bags.elementAt(0).bagId.bagCrtId)
            }

            //Assertions in Player Effect
            Assertions.assertEquals(expected.effects.size, actual.effects.size)
            if(actual.effects.isNotEmpty()){
                assertPlayerAttributes(expected.effects.elementAt(0).characterEffectId.cefCrtId,
                        actual.effects.elementAt(0).characterEffectId.cefCrtId)
                assertEffect(expected.effects.elementAt(0).characterEffectId.cefEfcId,
                        actual.effects.elementAt(0).characterEffectId.cefEfcId)
            }
        }
    }


}