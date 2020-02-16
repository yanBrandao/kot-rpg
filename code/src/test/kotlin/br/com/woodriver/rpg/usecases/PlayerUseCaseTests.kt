package br.com.woodriver.rpg.usecases

import br.com.woodriver.rpg.configuration.BlizzardTokenConfiguration
import br.com.woodriver.rpg.domains.Effect
import br.com.woodriver.rpg.domains.Equipment
import br.com.woodriver.rpg.domains.Item
import br.com.woodriver.rpg.domains.Player
import br.com.woodriver.rpg.domains.compositekeys.EquipmentId
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
import org.springframework.util.Assert

@SpringBootTest
class PlayerUseCaseTests {

    lateinit var createOrUpdatePlayerUseCase: CreateOrUpdatePlayerUseCase

    lateinit var getAllPlayersUseCase: GetAllPlayersUseCase

    lateinit var top10BestPlayersUseCase: Top10BestPlayersUseCase

    lateinit var deletePlayerUseCase: DeletePlayerUseCase

    @Mock
    lateinit var playerRepository: PlayerRepository

    @MockBean
    lateinit var blizzardTokenConfiguration: BlizzardTokenConfiguration

    @BeforeEach
    fun setup(){
        createOrUpdatePlayerUseCase = CreateOrUpdatePlayerUseCase(playerRepository)
        getAllPlayersUseCase = GetAllPlayersUseCase(playerRepository)
        top10BestPlayersUseCase = Top10BestPlayersUseCase(playerRepository)
        deletePlayerUseCase = DeletePlayerUseCase(playerRepository)

        var listPlayer: ArrayList<Player> = arrayListOf()
        var player = Player(1L, "Yan", "yan@zup.com.br", 1, listOf(), listOf())
        listPlayer.add(player)
        `when`(playerRepository.save<Player?>(Mockito.any())).thenReturn(player)
        `when`(playerRepository.findAll()).thenReturn(listPlayer)
        `when`(playerRepository.findTop10ByOrderByLevelDesc()).thenReturn(listPlayer)
        doNothing().`when`(playerRepository).delete(Mockito.any())
    }

    @Test
    fun `As a player, I want to save me in database`(){
        var listEffect = arrayListOf<Effect>()
        var listEquipment = arrayListOf<Equipment>()
        val item = Item(1L, "Ring", 100.0, 100.0, PositionType.RIGHT_EAR, RarityType.LEGENDARY, "0")
        val otherItem = Item(2L, "Earring", 100.0, 100.0, PositionType.LEFT_ARM, RarityType.COMMON, "0")
        val player = Player(1L, "Yan", "yan@zup.com.br", 1, listEffect, listEquipment)
        val equipmentId = EquipmentId(player, item)
        val otherEquipmentId = EquipmentId(player, otherItem)
        var equipment = Equipment(equipmentId, PositionType.RIGHT_EAR)
        val otherSameEquipment = Equipment(equipmentId, PositionType.RIGHT_EAR)
        val otherEquipment = Equipment(equipmentId, PositionType.LEFT_ARM)
        val otherEquipmentWithOtherId = Equipment(otherEquipmentId, PositionType.LEFT_ARM)
        val expectedHashCode = 31 * equipmentId.hashCode() + equipment.positionType.hashCode()
        var effect = Effect(1L, "Speed", 120.0, EffectType.BUFF, 100.0, 100.0, listOf())
        listEquipment.add(equipment)
        listEffect.add(effect)


        var playerCreated = createOrUpdatePlayerUseCase.execute(player)

        Assertions.assertEquals(player.email, playerCreated.email)
        Assertions.assertEquals(1, player.effects.size)
        Assertions.assertEquals(1L, player.effects[0].key)
        Assertions.assertEquals("Speed", player.effects[0].name)
        Assertions.assertEquals(120.0, player.effects[0].value)
        Assertions.assertEquals(EffectType.BUFF, player.effects[0].type)
        Assertions.assertEquals(100.0, player.effects[0].range)
        Assertions.assertEquals(100.0, player.effects[0].duration)
        Assertions.assertEquals(0, player.effects[0].players.size)

        Assertions.assertEquals(1, player.equipment.size)
        Assertions.assertEquals(PositionType.RIGHT_EAR, player.equipment[0].positionType)
        Assertions.assertTrue(player.equipment[0] == equipment)
        Assertions.assertFalse(player.equipment[0].equals(otherEquipment))
        Assertions.assertFalse(player.equipment[0].equals(otherEquipmentWithOtherId))
        Assertions.assertTrue(player.equipment[0].equals(otherSameEquipment))
        Assertions.assertFalse(player.equipment[0].equals(null))
        Assertions.assertEquals(equipmentId, player.equipment[0].equipmentId)

        Assertions.assertEquals(expectedHashCode, player.equipment[0].hashCode())
    }

    @Test
    fun `As a user to update an attribute, I need to pass the Key`(){
        val player = Player(0L, "Yan", "yan@zup.com.br", 1, listOf(), listOf())
        assertThrows<KeyCannotBeZeroException> { createOrUpdatePlayerUseCase.execute(player, true) }
    }

    @Test
    fun `As a user, I cannot create a player with same key value`(){
        `when`(playerRepository.findFirstByKey(Mockito.anyLong())).thenReturn(Player(1L, "name", "email", 1, listOf(), listOf()))
        val player = Player(0L, "Yan", "yan@zup.com.br", 1, listOf(), listOf())
        assertThrows<PlayerAlreadyCreatedException> { createOrUpdatePlayerUseCase.execute(player) }
    }

    @Test
    fun `As a user, I want to list all players`(){
        val listPlayers = getAllPlayersUseCase.execute()

        Assertions.assertEquals(1, listPlayers.size)
        Assertions.assertEquals("Yan", listPlayers[0].name)
    }

    @Test
    fun `As a user, I want to know the best 10 players by level`(){
        val listPlayers = top10BestPlayersUseCase.execute()

        Assertions.assertEquals(1, listPlayers.size)
        Assertions.assertEquals("Yan", listPlayers[0].name)
    }

    @Test
    fun `As a user, I want to delete a player`(){
        deletePlayerUseCase.execute(1L)
    }
}