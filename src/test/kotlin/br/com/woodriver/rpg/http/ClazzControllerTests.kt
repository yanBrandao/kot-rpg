package br.com.woodriver.rpg.http

import br.com.woodriver.rpg.TestContext
import br.com.woodriver.rpg.TestUtils.Companion.createClazz
import br.com.woodriver.rpg.domain.Clazz
import br.com.woodriver.rpg.gateway.repository.ClazzRepository
import br.com.woodriver.rpg.usecases.clazz.CreateClazzUseCase
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
class ClazzControllerTests : TestContext() {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var createClazzUseCase: CreateClazzUseCase

    @MockBean
    lateinit var clazzRepository: ClazzRepository


    @BeforeEach
    fun setup() {
        createClazzUseCase = CreateClazzUseCase(clazzRepository)
        `when`(clazzRepository.save<Clazz?>(Mockito.any())).thenReturn(createClazz())
    }

    @Test
    fun `Controller should create a class`() {

        mockMvc.perform(MockMvcRequestBuilders.post("/clazzes/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(createClazz())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("")))
    }
}