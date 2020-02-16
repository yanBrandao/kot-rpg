package br.com.woodriver.rpg.http

import br.com.woodriver.rpg.configuration.BlizzardTokenConfiguration
import br.com.woodriver.rpg.domains.BlizzardItem
import br.com.woodriver.rpg.domains.BlizzardItemDetail
import br.com.woodriver.rpg.domains.Item
import br.com.woodriver.rpg.domains.types.PositionType
import br.com.woodriver.rpg.domains.types.RarityType
import br.com.woodriver.rpg.gateway.client.BlizzardAPIClient
import br.com.woodriver.rpg.gateway.repository.ItemRepository
import br.com.woodriver.rpg.usecases.item.CreateItemUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.core.StringContains
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTests {

        @Autowired
        lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var createItemUseCase: CreateItemUseCase

    @MockBean
    lateinit var itemRepository: ItemRepository

    @MockBean
    lateinit var blizzardAPIClient: BlizzardAPIClient

    @MockBean
    lateinit var blizzardTokenConfiguration: BlizzardTokenConfiguration


    @BeforeEach
    fun setup(){
        val blizzardItemDetail = BlizzardItemDetail("99", "detail")
        val blizzardItemDetails = arrayListOf<BlizzardItemDetail>()
        blizzardItemDetails.add(blizzardItemDetail)
        val blizzardItem = BlizzardItem("_link", blizzardItemDetails)
        createItemUseCase =  CreateItemUseCase(itemRepository, blizzardAPIClient,
                blizzardTokenConfiguration)
        val item = Item(1L, "Ring", 100.0, 100.0, PositionType.RIGHT_EAR, RarityType.LEGENDARY, "0")
        `when`(blizzardAPIClient.getItemIcon(Mockito.anyString(), Mockito.anyString())).thenReturn(blizzardItem)
        `when`(itemRepository.save<Item?>(Mockito.any())).thenReturn(item)
    }


    @Test
    fun `Controller should return all items`(){
        mockMvc.perform(MockMvcRequestBuilders.get("/items/")
                .header("offset", "0")
                .header("pageNumber", "0")
                .header("pageSize", "100")
                .header("paged", "0")
                .header("sort.sorted", "0")
                .header("unpaged", "0")
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("")))
    }


    @Test
    fun `Controller should create an item`(){
        val item = Item(1L, "Ring", 100.0, 100.0, PositionType.RIGHT_EAR, RarityType.LEGENDARY, "0")
        mockMvc.perform(MockMvcRequestBuilders.post("/items/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(item)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("")))
    }
}