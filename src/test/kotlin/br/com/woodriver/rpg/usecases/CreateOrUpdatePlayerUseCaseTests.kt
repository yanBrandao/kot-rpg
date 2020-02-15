package br.com.woodriver.rpg.usecases

import br.com.woodriver.rpg.configuration.BlizzardTokenConfiguration
import br.com.woodriver.rpg.domains.Player
import br.com.woodriver.rpg.gateway.repository.PlayerRepository
import br.com.woodriver.rpg.usecases.player.CreateOrUpdatePlayerUseCase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class CreateOrUpdatePlayerUseCaseTests {

    lateinit var createOrUpdatePlayerUseCase: CreateOrUpdatePlayerUseCase

    @Mock
    lateinit var playerRepository: PlayerRepository

    @MockBean
    lateinit var blizzardTokenConfiguration: BlizzardTokenConfiguration

    @BeforeEach
    fun setup(){
        createOrUpdatePlayerUseCase = CreateOrUpdatePlayerUseCase(playerRepository)
        val player = Player(1L, "Yan", "yan@zup.com.br", 1, listOf(), listOf())
        `when`(playerRepository.save<Player?>(Mockito.any())).thenReturn(player)
    }

    @Test
    fun `As a player, I want to save me in database`(){
        val player = Player(1L, "Yan", "yan@zup.com.br", 1, listOf(), listOf())
        var playerCreated = createOrUpdatePlayerUseCase.execute(player)

        Assertions.assertEquals(player.email, playerCreated.email)
    }
}