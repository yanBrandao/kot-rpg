package br.com.woodriver.rpg.usecases

import br.com.woodriver.rpg.TestContext
import br.com.woodriver.rpg.TestUtils.Companion.createRingItem
import br.com.woodriver.rpg.TestUtils.Companion.createRingItemWithIcon
import br.com.woodriver.rpg.domain.BlizzardItem
import br.com.woodriver.rpg.domain.BlizzardItemDetail
import br.com.woodriver.rpg.domain.Item
import br.com.woodriver.rpg.exceptions.IconEmptyException
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
class ItemUseCaseTests: TestContext() {
    lateinit var createItemUseCase: CreateItemUseCase


    @MockBean
    lateinit var itemRepository: ItemRepository

    @BeforeEach
    fun setup(){
        val blizzardItemDetail = BlizzardItemDetail("99", "detail")
        val blizzardItemDetails = arrayListOf<BlizzardItemDetail>()
        blizzardItemDetails.add(blizzardItemDetail)
        val blizzardItem = BlizzardItem("_link", blizzardItemDetails)

        val item = createRingItemWithIcon("detail")

        createItemUseCase = CreateItemUseCase(itemRepository,
                blizzardAPIClient, blizzardTokenConfiguration)

        `when`(blizzardAPIClient.getItemIcon(Mockito.anyString(), Mockito.anyString())).thenReturn(blizzardItem)
        `when`(itemRepository.save<Item?>(Mockito.any())).thenReturn(item)
    }

    @Test
    fun testCreateItemUseCase(){
        val item = createRingItem()
        var itemCreated = createItemUseCase.execute(item)

        Assertions.assertEquals("detail", itemCreated.icon)
    }

    @Test
    fun `As a user, If I pass an empty icon value, it will throw an error`(){
        val item = createRingItem()
        item.icon = ""
        assertThrows<IconEmptyException> { createItemUseCase.execute(item) }
    }

}