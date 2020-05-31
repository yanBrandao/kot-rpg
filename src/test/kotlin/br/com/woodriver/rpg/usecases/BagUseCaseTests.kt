package br.com.woodriver.rpg.usecases

import br.com.woodriver.rpg.TestContext
import br.com.woodriver.rpg.TestUtils.Companion.assertBag
import br.com.woodriver.rpg.TestUtils.Companion.assertPlayerAttributes
import br.com.woodriver.rpg.TestUtils.Companion.createBag
import br.com.woodriver.rpg.TestUtils.Companion.createPlayerWithCustomRace
import br.com.woodriver.rpg.domain.Bag
import br.com.woodriver.rpg.domain.player.Character
import br.com.woodriver.rpg.domain.utils.types.RaceType
import br.com.woodriver.rpg.exceptions.IncorrectItemQuantityException
import br.com.woodriver.rpg.gateway.repository.BagRepository
import br.com.woodriver.rpg.gateway.repository.CharacterRepository
import br.com.woodriver.rpg.usecases.bag.GetItemsByPlayerUseCase
import br.com.woodriver.rpg.usecases.bag.InsertItemIntoBagPlayerUseCase
import br.com.woodriver.rpg.usecases.bag.SellPlayerItemUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class BagUseCaseTests: TestContext() {
    lateinit var getItemsByPlayerUseCase: GetItemsByPlayerUseCase
    lateinit var insertItemIntoBagPlayerUseCase: InsertItemIntoBagPlayerUseCase
    lateinit var sellPlayerItemUseCase: SellPlayerItemUseCase

    @MockBean
    lateinit var bagRepository: BagRepository

    @MockBean
    lateinit var characterRepository: CharacterRepository

    @BeforeEach
    fun setup(){
        getItemsByPlayerUseCase = GetItemsByPlayerUseCase(bagRepository)
        insertItemIntoBagPlayerUseCase = InsertItemIntoBagPlayerUseCase(bagRepository)
        sellPlayerItemUseCase = SellPlayerItemUseCase(bagRepository, characterRepository)

        `when`<Bag>(bagRepository.saveAndFlush(Mockito.any())).thenReturn(createBag())
        `when`<Character>(characterRepository.saveAndFlush(Mockito.any())).thenReturn(createPlayerWithCustomRace(RaceType.DARK_ELF))
        `when`<Character>(characterRepository.findFirstByKey(Mockito.anyLong())).thenReturn(createPlayerWithCustomRace(RaceType.DARK_ELF))
        `when`<Bag>(bagRepository.findFirstByBagIdBagItmIdKeyAndBagIdBagCrtIdKey(Mockito.anyLong(),
                Mockito.anyLong())).thenReturn(createBag())
    }

    @Test
    fun getItemByPlayerTest(){
        val bag = insertItemIntoBagPlayerUseCase.execute(createBag())

        assertBag(createBag(), bag)
    }

    @Test
    fun sellPlayerItem(){
        val player = sellPlayerItemUseCase.execute(10, 10, 1)
        assertPlayerAttributes(createPlayerWithCustomRace(RaceType.DARK_ELF), player)
    }

    @Test
    fun sellPlayerItemEquals(){
        val player = sellPlayerItemUseCase.execute(10, 10, 10)
        assertPlayerAttributes(createPlayerWithCustomRace(RaceType.DARK_ELF), player)
    }

    @Test
    fun sellPlayerItemMore(){
        assertThrows<IncorrectItemQuantityException> { sellPlayerItemUseCase.execute(10, 10, 20) }
    }

}