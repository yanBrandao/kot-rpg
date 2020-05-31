package br.com.woodriver.rpg.http

import br.com.woodriver.rpg.TestUtils.Companion.createPlayerWithCustomRace
import br.com.woodriver.rpg.domain.utils.types.RaceType
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.core.StringContains.containsString
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class CharacterControllerTests() {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `Controller should return players`() {
        mockMvc.perform(get("/players/")).andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("")))
    }


    @Test
    fun `Controller should return top10 players`() {
        mockMvc.perform(get("/players/top10")).andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("")))
    }


    @Test
    fun `Controller should create and delete a player`() {
        var player = createPlayerWithCustomRace(RaceType.ANDROID)
        mockMvc.perform(post("/players/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(player)))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("")))

        mockMvc.perform(delete("/players/2")).andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("")))
    }

    @Test
    fun `Controller should create a player`() {
        var player = createPlayerWithCustomRace(RaceType.CENTAUR)
        player.key = 101
        mockMvc.perform(post("/players/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(player)))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("")))

    }

    @Test
    fun `Controller should update a player`() {
        val player = createPlayerWithCustomRace(RaceType.GOBLIN)
        player.name = "YanZica"
        player.exp = 99.0

        mockMvc.perform(put("/players/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(player)))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().string(containsString("")))

    }


}