package br.com.woodriver.rpg

import br.com.woodriver.rpg.configuration.BlizzardClientConfiguration
import br.com.woodriver.rpg.configuration.BlizzardTokenConfiguration
import br.com.woodriver.rpg.domains.OAuthBlizzard
import br.com.woodriver.rpg.gateway.client.BlizzardSTSClient
import org.aspectj.lang.annotation.Before
import org.hibernate.SessionFactory
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.doNothing
import org.mockito.MockitoAnnotations
import org.springframework.boot.runApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.event.annotation.BeforeTestClass
import org.springframework.test.context.event.annotation.BeforeTestMethod

@SpringBootTest
class RpgApplicationTests {

	@MockBean
	lateinit var blizzardClientConfiguration: BlizzardClientConfiguration

	@MockBean
	lateinit var blizzardTokenConfiguration: BlizzardTokenConfiguration

	@Mock
	lateinit var sessionFactory: SessionFactory

	@Test
	fun contextLoads() {
	}

	@Test
	fun mainTest(){
		main(arrayOf())
	}

}
