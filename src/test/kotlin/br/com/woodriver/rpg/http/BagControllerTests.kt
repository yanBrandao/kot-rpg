package br.com.woodriver.rpg.http

import br.com.woodriver.rpg.TestContext
import br.com.woodriver.rpg.TestUtils.Companion.createBag
import br.com.woodriver.rpg.domain.BlizzardItem
import br.com.woodriver.rpg.domain.BlizzardItemDetail
import br.com.woodriver.rpg.domain.player.Character
import br.com.woodriver.rpg.gateway.repository.ItemRepository
import br.com.woodriver.rpg.usecases.item.CreateItemUseCase
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import org.hamcrest.core.StringContains
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
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
class BagControllerTests : TestContext() {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var createItemUseCase: CreateItemUseCase

    @Autowired
    lateinit var itemRepository: ItemRepository


    @BeforeEach
    fun setup() {
        val blizzardItemDetail = BlizzardItemDetail("99", "detail")
        val blizzardItemDetails = arrayListOf<BlizzardItemDetail>()
        blizzardItemDetails.add(blizzardItemDetail)
        val blizzardItem = BlizzardItem("_link", blizzardItemDetails)
        createItemUseCase = CreateItemUseCase(itemRepository, blizzardAPIClient,
                blizzardTokenConfiguration)
        Mockito.`when`(blizzardAPIClient.getItemIcon(Mockito.anyString(), Mockito.anyString())).thenReturn(blizzardItem)
    }

    @Test
    fun `Controller should create a bag`() {


        val bag = createBag()

        mockMvc.perform(MockMvcRequestBuilders.post("/players/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(bag.bagId.bagCrtId)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("")))

        mockMvc.perform(MockMvcRequestBuilders.post("/items/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(bag.bagId.bagItmId)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("")))


        mockMvc.perform(MockMvcRequestBuilders.post("/bags/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(bag)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("")))
    }

    //Converts Object to Json String
    @Throws(JsonProcessingException::class)
    private fun convertObjectToJsonString(character: Character): String? {
        val writer: ObjectWriter? = ObjectMapper().writer().withDefaultPrettyPrinter()
        return writer?.writeValueAsString(character)
    }
}