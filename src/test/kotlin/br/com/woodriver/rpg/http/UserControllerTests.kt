package br.com.woodriver.rpg.http

import br.com.woodriver.rpg.TestContext
import br.com.woodriver.rpg.TestUtils
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.core.StringContains
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests: TestContext() {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `Controller should create an user`() {
        var user = TestUtils.createUser()
        mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(user)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("")))
    }

    @Test
    fun `Controller should not found user`(){
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                .header("x-kot-email", "")
                .header("x-kot-password", "")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("Login Failed")))
    }

    @Test
    fun `Controller should login successfully`(){
        var user = TestUtils.createUser()
        mockMvc.perform(MockMvcRequestBuilders.post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(user)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("")))

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                .header("x-kot-email", user.email)
                .header("x-kot-password", user.password)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("key")))
    }
}