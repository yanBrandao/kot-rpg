package br.com.woodriver.rpg.usecases

import br.com.woodriver.rpg.configuration.BlizzardTokenConfiguration
import br.com.woodriver.rpg.domains.BlizzardItem
import br.com.woodriver.rpg.domains.BlizzardItemDetail
import br.com.woodriver.rpg.domains.Item
import br.com.woodriver.rpg.domains.types.PositionType
import br.com.woodriver.rpg.domains.types.RarityType
import br.com.woodriver.rpg.exceptions.IconEmptyException
import br.com.woodriver.rpg.gateway.client.BlizzardAPIClient
import br.com.woodriver.rpg.gateway.repository.ItemRepository
import br.com.woodriver.rpg.usecases.item.CreateItemUseCase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class ItemUseCaseTests {
    lateinit var createItemUseCase: CreateItemUseCase

    @MockBean
    lateinit var blizzardTokenConfiguration: BlizzardTokenConfiguration

    @MockBean
    lateinit var blizzardAPIClient: BlizzardAPIClient

    @MockBean
    lateinit var itemRepository: ItemRepository

    @BeforeEach
    fun setup(){
        val blizzardItemDetail = BlizzardItemDetail("99", "detail")
        val blizzardItemDetails = arrayListOf<BlizzardItemDetail>()
        blizzardItemDetails.add(blizzardItemDetail)
        val blizzardItem = BlizzardItem("_link", blizzardItemDetails)

        val item = Item(1L, "Ring", 10.0,
                10.0, PositionType.FINGERS,
                RarityType.EPIC, "detail")

        createItemUseCase = CreateItemUseCase(itemRepository,
                blizzardAPIClient, blizzardTokenConfiguration)

        `when`(blizzardAPIClient.getItemIcon(Mockito.anyString(), Mockito.anyString())).thenReturn(blizzardItem)
        `when`(itemRepository.save<Item?>(Mockito.any())).thenReturn(item)
    }

    @Test
    fun testCreateItemUseCase(){
        val item = Item(1L, "Ring", 10.0,
                             10.0, PositionType.FINGERS,
                              RarityType.EPIC, "10005")
        var itemCreated = createItemUseCase.execute(item)

        Assertions.assertEquals("detail", itemCreated.icon)

    }

    @Test
    fun `As a user, If I pass an empty icon value, it will throw an error`(){
        val item = Item(1L, "Ring", 10.0,
                10.0, PositionType.FINGERS,
                RarityType.EPIC, "")
        assertThrows<IconEmptyException> { createItemUseCase.execute(item) }
    }

}