package br.com.woodriver.rpg

import org.hibernate.SessionFactory
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RpgApplicationTests: TestContext() {

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
