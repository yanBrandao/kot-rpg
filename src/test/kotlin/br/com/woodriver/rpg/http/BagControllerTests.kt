package br.com.woodriver.rpg.http

import br.com.woodriver.rpg.TestContext
import br.com.woodriver.rpg.TestUtils.Companion.createBag
import br.com.woodriver.rpg.TestUtils.Companion.createUser
import br.com.woodriver.rpg.domain.Bag
import br.com.woodriver.rpg.domain.BlizzardItem
import br.com.woodriver.rpg.domain.BlizzardItemDetail
import br.com.woodriver.rpg.gateway.repository.BagRepository
import br.com.woodriver.rpg.gateway.repository.ItemRepository
import br.com.woodriver.rpg.usecases.item.CreateItemUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import com.nhaarman.mockitokotlin2.any
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.hamcrest.core.StringContains
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
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

    lateinit var createItemUseCase: CreateItemUseCase

    @Autowired
    lateinit var itemRepository: ItemRepository

    @MockBean
    lateinit var bagRepository: BagRepository

    private val logger: Log = LogFactory.getLog(BagControllerTests::class.java)

    @BeforeEach
    fun setup() {
        val blizzardItemDetail = BlizzardItemDetail("99", "detail")
        val blizzardItemDetails = arrayListOf<BlizzardItemDetail>()
        blizzardItemDetails.add(blizzardItemDetail)
        val blizzardItem = BlizzardItem("_link", blizzardItemDetails)
        createItemUseCase = CreateItemUseCase(itemRepository, blizzardAPIClient,
                blizzardTokenConfiguration)
        `when`(blizzardAPIClient.getItemIcon(anyString(), anyString())).thenReturn(blizzardItem)
        `when`(bagRepository.save(any<Bag>())).thenReturn(createBag())
    }

    @Test
    fun `Controller should create a bag`() {
        val bag = createBag()

        logger.info("Creating user")
        mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(createUser())))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("")))
        logger.info("User created")
        logger.info("Creating character")
        mockMvc.perform(MockMvcRequestBuilders.post("/characters/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(bag.bagId.bagCrtId)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("")))
        logger.info("Character created")
        logger.info("Creating item")
        mockMvc.perform(MockMvcRequestBuilders.post("/items/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(bag.bagId.bagItmId)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("")))
        logger.info("Item created")
        mockMvc.perform(MockMvcRequestBuilders.post("/bags/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(bag)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("")))
    }
}