package br.com.woodriver.rpg.usecases

import br.com.woodriver.rpg.configuration.BlizzardTokenConfiguration
import br.com.woodriver.rpg.domains.*
import br.com.woodriver.rpg.domains.compositekeys.BagId
import br.com.woodriver.rpg.domains.compositekeys.PlayerEffectId
import br.com.woodriver.rpg.domains.compositekeys.SkillTreeId
import br.com.woodriver.rpg.domains.types.AbilityType
import br.com.woodriver.rpg.domains.types.EffectType
import br.com.woodriver.rpg.domains.types.PositionType
import br.com.woodriver.rpg.domains.types.RarityType
import br.com.woodriver.rpg.exceptions.KeyCannotBeZeroException
import br.com.woodriver.rpg.exceptions.PlayerAlreadyCreatedException
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import br.com.woodriver.rpg.usecases.player.CreateOrUpdatePlayerUseCase
import br.com.woodriver.rpg.usecases.player.DeletePlayerUseCase
import br.com.woodriver.rpg.usecases.player.GetAllPlayersUseCase
import br.com.woodriver.rpg.usecases.player.Top10BestPlayersUseCase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doNothing
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.math.BigDecimal

@SpringBootTest
class PlayerUseCaseTests {

    lateinit var createOrUpdatePlayerUseCase: CreateOrUpdatePlayerUseCase

    lateinit var getAllPlayersUseCase: GetAllPlayersUseCase

    lateinit var top10BestPlayersUseCase: Top10BestPlayersUseCase

    lateinit var deletePlayerUseCase: DeletePlayerUseCase

    @Mock
    lateinit var playerRepository: PlayerRepository

    @BeforeEach
    fun setup() {
        createOrUpdatePlayerUseCase = CreateOrUpdatePlayerUseCase(playerRepository)
        getAllPlayersUseCase = GetAllPlayersUseCase(playerRepository)
        top10BestPlayersUseCase = Top10BestPlayersUseCase(playerRepository)
        deletePlayerUseCase = DeletePlayerUseCase(playerRepository)

        var listPlayer: ArrayList<Player> = arrayListOf()
        var player = Player(1L, "Yan", "yan@zup.com.br", 1.0, listOf(), listOf(), listOf())
        listPlayer.add(player)
        `when`(playerRepository.save<Player?>(Mockito.any())).thenReturn(player)
        `when`(playerRepository.findAll()).thenReturn(listPlayer)
        `when`(playerRepository.findTop10ByOrderByExpDesc()).thenReturn(listPlayer)
        doNothing().`when`(playerRepository).delete(Mockito.any())
    }

    @Test
    fun `As a player, I want to save me in database`() {
        var listEffect = arrayListOf<PlayerEffect>()
        var listEquipment = arrayListOf<Bag>()
        var listSkillTree = arrayListOf<SkillTree>()
        val item = Item(1L, "Ring", 100.0, 100.0, PositionType.RIGHT_EAR, RarityType.LEGENDARY, "0")
        val otherItem = Item(2L, "Earring", 100.0, 100.0, PositionType.LEFT_ARM, RarityType.COMMON, "0")
        val player = Player(1L, "Yan", "yan@zup.com.br", 1.0, listEffect, listEquipment, listSkillTree)
        val equipmentId = BagId(player, item)
        val otherEquipmentId = BagId(player, otherItem)
        var clazz = Clazz()
        val skill = Skill()
        val otherSkill = Skill()
        val skillTreeId = SkillTreeId(player, skill)
        val otherSkillTreeId = SkillTreeId(player, otherSkill)
        val skillTree = SkillTree()
        val otherSameSkillTree = SkillTree(skillTreeId, 1, "Ice Fire Shot")
        val otherSkillTree = SkillTree(skillTreeId, 2, "Double Fire Shot")
        val otherSkillTreeWithOtherId = SkillTree(otherSkillTreeId, 1, "Ice Fire Shot")
        var equipment = Bag(equipmentId, true, 1)
        val otherSameEquipment = Bag(equipmentId, true, 1)
        val otherEquipment = Bag(equipmentId, false, 1)
        val otherEquipmentWithOtherId = Bag(otherEquipmentId, true, 1)
        val expectedHashCode = 31 * equipmentId.hashCode() + equipment.isEquipped.hashCode()
        var effect = Effect(2L, "None", 0.0, EffectType.AURA, 0.0, 0.0)
        listEquipment.add(equipment)
        var playerEffect = PlayerEffect(PlayerEffectId(player, effect))
        listEffect.add(playerEffect)
        listSkillTree.add(skillTree)

        skillTree.level = 1
        skillTree.rune = "Ice Fire Shot"
        skillTree.skillTreeId = skillTreeId

        skill.key = 1L
        skill.name = "Fire-ball"
        skill.type = AbilityType.PROJECTILE
        skill.clazz = clazz

        clazz.key = 1L
        clazz.name = "Mage"
        val expectedSkillHashCode = 31 * (skillTreeId.hashCode() + skillTree.rune.hashCode()) + skillTree.level.hashCode()

        var playerCreated = createOrUpdatePlayerUseCase.execute(player)

        setEffectValues(effect)

        Assertions.assertEquals(player.email, playerCreated.email)
        Assertions.assertEquals(1, player.effects.size)
        Assertions.assertEquals(1L, player.effects[0].playerEffectId.pefEfcId.key)
        Assertions.assertEquals("Speed", player.effects[0].playerEffectId.pefEfcId.name)
        Assertions.assertEquals(120.0, player.effects[0].playerEffectId.pefEfcId.value)
        Assertions.assertEquals(EffectType.BUFF, player.effects[0].playerEffectId.pefEfcId.type)
        Assertions.assertEquals(100.0, player.effects[0].playerEffectId.pefEfcId.range)
        Assertions.assertEquals(100.0, player.effects[0].playerEffectId.pefEfcId.duration)

        Assertions.assertEquals(1, player.bags.size)
        Assertions.assertTrue(player.bags[0] == equipment)
        Assertions.assertFalse(player.bags[0].equals(otherEquipment))
        Assertions.assertFalse(player.bags[0].equals(otherEquipmentWithOtherId))
        Assertions.assertTrue(player.bags[0].equals(otherSameEquipment))
        Assertions.assertFalse(player.bags[0].equals(null))
        Assertions.assertEquals(equipmentId, player.bags[0].bagId)

        Assertions.assertEquals(expectedHashCode, player.bags[0].hashCode())


        Assertions.assertTrue(player.skillTree[0] == skillTree)
        Assertions.assertFalse(player.skillTree[0].equals(otherSkillTree))
        Assertions.assertFalse(player.skillTree[0].equals(otherSkillTreeWithOtherId))
        Assertions.assertTrue(player.skillTree[0].equals(otherSameSkillTree))
        Assertions.assertEquals(1, player.skillTree[0].level)
        Assertions.assertEquals("Ice Fire Shot", player.skillTree[0].rune)
        Assertions.assertEquals(skillTreeId, player.skillTree[0].skillTreeId)
        Assertions.assertEquals(1L, player.skillTree[0].skillTreeId.sktSklId.key)
        Assertions.assertEquals("Fire-ball", player.skillTree[0].skillTreeId.sktSklId.name)
        Assertions.assertEquals(AbilityType.PROJECTILE, player.skillTree[0].skillTreeId.sktSklId.type)
        Assertions.assertEquals(clazz, player.skillTree[0].skillTreeId.sktSklId.clazz)
        Assertions.assertEquals(1L, player.skillTree[0].skillTreeId.sktSklId.clazz.key)
        Assertions.assertEquals("Mage", player.skillTree[0].skillTreeId.sktSklId.clazz.name)
        Assertions.assertEquals(expectedSkillHashCode, player.skillTree[0].hashCode())
    }

    @Test
    fun `As a user to update an attribute, I need to pass the Key`() {
        val player = Player(0L, "Yan", "yan@zup.com.br", 1.0, listOf(), listOf(), listOf())
        assertThrows<KeyCannotBeZeroException> { createOrUpdatePlayerUseCase.execute(player, true) }
    }

    @Test
    fun `As a user, I cannot create a player with same key value`() {
        `when`(playerRepository.findFirstByKey(Mockito.anyLong())).thenReturn(Player(1L, "name", "email", 1.0, listOf(), listOf(), listOf()))
        val player = Player(0L, "Yan", "yan@zup.com.br", 1.0, listOf(), listOf(), listOf())
        assertThrows<PlayerAlreadyCreatedException> { createOrUpdatePlayerUseCase.execute(player) }
    }

    @Test
    fun `As a user, I want to list all players`() {
        val listPlayers = getAllPlayersUseCase.execute()

        Assertions.assertEquals(1, listPlayers.size)
        Assertions.assertEquals("Yan", listPlayers[0].name)
    }

    @Test
    fun `As a user, I want to know the best 10 players by level`() {
        val listPlayers = top10BestPlayersUseCase.execute()

        Assertions.assertEquals(1, listPlayers.size)
        Assertions.assertEquals("Yan", listPlayers[0].name)
    }

    @Test
    fun `As a user, I want to delete a player`() {
        deletePlayerUseCase.execute(1L)
    }

    fun setEffectValues(effect: Effect) {
        effect.value = 120.0
        effect.key = 1L
        effect.name = "Speed"
        effect.type = EffectType.BUFF
        effect.duration = 100.0
        effect.range = 100.0
    }
}