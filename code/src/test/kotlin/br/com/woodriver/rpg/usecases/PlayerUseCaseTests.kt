package br.com.woodriver.rpg.usecases

import br.com.woodriver.rpg.configuration.BlizzardTokenConfiguration
import br.com.woodriver.rpg.domains.*
import br.com.woodriver.rpg.domains.compositekeys.BagId
import br.com.woodriver.rpg.domains.compositekeys.PlayerEffectId
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
    fun setup(){
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
    fun `As a player, I want to save me in database`(){
        var listEffect = arrayListOf<PlayerEffect>()
        var listEquipment = arrayListOf<Bag>()
        val item = Item(1L, "Ring", 100.0, 100.0, PositionType.RIGHT_EAR, RarityType.LEGENDARY, "0")
        val otherItem = Item(2L, "Earring", 100.0, 100.0, PositionType.LEFT_ARM, RarityType.COMMON, "0")
        val player = Player(1L, "Yan", "yan@zup.com.br", 1.0, listEffect, listEquipment, listOf())
        val equipmentId = BagId(player, item)
        val otherEquipmentId = BagId(player, otherItem)
        var equipment = Bag(equipmentId, true)
        val otherSameEquipment = Bag(equipmentId, true)
        val otherEquipment = Bag(equipmentId, false)
        val otherEquipmentWithOtherId = Bag(otherEquipmentId, true)
        val expectedHashCode = 31 * equipmentId.hashCode() + equipment.isEquipped.hashCode()
        var effect = Effect(1L, "Speed", 120.0, EffectType.BUFF, 100.0, 100.0)
        listEquipment.add(equipment)
        var playerEffect = PlayerEffect(PlayerEffectId(player, effect))
        listEffect.add(playerEffect)


        var playerCreated = createOrUpdatePlayerUseCase.execute(player)

        Assertions.assertEquals(player.email, playerCreated.email)
        Assertions.assertEquals(1, player.effects.size)
        //TODO: Fix Effect Test
//        Assertions.assertEquals(1L, player.effects[0].)
//        Assertions.assertEquals("Speed", player.effects[0].name)
//        Assertions.assertEquals(120.0, player.effects[0].value)
//        Assertions.assertEquals(EffectType.BUFF, player.effects[0].type)
//        Assertions.assertEquals(100.0, player.effects[0].range)
//        Assertions.assertEquals(100.0, player.effects[0].duration)
//        Assertions.assertEquals(0, player.effects[0].players.size)

        Assertions.assertEquals(1, player.bags.size)
        Assertions.assertTrue(player.bags[0] == equipment)
        Assertions.assertFalse(player.bags[0].equals(otherEquipment))
        Assertions.assertFalse(player.bags[0].equals(otherEquipmentWithOtherId))
        Assertions.assertTrue(player.bags[0].equals(otherSameEquipment))
        Assertions.assertFalse(player.bags[0].equals(null))
        Assertions.assertEquals(equipmentId, player.bags[0].bagId)

        Assertions.assertEquals(expectedHashCode, player.bags[0].hashCode())
    }

    @Test
    fun `As a user to update an attribute, I need to pass the Key`(){
        val player = Player(0L, "Yan", "yan@zup.com.br", 1.0, listOf(), listOf(), listOf())
        assertThrows<KeyCannotBeZeroException> { createOrUpdatePlayerUseCase.execute(player, true) }
    }

    @Test
    fun `As a user, I cannot create a player with same key value`(){
        `when`(playerRepository.findFirstByKey(Mockito.anyLong())).thenReturn(Player(1L, "name", "email", 1.0, listOf(), listOf(), listOf()))
        val player = Player(0L, "Yan", "yan@zup.com.br", 1.0, listOf(), listOf(), listOf())
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