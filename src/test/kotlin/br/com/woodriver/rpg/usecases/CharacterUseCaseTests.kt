package br.com.woodriver.rpg.usecases

import br.com.woodriver.rpg.TestUtils.Companion.assertPlayer
import br.com.woodriver.rpg.TestUtils.Companion.createPlayerWithCustomRace
import br.com.woodriver.rpg.domain.player.Character
import br.com.woodriver.rpg.domain.utils.types.*
import br.com.woodriver.rpg.exceptions.KeyCannotBeZeroException
import br.com.woodriver.rpg.gateway.mapper.CharacterMapper
import br.com.woodriver.rpg.gateway.repository.CharacterRepository
import br.com.woodriver.rpg.usecases.character.CreateOrUpdatePlayerUseCase
import br.com.woodriver.rpg.usecases.character.DeletePlayerUseCase
import br.com.woodriver.rpg.usecases.character.GetAllPlayersUseCase
import br.com.woodriver.rpg.usecases.character.Top10BestPlayersUseCase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doNothing
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CharacterUseCaseTests {

    lateinit var createOrUpdatePlayerUseCase: CreateOrUpdatePlayerUseCase

    lateinit var getAllPlayersUseCase: GetAllPlayersUseCase

    lateinit var top10BestPlayersUseCase: Top10BestPlayersUseCase

    lateinit var deletePlayerUseCase: DeletePlayerUseCase

    @Mock
    lateinit var characterRepository: CharacterRepository

    @BeforeEach
    fun setup() {
        createOrUpdatePlayerUseCase = CreateOrUpdatePlayerUseCase(characterRepository)
        getAllPlayersUseCase = GetAllPlayersUseCase(characterRepository)
        top10BestPlayersUseCase = Top10BestPlayersUseCase(characterRepository)
        deletePlayerUseCase = DeletePlayerUseCase(characterRepository)

        val listCharacter: ArrayList<Character> = arrayListOf()
        val player = createPlayerWithCustomRace(RaceType.DWARF)
        listCharacter.add(player)
        `when`(characterRepository.save<Character?>(Mockito.any())).thenReturn(player)
        `when`(characterRepository.findAll()).thenReturn(listCharacter)
        `when`(characterRepository.findTop10ByOrderByExpDesc()).thenReturn(listCharacter)
        doNothing().`when`(characterRepository).delete(Mockito.any())
    }

    @Test
    fun `As a player, I want to save me in database`() {
        val player = createPlayerWithCustomRace(RaceType.DWARF)

        val playerCreated = createOrUpdatePlayerUseCase.execute(CharacterMapper().convertEntityToRequest(player))

        assertPlayer(player, playerCreated)
    }

    @Test
    fun `As a user to update an attribute, I need to pass the Key`() {
        val player = createPlayerWithCustomRace(RaceType.ORC)
        player.key = 0L

        assertThrows<KeyCannotBeZeroException> { createOrUpdatePlayerUseCase.execute(CharacterMapper().convertEntityToRequest(player), true) }
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
}